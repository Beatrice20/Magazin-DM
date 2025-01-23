package model;

public class Produs extends Articol{
    private String categorie;

    public Produs(int id, String nume, double pret, String categorie) {
        super(id, nume, pret);
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produs{" +
                super.toString() +
                "categorie='" + categorie + '\'' +
                '}';
    }
}
