package br.com.sd.rmi.devmediateste;

import java.rmi.Naming;
import java.rmi.RemoteException; /* Classname: HelloClient Comment: The RMI client. */


public class HelloClient {

    static String message = "blank"; // The Hello object "obj" is the identifier that is // the Hello interface. 
    static Hello obj = null;

    public static void main(String args[]) {
        try {
            obj = (Hello) Naming.lookup("//" + "kvist.cs.umu.se" + "/Hello");
            message = obj.Hello();
            System.out.println("Mensagem no servidor RMI de: \"" + message + "\"");
        } catch (Exception e) {
            System.out.println("HelloClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
