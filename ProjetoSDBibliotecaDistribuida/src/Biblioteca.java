
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public abstract class Biblioteca extends UnicastRemoteObject
        implements BibInterface {

    protected Map<Integer, Aluno> alunos;
    protected int geradorMatricula;
    //informaçoes do RMI
    protected String nome;
    protected String host;
    protected String porta;

    public Biblioteca() throws RemoteException {
        this.alunos = new HashMap();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
    
    public void iniciarRMI(){
        try {  
            Naming.rebind("rmi://" + this.host + ":" + this.porta + "/" + this.nome, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public Aluno cadastrarAluno(String nome, String setorial, String host, String porta) throws RemoteException {
        Aluno aluno = new Aluno();

        aluno.setMatricula(++geradorMatricula);
        aluno.setNome(nome);
        aluno.setSetorial(setorial);
        aluno.setHost(host);
        aluno.setPorta(porta);

        this.alunos.put(geradorMatricula, aluno);

        return aluno;
    }

    @Override
    public int consultarQtdLivros(int matricula) throws RemoteException, IllegalArgumentException {

        Aluno aluno = consultarAluno(matricula);

        if (aluno != null) {
            return aluno.getQtdLivros();
        }

        throw new IllegalArgumentException("Matrícula Não Consta Na Base De Dados!");
    }

    @Override
    public void atualizar(int qtdLivros, int matricula, ModoAtualizacao modo) throws RemoteException, IllegalArgumentException {

//        if (modo == ModoAtualizacao.NESTA_SETORIAL) {
//        System.out.println(modo.name());
        Aluno aluno = consultarAluno(matricula);

        if (aluno != null) {
            aluno.setQtdLivros(aluno.getQtdLivros() + qtdLivros);
        } else {
            throw new IllegalArgumentException("Matrícula Não Consta Na Base De Dados!");
        }
//        }
    }

    public Aluno consultarAluno(int matricula) {
        Aluno aluno = this.alunos.get(matricula);

        if (aluno == null) {
            throw new IllegalArgumentException("Matrícula Não Consta Na Base De Dados!");
        }

        return aluno;
    }

}
