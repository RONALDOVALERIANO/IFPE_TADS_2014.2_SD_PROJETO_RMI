
import java.rmi.RemoteException;

/**
 *
 * @author lourivaldo
 */
public class BibliotecaSetorial extends Biblioteca implements BibInterface{

    private BibInterface bibCentral;
    private static int qtdLimiteEmprestimo = 3;

    public BibliotecaSetorial() throws RemoteException {
        bibCentral = conectar("localhost", "1099", "Central");
    }

    public void setBibCentral(BibInterface bibCentral) {
        this.bibCentral = bibCentral;
    }

    public static int getQtdLimiteEmprestimo() {
        return qtdLimiteEmprestimo;
    }

    public static void setQtdLimiteEmprestimo(int qtdLimiteEmprestimo) {
        BibliotecaSetorial.qtdLimiteEmprestimo = qtdLimiteEmprestimo;
    }

    public void conectarComCentral(String host, String porta, String nomeServico) {
        this.bibCentral = super.conectar(host, porta, nomeServico);
    }

    public void cadastrar(String nome, String setorial)  {
        //cadastra na central
        Aluno aluno;
        try {
            aluno = bibCentral.cadastrarAluno(nome, setorial);
            //espera confirmacao depois atualiza a setorial
            super.alunos.put(aluno.getMatricula(), aluno);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void atualizar(int qtdLivros, int matricula, boolean setorialCadastro) 
            throws RemoteException, IllegalArgumentException {
        //espera confirmacao depois atualiza a setorial
        //atualizar na central
        bibCentral.atualizar(qtdLivros, matricula, setorialCadastro);
        if (setorialCadastro) {
            super.atualizar(qtdLivros, matricula, setorialCadastro);
        }
        
    }

    @Override
    public int consultarQtdLivros(int matricula) throws RemoteException {

        int qtdLivros = 0;

        try {
            qtdLivros = super.consultarQtdLivros(matricula);//se nao encontrar na lista local
        } catch (IllegalArgumentException ex1) {
            //procurar na central
            try {
                qtdLivros = bibCentral.consultarQtdLivros(matricula);
            } catch (IllegalArgumentException ex2) {
                System.out.println(ex2.getMessage());
            }
        }

        return qtdLivros;
    }

    public void emprestar(int qtdEmprestimo, int matricula) {
        try {
            int qtdDisponivel = (BibliotecaSetorial.qtdLimiteEmprestimo - this.consultarQtdLivros(matricula));

            if (qtdDisponivel > 0 && qtdEmprestimo <= qtdDisponivel) {
//                #######Problema nao encontra matricula - Resolvido
                //atualiza 2 vezes (local-passagem por referencia) - resolvido com rmi - passagem por copia
                this.atualizar(qtdEmprestimo, matricula, true);
            } else {
                throw new RuntimeException("Aluno não pode realizar emprestimo! Disponível " + qtdDisponivel + " emprestimo");
            }
        } catch (RemoteException ex) {
            System.out.println("Não foi possivel se conectar");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            System.out.println("Matricula inexistente");
        }

    }

}
