
import java.rmi.RemoteException;


/**
 *
 * @author lourivaldo
 */
public class BibliotecaSetorial extends Biblioteca {

    @Override
    public Aluno consultarAluno(String matricula) throws RemoteException {
        
    }

    @Override
    public void cadastrarAluno(Aluno aluno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Aluno aluno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
