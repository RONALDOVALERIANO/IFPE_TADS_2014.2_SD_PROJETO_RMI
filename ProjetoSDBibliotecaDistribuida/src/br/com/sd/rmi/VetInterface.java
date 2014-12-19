package br.com.sd.rmi;

public interface VetInterface extends java.rmi.Remote {

    public void reset() throws java.rmi.RemoteException;

    public void setInt(int pos, int val)
            throws java.rmi.RemoteException;

    public int getInt(int pos)
            throws java.rmi.RemoteException;

    public int soma(int a, int b)
            throws java.rmi.RemoteException;
}
