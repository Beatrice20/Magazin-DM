package view;

import model.Reclamatie;

import java.util.List;
import java.util.Scanner;

public class ViewReclamatie {
    private final Scanner scanner = new Scanner(System.in);

    public void afiseazaReclamatii(List<Reclamatie> reclamatii) {
        System.out.println("Lista reclamatiilor: ");
        for (Reclamatie r : reclamatii) {
            System.out.println(r.toString());
        }
    }

    public String citesteText() {
        return scanner.nextLine();
    }

    public int citesteInt() {
        int valoare = scanner.nextInt();
        scanner.nextLine();
        return valoare;
    }

    public int citesteOptiune(){
        System.out.println("\nMeniul principal: ");
        System.out.println("1.Vizualizeaza reclamatii ");
        System.out.println("2.Filtreaza/Cauta reclamatii dupa stare ");
        System.out.println("3.Modifica stare reclamatie ");
        System.out.println("4.Iesire ");
        System.out.print("Alegeti o optiune ");
        int optiune = scanner.nextInt();
        scanner.nextLine();
        return optiune;
    }

    public void afiseazaMesaj(String mesaj) {
        System.out.print(mesaj);
    }
}
