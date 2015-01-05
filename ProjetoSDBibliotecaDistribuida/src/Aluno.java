
import java.io.Serializable;

public class Aluno implements Serializable {

    private int matricula;
    private String nome;
    private int qtdLivros;
    private String setorial;//nome servico
    private String host;
    private String porta;

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getQtdLivros() {
        return this.qtdLivros;
    }

    public void setQtdLivros(int qtdLivros) {
        this.qtdLivros = qtdLivros;
    }

    public String getSetorial() {
        return setorial;
    }

    public void setSetorial(String setorial) {
        this.setorial = setorial;
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
    
    

}
