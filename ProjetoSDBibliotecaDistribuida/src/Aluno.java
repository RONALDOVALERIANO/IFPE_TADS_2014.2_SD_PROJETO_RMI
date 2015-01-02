
import java.io.Serializable;

public class Aluno implements Serializable {

    private String matricula;
    private String nome;
    private int qtdLivros;
    private String setorial;

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getQtdLivros() {
        return this.qtdLivros;
    }

    public void addLivro() {
        this.qtdLivros++;
    }

    public String getSetorial() {
        return setorial;
    }

    public void setSetorial(String setorial) {
        this.setorial = setorial;
    }

}
