package model.state;

import model.Reclamatie;

import java.io.Serializable;

public class StateSolutionata implements State{
    @Override
    public void doAction(Reclamatie r) {
        System.out.println("Reclamatia " +r.getId() + " se afla in starea: solutionata.");
    }

    @Override
    public String getStateName() {
        return "solutionata";
    }
}
