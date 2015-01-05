
import java.rmi.Naming;

/**
 *
 * @author Lourivaldo
 */
public class Server {

    public Server(String nome) {
        try {
            BibliotecaCentral central = new BibliotecaCentral();
            central.setNome(nome);
            
            Naming.rebind("rmi://localhost:1099/Central", central);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server("CentralNome");
    }
}
