package Test;

import Servidor.ServidorRMI;
import Servidor.InterfaceRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Test {
    public static void main(String[] args) {
        try{
            InterfaceRMI interfaceRMI = new ServidorRMI();
            LocateRegistry.createRegistry(1099);
            String rmiObjecName = "rmi://172.31.116.89/Datos";
            Naming.rebind(rmiObjecName, interfaceRMI);
            System.out.println("Servidor conectado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
