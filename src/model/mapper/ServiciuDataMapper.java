package model.mapper;

import model.Serviciu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiciuDataMapper implements DataMapper<Serviciu> {

    private String fileName;

    public ServiciuDataMapper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Serviciu> readAll() {
        List<Serviciu> servicii = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                int id = Integer.parseInt(split[0]);
                String name = split[1];
                double pret = Double.parseDouble(split[2]);
                int durata = Integer.parseInt(split[3]);
                Serviciu s = new Serviciu(id, name, pret, durata);
                servicii.add(s);
            }
            return servicii;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(List<Serviciu> obiecte) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Serviciu s : obiecte) {
                bw.write(s.getId() + "," + s.getNume() + "," + s.getPret() + "," + s.getDurata());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
