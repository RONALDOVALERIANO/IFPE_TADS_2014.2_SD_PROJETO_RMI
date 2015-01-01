
import java.rmi.Naming;

public class CalculatorServer {

    CalculatorServer() {
        try {
            Calculator c = new CalculatorImple();
            Naming.rebind("rmi://localhost:1099/CalculatorService", c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CalculatorServer();
    }
}
