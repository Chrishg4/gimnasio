/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuarios.LoginService;
import java.sql.Connection;
/**
 *
 * @author sofia
 */
public class LoginController {

    private LoginService loginService;

    public LoginController(Connection connection) {
        this.loginService = new LoginService(connection);
    }

    public boolean autenticarUsuario(String username, String password) {
        return loginService.login(username, password);
    }
}
