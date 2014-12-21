
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BibliotecaSetorialClient {

    private String nome;
    private String hostCentral;
    private String nomeServico;
    private ArrayList<Aluno> alunos;
    private BibliotecaInterface bibCentral;

    public BibliotecaSetorialClient(String nome, String hostCentral, String nomeServico) {
        this.nome = nome;
        this.hostCentral = hostCentral;
        this.nomeServico = nomeServico;
        this.alunos = new ArrayList<>();
        connect();
    }

    public void connect() {
        try {
            bibCentral = (BibliotecaInterface) Naming.lookup("rmi://localhost:1099/BibCentral");
        } catch (Exception e) {
            System.out.println("Setorial " + getNome() + ": Não foi comunicar-se com a central!\nTente novamente mais tarde.");
            e.printStackTrace();
        }
    }

    public Aluno consultarAluno(String matricula) {
        //procurar aluno na lista local
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == matricula) {
                return aluno;
            }
        }
        //na central
        try {
            return bibCentral.consultarAluno(matricula);
        } catch (RemoteException ex) {
            System.out.println("Setorial " + this.getNome() + ": Não foi comunicar-se com a central!\nTente novamente mais tarde.");
            ex.printStackTrace();
        }

        return null;
    }

    public void cadastrarAluno(String nome, String matricula) {
        //testar se ja foi cadastrado
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setMatricula(matricula);
        aluno.setBiSetorial(this);
        this.alunos.add(aluno);
        try {
            //replicar
            this.bibCentral.cadastrarAluno(aluno);
        } catch (RemoteException ex) {
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
        BibliotecaSetorialClient bib = new BibliotecaSetorialClient("Setorial A", "localhost", "BibCentral");
        bib.cadastrarAluno("Loro", "2013");
        Aluno aluno = bib.consultarAluno("2013");
        System.out.println("Main"+aluno.getNome());
    }
}
