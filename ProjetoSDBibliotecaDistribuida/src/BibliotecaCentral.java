
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
        
        ModoAtualizacao ma = ModoAtualizacao.NESTA_SETORIAL;
        if (ma == ModoAtualizacao.NESTA_SETORIAL) {
            super.atualizar(qtdLivros, matricula, modo);
        } else if (ma == ModoAtualizacao.OUTRA_SETORIAL) {
            BibInterface bibSetorial = conectar("localhost", "1099", consultarAluno(matricula).getSetorial());
            bibSetorial.atualizar(qtdLivros, matricula, ModoAtualizacao.CENTRAL);
            super.atualizar(qtdLivros, matricula, ModoAtualizacao.NESTA_SETORIAL);
        }

    }

}
