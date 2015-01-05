
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BibInterface extends Remote {

    public int consultarQtdLivros(int matricula)
            throws RemoteException;

    public Aluno cadastrarAluno(String nome, String setorial, String host, String porta)
            throws RemoteException;

    public void atualizar(int qtdLivros, int matricula, ModoAtualizacao modo)
            throws RemoteException;
}
