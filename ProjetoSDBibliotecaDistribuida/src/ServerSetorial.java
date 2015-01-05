
import java.rmi.Naming;

/**
 *
 * @author Lourivaldo
 */
public class ServerSetorial {

    public ServerSetorial(String host, String porta, String nomeServico) {
        try {
            BibliotecaSetorial bib = new BibliotecaSetorial();
            bib.setNome(nomeServico);
            bib.setHost(host);
            bib.setPorta(porta);
            
            Naming.rebind("rmi://" + host + ":" + porta + "/" + nomeServico, bib);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
