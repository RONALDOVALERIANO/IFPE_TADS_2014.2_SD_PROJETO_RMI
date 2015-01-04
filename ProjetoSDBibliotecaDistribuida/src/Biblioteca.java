
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lourivaldo
 */
public abstract class Biblioteca extends UnicastRemoteObject
        implements BibInterface {

    protected String nome;
    protected Map<Integer, Aluno> alunos;
    protected int geradorMatricula;

    public Biblioteca() throws RemoteException {
        this.alunos = new HashMap();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BibInterface conectar(String host, String porta, String nomeServico) {
        try {
            return (BibInterface) Naming.lookup("rmi://" + host + ":" + porta + "/" + nomeServico);
        } catch (Exception ex) {
            System.out.println("A biblioteca " + getNome() + ": Não foi conectar-se com o " + nomeServico + "!\nTente novamente mais tarde.");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Aluno cadastrarAluno(String nome, String setorial) throws RemoteException {
        Aluno aluno = new Aluno();
        
        aluno.setMatricula(++geradorMatricula);
        aluno.setNome(nome);
        aluno.setSetorial(setorial);
        
        this.alunos.put(geradorMatricula, aluno);
        
        return aluno;
    }

    @Override
    public int consultarQtdLivros(int matricula) throws RemoteException, IllegalArgumentException {

        Aluno aluno = this.alunos.get(matricula);

        if (aluno != null) {
            return aluno.getQtdLivros();
        }

        throw new IllegalArgumentException("Matrícula Não Consta Na Base De Dados!");
    }

    @Override
    public void atualizar(int qtdLivros, int matricula, boolean setorialCadastro) throws RemoteException, IllegalArgumentException {

        if (setorialCadastro) {
            Aluno aluno = this.alunos.get(matricula);

            if (aluno != null) {
                aluno.setQtdLivros(aluno.getQtdLivros() + qtdLivros);
            } else {
                throw new IllegalArgumentException("Matrícula Não Consta Na Base De Dados!");
            }
        }
    }

}
