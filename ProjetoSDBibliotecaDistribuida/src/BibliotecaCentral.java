
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lourivaldo
 */
public class BibliotecaCentral extends Biblioteca implements BibInterface {

    protected Map<String, BibInterface> setoriais;

    public BibliotecaCentral() throws RemoteException {
        this.setoriais = new HashMap();
    }

    @Override
    public void atualizar(int qtdLivros, int matricula, boolean setorialCadastro) throws RemoteException {
        if (setorialCadastro) {
            super.atualizar(qtdLivros, matricula, true);
        } else {
            //atualizar /procurar setorial
        }
    }

//    @Override
//    public Aluno cadastrarAluno(String nome, String setorial) throws RemoteException {
//        return super.cadastrarAluno(nome, setorial);
//    }

}
