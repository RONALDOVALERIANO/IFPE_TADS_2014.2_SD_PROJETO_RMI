
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lourivaldo
 */
public class MainSetorial {

    private static BibliotecaSetorial setorial;
    private static Scanner scanner = new Scanner(System.in);

    public static int menu() {
        System.out.println("##############################");
        System.out.println("Digite:");
        System.out.println("0-Cadastrar Aluno");
        System.out.println("1-Emprestar");
        System.out.println("2-Devolver");
        System.out.println("3-Listar Alunos desta setorial");
        System.out.println("4-Consultar debito Aluno");
        System.out.println("5-Sair");
        System.out.print("Opção: ");
        return scanner.nextInt();
        
    }

    public static void cadastrar() {
        System.out.println("Digite o nome do aluno:");
        String nome = scanner.next();
        setorial.cadastrar(nome);
    }

    public static void emprestar() {
        System.out.println("Emprestimo:");
        System.out.println("Quantos livros?");
        int qtdLivros = scanner.nextInt();
        System.out.println("Matricula do Aluno?");
        int matricula = scanner.nextInt();
        setorial.emprestar(qtdLivros, matricula);
    }

    public static void devolver() {
        System.out.println("Devolucao:");
        System.out.println("Quantos livros?");
        int qtdLivros = scanner.nextInt();
        System.out.println("Matricula do Aluno?");
        int matricula = scanner.nextInt();
        setorial.devolver(qtdLivros, matricula);
    }

    public static void listar() {
        Map<Integer, Aluno> alunos = setorial.getAlunos();

        System.out.println("Matricula\tNome\tQtd livros");

        for (Map.Entry<Integer, Aluno> aluno : alunos.entrySet()) {
            Integer key = aluno.getKey();
            Aluno value = aluno.getValue();
            System.out.println(value.getMatricula() + "\t" + value.getNome() + "\t" + value.getQtdLivros());

        }
    }

    public static void consultar() {
        System.out.println("Consulta debito do aluno:");
        System.out.println("Digite a matricula: ");
        int matricula = scanner.nextInt();
        try {
            int qtd = setorial.consultarQtdLivros(matricula);
            System.out.println("Deve " + qtd + " livros.");
        } catch (RemoteException ex) {
            System.out.println("Aluno nao cadastrado");
        }
    }

    public static void main(String[] args) {

        try {
            System.out.println("Dados desta setorial:");
            System.out.println("Digite o nome do serviço:");
//            String servico = scanner.next();
            System.out.println("Digite o ip:");
//            String ip = scanner.next();
            System.out.println("Digite a porta:");
//            String porta = scanner.next();

            setorial = new BibliotecaSetorial();
//            setorial.setNome(servico);
//            setorial.setHost(ip);
//            setorial.setPorta(porta);
            setorial.setNome("Setorial");
            setorial.setHost("localhost");
            setorial.setPorta("1099");

            setorial.iniciarRMI();

            System.out.println("Dados da Central:");
            System.out.println("Digite o nome do serviço:");
//            servico = scanner.next();
            System.out.println("Digite o ip:");
//            ip = scanner.next();
            System.out.println("Digite a porta:");
//            porta = scanner.next();

//            setorial.conectarComCentral(ip, porta, servico);
            setorial.conectarComCentral("localhost", "1099", "Central");

            int opcao;
            while (true) {

                System.out.println("##############################");
                
                opcao = menu();

                if (opcao == 5) {
                    break;
                }

                switch (opcao) {
                    case 0:
                        cadastrar();
                        break;
                    case 1:
                        emprestar();
                        break;
                    case 2:
                        devolver();
                        break;
                    case 3:
                        listar();
                        break;
                    case 4:
                        consultar();
                        break;
                    default:
                        System.out.println("Opção invalida!");
                }
            }

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        System.out.println("Saiu!");
    }

}
