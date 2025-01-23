package model.mapper;

import model.Produs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdusDataMapper implements DataMapper<Produs> {
    private String fileName;

    public ProdusDataMapper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Produs> readAll() {
        List<Produs> produse = new ArrayList<Produs>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while((linie = br.readLine()) != null) {
                String[] fields = linie.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                double price = Double.parseDouble(fields[2]);
                String categorie = fields[3];
                Produs p = new Produs(id, name, price, categorie);
                produse.add(p);
            }
            return produse;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(List<Produs> obiecte) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,false))) {
            for(Produs p : obiecte) {
                bw.write(p.getId() + "," + p.getNume() + "," + p.getPret() + "," + p.getCategorie());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
