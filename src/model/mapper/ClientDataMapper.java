package model.mapper;

import model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDataMapper implements DataMapper<Client> {
    private String fileName;

    public ClientDataMapper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Client> readAll() {
        List<Client> clienti = new ArrayList<Client>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while((linie = br.readLine()) != null) {
                String[] fields = linie.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                String telefon = fields[2];
                Client c = new Client(id, name, telefon);
                clienti.add(c);
            }
            return clienti;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(List<Client> obiecte) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Client c : obiecte) {
                bw.write(c.getId() + "," + c.getNume() + "," + c.getTelefon());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
