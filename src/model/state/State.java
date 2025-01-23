package model.state;

import model.Reclamatie;

public interface State {
    public void doAction(Reclamatie r);
    public String getStateName();
}
