import controller.ControllerReclamatie;
import model.mapper.ClientDataMapper;
import model.mapper.ProdusDataMapper;
import model.mapper.ReclamatieDataMapper;
import model.mapper.ServiciuDataMapper;
import view.ViewReclamatie;

public class Main {
    public static void main(String[] args) {

        String clientiFile = "data/clienti.txt";
        String produseFile = "data/produse.txt";
        String serviciiFile = "data/servicii.txt";
        String reclamatiiFile = "data/reclamatii.txt";

        ClientDataMapper clientMapper = new ClientDataMapper(clientiFile);
        ProdusDataMapper produseMapper = new ProdusDataMapper(produseFile);
        ServiciuDataMapper serviciuMapper = new ServiciuDataMapper(serviciiFile);
        ReclamatieDataMapper reclamatieMapper = new ReclamatieDataMapper(reclamatiiFile, clientMapper, produseMapper, serviciuMapper);

        ViewReclamatie view = new ViewReclamatie();
        ControllerReclamatie controller = new ControllerReclamatie(view, reclamatieMapper);
        controller.start();
    }
}