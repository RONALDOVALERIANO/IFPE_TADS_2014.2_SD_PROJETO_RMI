
import java.rmi.RemoteException;

/**
 *
 * @author lourivaldo
 */
public class BibliotecaSetorial extends Biblioteca implements BibInterface {

    private BibInterface bibCentral;
    private static int qtdLimiteEmprestimo = 3;

    public BibliotecaSetorial() throws RemoteException {
        
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

    public void cadastrar(String nome) {
        //cadastra na central
        Aluno aluno;
        try {
            aluno = bibCentral.cadastrarAluno(nome, super.nome, super.host, super.porta);
            //espera confirmacao depois atualiza a setorial
            super.alunos.put(aluno.getMatricula(), aluno);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void atualizar(int qtdLivros, int matricula, ModoAtualizacao modo)
            throws RemoteException, IllegalArgumentException {
        /*
         1- Cadastrado nesta setorial - Atualizar central e local
         2- Cadastrado outra setorial - Atualizar central->Central atualiza setorial (3)
         3- Central atualizar setorial
         */

        if (modo == ModoAtualizacao.NESTA_SETORIAL) {
            bibCentral.atualizar(qtdLivros, matricula, modo);
            super.atualizar(qtdLivros, matricula, modo);
        } else if (modo == ModoAtualizacao.OUTRA_SETORIAL) {
            bibCentral.atualizar(qtdLivros, matricula, modo);
        } else if (modo == ModoAtualizacao.CENTRAL) {
            super.atualizar(qtdLivros, matricula, ModoAtualizacao.NESTA_SETORIAL);
            System.out.println("ModoAtualizacao.CENTRAL");
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
//                #######Como saber se o aluno é da setorial?
                //Problema nao encontra matricula - Resolvido
                //atualiza 2 vezes (local-passagem por referencia) - resolvido com rmi - passagem por copia
                try {
                    super.consultarAluno(matricula);
                    this.atualizar(qtdEmprestimo, matricula, ModoAtualizacao.NESTA_SETORIAL);
                } catch (IllegalArgumentException e) {
                    this.atualizar(qtdEmprestimo, matricula, ModoAtualizacao.OUTRA_SETORIAL);
                }
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
