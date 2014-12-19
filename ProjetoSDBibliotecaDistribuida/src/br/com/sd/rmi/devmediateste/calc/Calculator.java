package br.com.sd.rmi.devmediateste.calc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

    public long add(long a, long b)
            throws RemoteException;
}
