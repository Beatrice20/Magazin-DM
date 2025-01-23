package controller;

import model.Reclamatie;
import model.mapper.ReclamatieDataMapper;
import model.state.State;
import model.state.StateInAnaliza;
import model.state.StateSolutionata;
import view.ViewReclamatie;

import java.util.ArrayList;
import java.util.List;

public class ControllerReclamatie {

    public final ViewReclamatie view;
    public final ReclamatieDataMapper dataMapper;

    public ControllerReclamatie(ViewReclamatie view, ReclamatieDataMapper dataMapper) {
        this.view = view;
        this.dataMapper = dataMapper;
    }

    public void start() {
        while (true) {
            int optiune = view.citesteOptiune();
            switch (optiune) {
                case 1 -> vizualizeazaReclamatii();
                case 2 -> {
                    view.afiseazaMesaj("Introduceți starea dorită pentru filtrare: ");
                    String stare = view.citesteText();
                    filtreazaReclamatii(stare);
                }
                case 3 -> {
                    view.afiseazaMesaj("Introduceți ID-ul reclamației de modificat: ");
                    int id = view.citesteInt();
                    view.afiseazaMesaj("Introduceți noua stare: ");
                    String stareNoua = view.citesteText();
                    modificaStareReclamatie(id, stareNoua);
                }
                case 4 -> {
                    view.afiseazaMesaj("La revedere!");
                    return;
                }
                default -> view.afiseazaMesaj("Optiune invalida! Reincercati.");
            }
        }
    }

    public void vizualizeazaReclamatii(){
        List<Reclamatie> reclamatii = dataMapper.readAll();
        view.afiseazaReclamatii(reclamatii);
    }

    public void filtreazaReclamatii(String stare){
        List<Reclamatie> reclamatii = dataMapper.readAll();
        List<Reclamatie> reclamatiiFiltrare = new ArrayList<>();
        for(Reclamatie r : reclamatii){
            if(r.getState().getStateName().equalsIgnoreCase(stare)){
                reclamatiiFiltrare.add(r);
            }
        }
        view.afiseazaReclamatii(reclamatiiFiltrare);
    }

    public void modificaStareReclamatie(int id, String stareNoua){
        List<Reclamatie> reclamatii = dataMapper.readAll();
        boolean reclamatieGasita = false;
        for(Reclamatie r : reclamatii){
            if(r.getId() == id){
                r.schimbaStarea(mapState(stareNoua));
                dataMapper.save(reclamatii);
                reclamatieGasita = true;
                break;
            }
        }
        if(reclamatieGasita == true ){
            dataMapper.save(reclamatii);
            view.afiseazaMesaj("Starea reclamatiei a fost modificata cu succes!");
        }else{
            view.afiseazaMesaj("Reclamatia cu id-ul " + id + " nu a fost gasita!");
        }
    }

    private State mapState(String state){
        switch(state){
            case "inregistrata": return new StateInAnaliza();
            case "in analiza" : return new StateInAnaliza();
            case "solutionata" : return new StateSolutionata();
            default : return null;
        }
    }
}
