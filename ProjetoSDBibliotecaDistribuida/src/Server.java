
import java.rmi.Naming;

/**
 *
 * @author Lourivaldo
 */
public class Server {

    public Server(String nome) {
        try {
            BibCentralImpl central = new BibCentralImpl();
            central.setNome(nome);
            
            Naming.rebind("rmi://localhost:1099/BibCentral", central);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server("Central");
    }
}
