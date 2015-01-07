
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lourivaldo
 */
public class MainCentral {

    public static void main(String[] args) {
        BibliotecaCentral central;
        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Dados desta Central:");
            System.out.println("Digite o nome do serviço:");
            String servico = scanner.next();
            System.out.println("Digite o ip:");
            String ip = scanner.next();
            System.out.println("Digite a porta:");
            String porta = scanner.next();

            central = new BibliotecaCentral();
            central.setNome(servico);
            central.setHost(ip);
            central.setPorta(porta);
            
            central.iniciarRMI();
            
            System.out.println("Central estabelecida!");
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }
}
