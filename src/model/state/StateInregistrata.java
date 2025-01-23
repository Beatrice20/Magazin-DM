package model.state;

import model.Reclamatie;

public class StateInregistrata implements State {

    @Override
    public void doAction(Reclamatie r) {
        System.out.println("Reclamatia " +r.getId() + " se afla in starea: inregistrata.");
    }

    @Override
    public String getStateName() {
        return "inregistrata";
    }
}
