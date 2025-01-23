package model;

public class Serviciu extends Articol{
    private int durata;

    public Serviciu(int id, String nume, double pret, int durata) {
        super(id, nume, pret);
        this.durata = durata;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Serviciu{" +
                super.toString() +
                "durata=" + durata +
                '}';
    }
}
