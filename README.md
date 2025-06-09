# Sistema de Registro de Usuarios - Java RMI + Docker

Este proyecto implementa un sistema distribuido de registro de usuarios utilizando **Java RMI**.  
El servidor y cliente se comunican por medio de RMI y permiten registrar usuarios y consultar su nombre de usuario mediante correo electrónico.

---

## Estructura del Proyecto

```
📁  ClienteRMI/
│   ├── src/Cliente/ClienteRMI.java
│   ├── src/Cliente/GUICliente.java
│   └── src/Servidor/InterfaceRMI.java

📁  ServidorRMI/
│   ├── src/Servidor/InterfaceRMI.java
│   ├── src/Servidor/ServidorImpl.java
│   └── src/Test/Test.java
```

---

## 📁 Backend - Servidor RMI

### Carpeta: `ServidorRMI`

### Funcionalidades
- Registrar usuarios: Genera un nombre de usuario aleatorio y único.
- Consultar nombre de usuario a partir de un correo electrónico.
- Persistencia en memoria con `ConcurrentHashMap`.

### Lógica Principal
```java
String registerUser(String fullName, String email);
String getUsernameByEmail(String email);
```

### Código Clave
```java
Servidor servidor = new ServidorImpl();
LocateRegistry.createRegistry(1099);
Naming.rebind("rmi://localhost/Datos", servidor);
```

---

## 📁 Frontend - Cliente con GUI

### Carpeta: `ClienteRMI`

### Funcionalidades

- Menú con tres opciones:
  -  Registro de usuario (nombre y correo)
  -  Buscar usuario por correo
  -  Salir

###  Interfaz (Swing)
```java
JButton btnRegistrar = new JButton("Registrar Usuario");
JButton btnBuscar = new JButton("Buscar Nombre de Usuario");
JButton btnSalir = new JButton("Salir");
```

###  Lógica RMI
```java
InterfaceRMI servidor = (InterfaceRMI) Naming.lookup("rmi://172.31.116.89/Datos");
```

---

##  Dockerización

>  Asegúrate de que tu archivo `.Dockerfile` esté bien configurado para el servidor. Puedes usar una imagen base como `openjdk:22`.

```dockerfile
FROM openjdk:22
COPY . /app
WORKDIR /app
CMD ["java", "Test.Test"]
```

---

## Capturas de Funcionamiento

Coloca tus capturas aquí:

### Inicio del servidor
![Imagen de WhatsApp 2025-06-09 a las 16 10 27_935a9477](https://github.com/user-attachments/assets/d235c7c4-6b86-4fde-978a-88275306fcc7)


### Cliente GUI
![image](https://github.com/user-attachments/assets/b1ee0bc6-b729-407c-bc00-9147e453f33e)


### Registro exitoso
![image](https://github.com/user-attachments/assets/635506e0-fad2-4730-ad1b-12945071e031)

![image](https://github.com/user-attachments/assets/12ad33d8-1443-4c3a-b07a-9e796a6004ec)

![image](https://github.com/user-attachments/assets/f4d0b87b-f3f0-48c8-b5e2-d807149191d2)


### Búsqueda
![image](https://github.com/user-attachments/assets/69597129-b740-4804-a70c-f734e6819f70)

![image](https://github.com/user-attachments/assets/43d7b8bc-95e3-4ff7-a361-1d96752a1136)


---

## Requisitos

- Java 17+ (preferiblemente Java 22)
- Docker (opcional para deploy)
- IntelliJ IDEA o cualquier IDE Java
- Git

---

## Cómo Ejecutar

### Backend
```bash
cd ServidorRMI
javac -d . src/Servidor/*.java src/Test/Test.java
java Test.Test
```

### Frontend
```bash
cd ClienteRMI
javac -d . src/Cliente/*.java src/Servidor/InterfaceRMI.java
java Cliente.ClienteRMI
```

---

## Créditos

> Desarrollado por **Isaac Quinapallo y Alejandro Guitierrez**  
> Escuela Politécnica Nacional

---
