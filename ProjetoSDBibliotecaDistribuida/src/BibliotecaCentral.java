
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lourivaldo
 */
public class BibliotecaCentral extends Biblioteca implements BibInterface {

    protected Map<String, BibInterface> setoriais;

    public BibliotecaCentral() throws RemoteException {
        this.setoriais = new HashMap();
    }

    @Override
    public void atualizar(int qtdLivros, int matricula, ModoAtualizacao modo) throws RemoteException {
        /*
         1- Atualizar central
         2- Atualizar central e atualiza setorial 
         */

        if (modo == ModoAtualizacao.NESTA_SETORIAL) {
            super.atualizar(qtdLivros, matricula, modo);
        } else if (modo == ModoAtualizacao.OUTRA_SETORIAL) {
            Aluno aluno = super.consultarAluno(matricula);
            BibInterface bibSetorial = super.conectar(aluno.getHost(), aluno.getPorta(), aluno.getSetorial());
            bibSetorial.atualizar(qtdLivros, matricula, ModoAtualizacao.CENTRAL);
            super.atualizar(qtdLivros, matricula, ModoAtualizacao.NESTA_SETORIAL);
        }

    }

}
