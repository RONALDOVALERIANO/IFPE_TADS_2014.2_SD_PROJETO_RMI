package br.com.sd.rmi;

import java.rmi.*;

public class VetClient {

    public static void main(String[] arg) { //System.setSecurityManager(new RMISecurityManager());
        VetInterface v;
        try {
            v = (VetInterface) Naming.lookup("rmi://10.10.0.65:1099/Vet10");
            // v.setInt(4, 45);
            System.out.println("Valor = " + v.getInt(4));

            System.out.println("sdgs");
        } catch (Exception e) { /*...*/ }
    }
}
