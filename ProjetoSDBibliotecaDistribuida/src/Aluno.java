
import java.io.Serializable;

public class Aluno implements Serializable {

    private String nome;
    private String matricula;
    private Integer qtdLivros;
    private BibliotecaSetorialClient biSetorial;

    public Aluno() {
        super();
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

    public Integer getQtdLivros() {
        return qtdLivros;
    }

    public void setQtdLivros(Integer qtdLivros) {
        this.qtdLivros = qtdLivros;
    }

    public BibliotecaSetorialClient getBiSetorial() {
        return biSetorial;
    }

    public void setBiSetorial(BibliotecaSetorialClient biSetorial) {
        this.biSetorial = biSetorial;
    }
    
    

}
