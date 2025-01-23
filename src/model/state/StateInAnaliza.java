package model.state;

import model.Reclamatie;

public class StateInAnaliza implements State {

    @Override
    public void doAction(Reclamatie r) {
        System.out.println("Reclamatia " +r.getId() + " se afla in starea: in analiza.");
    }

    @Override
    public String getStateName() {
        return "in analiza";
    }
}
