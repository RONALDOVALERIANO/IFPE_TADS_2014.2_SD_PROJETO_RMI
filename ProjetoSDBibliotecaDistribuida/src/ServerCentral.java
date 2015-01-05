
import java.rmi.Naming;

/**
 *
 * @author Lourivaldo
 */
public class ServerCentral {

    public ServerCentral(String host, String porta, String nomeServico) {
        try {
            BibliotecaCentral bib = new BibliotecaCentral();
            bib.setNome(nomeServico);
            bib.setHost(host);
            bib.setPorta(porta);
            
            Naming.rebind("rmi://" + host + ":" + porta + "/" + nomeServico, bib);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
