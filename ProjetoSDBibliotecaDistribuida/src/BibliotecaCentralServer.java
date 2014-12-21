
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Lourivaldo
 */
public class BibliotecaCentralServer {

    public BibliotecaCentralServer(String nome) {
        try {
            Naming.rebind("BibCentral", new BibliotecaImpl(nome));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BibliotecaCentralServer("Central");
    }
}
