
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 *
 * @author Lourivaldo
 */
public class Main {

    public static void main(String[] args) {
        int matricula = 1;
        try {
//            BibliotecaCentral central = new BibliotecaCentral();

//Setorial A
            BibliotecaSetorial setorialA = null;
            try {
                setorialA = new BibliotecaSetorial();
                Naming.rebind("rmi://localhost:1099/SetorialA", setorialA);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            BibliotecaSetorial setorialA = new BibliotecaSetorial();
            setorialA.setNome("SetorialA");
//            setorialA.setBibCentral(central);

            setorialA.cadastrar("Loro", setorialA.getNome());
            System.out.println(setorialA.consultarQtdLivros(matricula));
            setorialA.emprestar(1, matricula);
//            setorialA.emprestar(1, matricula);
            System.out.println(setorialA.consultarQtdLivros(matricula));

            
BibliotecaSetorial setorialB = null;
            try {
                setorialB = new BibliotecaSetorial();
                Naming.rebind("rmi://localhost:1099/SetorialB", setorialB);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            BibliotecaSetorial setorialB = new BibliotecaSetorial();
            setorialB.setNome("SetorialB");
//            setorialB.setBibCentral(central);

            setorialB.cadastrar("Joao", setorialB.getNome());
            System.out.println(setorialB.consultarQtdLivros(matricula));
            setorialB.emprestar(1, matricula);
//            setorialB.emprestar(1, matricula);
            System.out.println(setorialB.consultarQtdLivros(matricula));
            
            
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
