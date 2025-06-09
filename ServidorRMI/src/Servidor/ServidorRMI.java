package Servidor;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorRMI extends UnicastRemoteObject implements InterfaceRMI {

    private final Map<String, String> emailToUsername;

    public ServidorRMI() throws RemoteException {
        super();
        emailToUsername = new ConcurrentHashMap<>();
    }

    @Override
    public String registerUser(String fullName, String email) throws RemoteException {

        if (emailToUsername.containsKey(email)) {
            return "El correo ya está registrado con el usuario: " + emailToUsername.get(email);
        }

        // Generar nombre de usuario único
        String username;
        do {
            username = generateRandomUsername();
        } while (emailToUsername.containsValue(username));

        // Insertar en el mapa
        emailToUsername.put(email, username);
        System.out.println("Usuario registrado exitosamente. Nombre de usuario generado: " + username);
        return "Usuario registrado exitosamente. Nombre de usuario generado: " + username;
    }

    @Override
    public String getUsernameByEmail(String email) throws RemoteException {
        return emailToUsername.getOrDefault(email, "No se encontró ningún usuario con ese correo.");
    }

    private String generateRandomUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
