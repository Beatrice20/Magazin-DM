package model.observer;

public interface Observable {
    public void adaugaObserver(Observer observer);
    public void stergeObserver(Observer observer);
    public void notificaObserver();
}
