package model;

import model.observer.Observer;

public class Client implements Observer {
    private int id;
    private String nume;
    private String telefon;

    public Client(int id, String nume, String telefon) {
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }

    @Override
    public void update(String mesaj) {
        System.out.println("Notificare pentru clientul " + nume + ": " + mesaj);
    }
}
