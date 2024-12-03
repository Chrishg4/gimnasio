/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuarios;

import java.sql.SQLException;

import Model.DAO.Autenticacion;

/**
 *
 * @author sofia
 */
public class AutenticacionBD implements Autenticacion {

    private UsuarioDAO usuarioDAO;

    public AutenticacionBD(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public boolean autenticar(String username, String password) {
        try {
            UsuarioDTO usuarioDTO = usuarioDAO.read(username);
            if (usuarioDTO != null && usuarioDTO.getPassword().equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
