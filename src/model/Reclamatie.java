package model;

import model.mapper.ReclamatieDataMapper;
import model.observer.Observable;
import model.observer.Observer;
import model.state.State;
import model.state.StateInregistrata;

import java.util.ArrayList;
import java.util.List;

public class Reclamatie implements Observable {
    private int id;
    private String detalii;
    private Client client;
    private Articol articol;
    private State state;

    public Reclamatie(int id, String detalii, Client client, Articol articol) {
        this.id = id;
        this.detalii = detalii;
        this.client = client;
        this.articol = articol;
        this.state = new StateInregistrata();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Articol getArticol() {
        return articol;
    }

    public void setArticol(Articol articol) {
        this.articol = articol;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void schimbaStarea(State stareNoua){
        this.state = stareNoua;
        stareNoua.doAction(this);
        notificaObserver();
    }

    @Override
    public String toString() {
        return "Reclamatie{" +
                "id=" + id +
                ", detalii='" + detalii + '\'' +
                ", client=" + client +
                ", articol=" + articol +
                ", state=" + state.getStateName() +
                '}';
    }

    @Override
    public void adaugaObserver(Observer observer) {
        throw new UnsupportedOperationException("Nu se pot adauga mai multi observatori pentru o reclamatie!");
    }

    @Override
    public void stergeObserver(Observer observer) {
        throw new UnsupportedOperationException("Nu se poate sterge observatorul!");
    }

    @Override
    public void notificaObserver() {
        String mesaj = "Reclamatia " + id + " a fost modificata in starea " + state.getStateName();
        client.update(mesaj);
    }
}
