/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
//import Model.Usuarios.Autenticacion;
import Model.Usuarios.Usuario;
import Model.Usuarios.UsuarioDAO;
import Model.Usuarios.UsuarioDTO;
import Model.Usuarios.UsuarioMapper;
import javax.swing.text.View;
import javax.swing.text.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 *
 * @author chris
 */
public class UsuarioController {
 private UsuarioDAO usuarioDAO;
    private final View vista;
    private final UsuarioMapper mapper;
    private Usuario usuarioActual; // Para guardar el usuario autenticado

    public UsuarioController(View vista) {
        this.vista = vista;
        mapper = new UsuarioMapper();
        try {
            usuarioDAO = new UsuarioDAO(Database.getInstance().getConnection());
        } catch (SQLException ex) {
            this.usuarioDAO = null;
            vista.showError("Error al conectar con la base de datos: " + ex.getMessage());
        }
    }

    // Método para crear un nuevo usuario
    public void create(Usuario usuario) {
        if (usuarioDAO == null) {
            vista.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (usuario == null || !validarDatosRequeridos(usuario)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }

        try {
            usuarioDAO.create(mapper.toDto(usuario));
            vista.showError("Usuario creado correctamente.");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al crear el usuario: " + ex.getMessage());
        }
    }

    // Método para leer un usuario por ID
    public void read(int id) {
        if (usuarioDAO == null) {
            vista.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            UsuarioDTO dto = usuarioDAO.read(id);
            if (dto == null) {
                vista.showError("No se encontró el usuario con el ID especificado.");
                return;
            }
            Usuario usuario = mapper.toEntity(dto);
            vista.showError(usuario);
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al leer el usuario: " + ex.getMessage());
        }
    }

    // Método para leer todos los usuarios
    public void readAll() {
        if (usuarioDAO == null) {
            vista.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            List<UsuarioDTO> dtoList = usuarioDAO.readAll();
            List<Usuario> usuarioList = dtoList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
            vista.showError(usuarioList);
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al leer los usuarios: " + ex.getMessage());
        }
    }

    // Método para actualizar un usuario
    public void update(Usuario usuario) {
        if (usuarioDAO == null) {
            vista.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (usuario == null || !validarDatosRequeridos(usuario)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }

        try {
            usuarioDAO.update(mapper.toDto(usuario));
            vista.showError("Usuario actualizado correctamente.");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al actualizar el usuario: " + ex.getMessage());
        }
    }

    // Método para eliminar un usuario
    public void delete(int id) {
        if (usuarioDAO == null) {
            vista.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            usuarioDAO.delete(id);
            vista.showError("Usuario eliminado correctamente.");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al eliminar el usuario: " + ex.getMessage());
        }
    }

    // Método para autenticar un usuario dentro del controlador
    public void iniciarSesion(String email, String contraseña) {
        if (usuarioDAO == null) {
            vista.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            UsuarioDTO dto = usuarioDAO.readAll().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(contraseña))
                .findFirst()
                .orElse(null);

            if (dto != null) {
                this.usuarioActual = mapper.toEntity(dto); // Guardamos el usuario autenticado
                vista.showError("Inicio de sesión exitoso.");
            } else {
                vista.showError("Credenciales inválidas.");
            }
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al iniciar sesión: " + ex.getMessage());
        }
    }

    // Método para cerrar sesión
    public void cerrarSesion() {
        this.usuarioActual = null;
        vista.showError("Sesión cerrada correctamente.");
    }

    // Método para validar si hay un usuario autenticado actualmente
    public boolean estaAutenticado() {
        return this.usuarioActual != null;
    }

    // Validación de datos requeridos para el usuario
    private boolean validarDatosRequeridos(Usuario usuario) {
        return usuario != null
            && !usuario.getEmail().trim().isEmpty()
            && !usuario.getPassword().trim().isEmpty()
            && !usuario.getNombre().trim().isEmpty()
            && !usuario.getEmail().trim().isEmpty()
            && usuario.getRol() != null;  // Verificar que el enum Rol no sea nulo
    }

}
