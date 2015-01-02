
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author lourivaldo
 */
public abstract class Biblioteca extends UnicastRemoteObject
        implements BibInterface {

    private String nome;
    protected ArrayList<Aluno> alunos;

    public Biblioteca() throws RemoteException {
        this.alunos = new ArrayList();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Aluno consultarAluno(String matricula)
            throws RemoteException {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    @Override
    public void cadastrarAluno(Aluno aluno)
            throws RemoteException {
        this.alunos.add(aluno);
    }

}
