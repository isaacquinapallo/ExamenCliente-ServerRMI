package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMI extends Remote {
    String registerUser(String fullName, String email) throws RemoteException;
    String getUsernameByEmail(String email) throws RemoteException;
}
