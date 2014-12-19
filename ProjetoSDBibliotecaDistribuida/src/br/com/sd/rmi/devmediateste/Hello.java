package br.com.sd.rmi.devmediateste;

import java.rmi.Remote;
import java.rmi.RemoteException; /* Classname: Hello Comment: The remote interface. */


public interface Hello extends Remote {

    public String Hello()
            throws RemoteException;
}
