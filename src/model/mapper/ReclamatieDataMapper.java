package model.mapper;

import model.*;
import model.state.State;
import model.state.StateInAnaliza;
import model.state.StateInregistrata;
import model.state.StateSolutionata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamatieDataMapper implements DataMapper<Reclamatie> {
    private String fileName;
    private final ClientDataMapper clientDataMapper;
    private final ProdusDataMapper produsDataMapper;
    private final ServiciuDataMapper serviciuDataMapper;

    public ReclamatieDataMapper(String fileName, ClientDataMapper clientDataMapper, ProdusDataMapper produsDataMapper, ServiciuDataMapper serviciuDataMapper) {
        this.fileName = fileName;
        this.clientDataMapper = clientDataMapper;
        this.produsDataMapper = produsDataMapper;
        this.serviciuDataMapper = serviciuDataMapper;
    }

    @Override
    public List<Reclamatie> readAll() {
        List<Reclamatie> reclamatii = new ArrayList<Reclamatie>();
        List<Client> clienti = clientDataMapper.readAll();
        List<Produs> produse = produsDataMapper.readAll();
        List<Serviciu> servicii =serviciuDataMapper.readAll();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                int id = Integer.parseInt(split[0]);
                String detalii = split[1];
                String tip = split[2];
                int idClient = Integer.parseInt(split[3]);
                int idArticol = Integer.parseInt(split[4]);
                String stare = split[5];

                Client c = findClientById(clienti,idClient);
                if(c == null){
                    throw new IllegalArgumentException("Clientul cu id-ul " + idClient + " nu a fost gasit");
                }

                Articol articol = null;
                if(tip.equalsIgnoreCase("produs"))
                {
                    for(Produs p : produse){
                        if(p.getId() == idArticol){
                            articol = p;
                            break;
                        }
                    }
                }else if(tip.equalsIgnoreCase("serviciu")){
                    for(Serviciu s : servicii){
                        if(s.getId() == idArticol){
                            articol = s;
                            break;
                        }
                    }
                }

                State state;
                switch(stare){
                    case "inregistrata": {state = new StateInregistrata(); break;}
                    case "in analiza": {state = new StateInAnaliza(); break;}
                    case "solutionata":{state = new StateSolutionata(); break;}
                    default: throw new IllegalArgumentException("Stare " + stare + " nu a fost gasita");
                }
                Reclamatie r = new Reclamatie(id, detalii, c, articol);
                r.setState(state);

                reclamatii.add(r);
            }
            return reclamatii;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(List<Reclamatie> obiecte) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Reclamatie r : obiecte) {
                String tipArticol = (r.getArticol() instanceof Produs) ? "produs" : "serviciu";
                bw.write(r.getId() + "," + r.getDetalii() + "," + tipArticol + "," + + r.getClient().getId() + "," + r.getArticol().getId() + "," + r.getState().getStateName());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Client findClientById(List<Client> clienti, int id){
        for(Client c : clienti){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
