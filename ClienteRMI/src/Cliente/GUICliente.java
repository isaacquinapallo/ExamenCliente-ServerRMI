package Cliente;

import Servidor.InterfaceRMI;

import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;

public class GUICliente extends JFrame {

    private InterfaceRMI servidor;

    public GUICliente() {
        // Conectamos con el servidor RMI
        try {
            servidor = (InterfaceRMI) Naming.lookup("rmi://172.31.116.89/Datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor RMI:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Configurar la GUI
        setTitle("Cliente RMI - Registro de Usuarios");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Botones
        JButton btnRegistrar = new JButton("Registrar Usuario");
        JButton btnBuscar = new JButton("Buscar Nombre de Usuario");
        JButton btnSalir = new JButton("Salir");

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnBuscar.addActionListener(e -> buscarUsuario());
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones a la ventana
        add(btnRegistrar);
        add(btnBuscar);
        add(btnSalir);

        setVisible(true);
    }

    private void registrarUsuario() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre completo:");
        if (nombre == null || nombre.isEmpty()) return;

        String correo = JOptionPane.showInputDialog(this, "Ingrese su correo electrónico:");
        if (correo == null || correo.isEmpty()) return;

        try {
            String respuesta = servidor.registerUser(nombre, correo);
            JOptionPane.showMessageDialog(this, respuesta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarUsuario() {
        String correo = JOptionPane.showInputDialog(this, "Ingrese su correo electrónico:");
        if (correo == null || correo.isEmpty()) return;

        try {
            String username = servidor.getUsernameByEmail(correo);
            JOptionPane.showMessageDialog(this, "Resultado:\n" + username);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
