/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuarios;
import Model.DAO.Autenticacion;
import java.sql.Connection;
/**
 *
 * @author sofia
 */
public class LoginService {
     private Autenticacion autenticacion;

    public LoginService(Connection connection) {
        this.autenticacion = AutenticacionFactory.getAutenticacion("BASE_DE_DATOS", connection);
    }

    public boolean login(String username, String password) {
        return autenticacion.autenticar(username, password);
    }
}
