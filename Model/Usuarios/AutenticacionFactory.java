/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuarios;

import java.sql.Connection;
import Model.DAO.Autenticacion;

/**
 *
 * @author sofia
 */
public class AutenticacionFactory {

    public static Autenticacion getAutenticacion(String tipo, Connection connection) {
        if (tipo.equals("BASE_DE_DATOS")) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            return new AutenticacionBD(usuarioDAO);
        }
        // Se pueden agregar más tipos de autenticación si es necesario (p. ej., autenticación externa)
        return null;
    }
}
