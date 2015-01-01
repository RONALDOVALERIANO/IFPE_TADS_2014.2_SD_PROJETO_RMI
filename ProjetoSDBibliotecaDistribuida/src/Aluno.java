
import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable {

    private String matricula;
    private String nome;
    private ArrayList<Livro> livros;
    private String setorial;

    public Aluno() {
        livros = new ArrayList();
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
        return this.livros.size();
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void addLivro(Livro livro) {
        this.livros.add(livro);
    }

    public String getSetorial() {
        return setorial;
    }

    public void setSetorial(String setorial) {
        this.setorial = setorial;
    }

}
