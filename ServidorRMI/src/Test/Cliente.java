package Test;

import Servidor.InterfaceRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Usar el mismo puerto que el servidor (1099)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // El nombre que registraste fue "Datos", no necesitas "rmi://"
            InterfaceRMI service = (InterfaceRMI) registry.lookup("Datos");

            // Registrar usuario
            String result = service.registerUser("Juan Pérez", "juan@example.com");
            System.out.println(result);

            // Buscar nombre de usuario por correo
            String username = service.getUsernameByEmail("juan@example.com");
            System.out.println("Nombre de usuario: " + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
