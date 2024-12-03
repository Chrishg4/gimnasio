/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuarios;

import Model.DAO.DaoCRUD;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
/**
 *
 * @author chris
 */
public class UsuarioDAO extends DaoCRUD<UsuarioDTO> {

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean update(UsuarioDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call UsuarioUpdate(?,?,?,?,?,?)");
        stmt.setInt(1, dto.getId());
        
        stmt.setString(2, dto.getPassword());
        stmt.setString(3, dto.getNombre());
        stmt.setString(4, dto.getEmail());
        stmt.setString(5, dto.getRol().name());
        
        return stmt.executeUpdate() > 0;
    }

    @Override
    public boolean create(UsuarioDTO dto) throws SQLException {
          stmt = connection.prepareStatement("call UsuarioCreate(?,?,?,?,?,?)");
        stmt.setInt(1, dto.getId());
        
        stmt.setString(2, dto.getPassword());
        stmt.setString(3, dto.getNombre());
        stmt.setString(4, dto.getEmail());
        stmt.setString(5, dto.getRol().name());
        
        // Verifica si agrega a la bd los datos
        return stmt.executeUpdate() > 0;
    }

    @Override
    public UsuarioDTO read(Object username) throws SQLException {
          stmt = connection.prepareStatement("call UsuarioRead(?)");
        stmt.setString(1, String.valueOf(username));
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            return new UsuarioDTO(
                    rs.getInt(1),      // id
                    
                    rs.getString(2),   // password
                    rs.getString(3),   // nombre
                    rs.getString(4),   // email
                    Roles.valueOf(rs.getString(5))    // rol
            );
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> readAll() throws SQLException {
         stmt = connection.prepareStatement("call UsuarioReadAll()");
        rs = stmt.executeQuery();
        
        List<UsuarioDTO> dtos = new ArrayList<>();
        while (rs.next()) {
            dtos.add(new UsuarioDTO(
                    rs.getInt(1),      // id
                    
                    rs.getString(2),   // password
                    rs.getString(3),   // nombre
                    rs.getString(4),   // email
                    Roles.valueOf(rs.getString(5))    // rol
            ));
        }
        return dtos;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
         stmt = connection.prepareStatement("call UsuarioDelete(?)");
        stmt.setString(1, String.valueOf(id));
        return stmt.executeUpdate() > 0;
    }
    
}
