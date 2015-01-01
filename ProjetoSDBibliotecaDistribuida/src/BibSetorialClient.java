
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class BibSetorialClient {

    private ArrayList<Aluno> alunos;
    private String nome;
    private String hostCentral;
    private String porta;
    private String nomeServico;
    private BibInterface bibCentral;

    public BibSetorialClient(String nome, String hostCentral, String nomeServico) {
        this.nome = nome;
        this.hostCentral = hostCentral;
        this.nomeServico = nomeServico;
        this.alunos = new ArrayList();
    }

    public void connect() {
        try {
            bibCentral = (BibInterface) Naming.lookup("rmi://" + this.hostCentral + ":" + this.porta + "/" + this.nomeServico);
        } catch (Exception ex) {
            System.out.println("Setorial " + getNome() + ": Não foi conectar-se com a central!\nTente novamente mais tarde.");
            ex.printStackTrace();
        }
    }

    public Aluno consultarAluno(String matricula) {
        //procurar aluno na lista local
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        //procurar aluno na central
        try {
            return bibCentral.consultarAluno(matricula);
        } catch (RemoteException ex) {
            System.out.println("Setorial " + this.getNome() + ": Não foi consultar o aluno na central!\nTente reconectar.");
            ex.printStackTrace();
        }

        return null;
    }

    public void cadastrarAluno(String nome, String matricula) {
        //testar se ja foi cadastrado
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        this.alunos.add(aluno);

        //replicar
        try {
            this.bibCentral.cadastrarAluno(aluno);
        } catch (RemoteException ex) {
            System.out.println("Setorial " + this.getNome() + ": Não foi replicar o aluno na central!\nTente reconectar.");
            ex.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHostCentral() {
        return hostCentral;
    }

    public void setHostCentral(String hostCentral) {
        this.hostCentral = hostCentral;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public static void main(String[] args) {
        BibSetorialClient setorialA = new BibSetorialClient("A", "", "");
        BibSetorialClient setorialB = new BibSetorialClient("B", "", "");

        setorialA.connect();
        setorialB.connect();

        setorialA.cadastrarAluno("Loro da A", "1234");
        setorialB.cadastrarAluno("Joao da B", "5678");

        System.out.println(setorialA.consultarAluno("1234").getNome());
        System.out.println(setorialA.consultarAluno("5678").getNome());

    }
}
