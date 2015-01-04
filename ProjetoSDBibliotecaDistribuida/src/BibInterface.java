
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BibInterface extends Remote {

    public int consultarQtdLivros(int matricula)
            throws RemoteException;

    public Aluno cadastrarAluno(String nome, String setorial)
            throws RemoteException;

    public void atualizar(int qtdLivros, int matricula, boolean atualizarSetorialCadastro)
            throws RemoteException;
}
