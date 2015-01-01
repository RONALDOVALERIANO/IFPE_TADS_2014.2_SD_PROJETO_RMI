
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BibInterface extends Remote {

    public Aluno consultarAluno(String matricula)
            throws RemoteException;

    public void cadastrarAluno(Aluno aluno)
            throws RemoteException;
    
    public void atualizar(Aluno aluno)
            throws RemoteException;
}
