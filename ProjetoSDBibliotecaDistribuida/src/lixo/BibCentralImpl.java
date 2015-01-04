
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Lourivaldo
 */
public class BibCentralImpl extends UnicastRemoteObject
        implements BibInterface {

    private String nome;
    protected ArrayList<Aluno> alunos;
//    protected ArrayList<BibSetorialClient> setoriais;

    public BibCentralImpl() throws RemoteException {
        this.alunos = new ArrayList<>();
//        this.setoriais = new ArrayList<>();
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

    @Override
    public void atualizar(Aluno a)
            throws RemoteException {
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            if (aluno.getMatricula().equals(a.getMatricula())) {
                alunos.remove(i);
                alunos.add(a);
                break;
            }
        }
    }
}
