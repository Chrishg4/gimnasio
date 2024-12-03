/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entrenadores;

import Model.DAO.DaoCRUD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sofia
 */
public class EntrenadorDAO extends DaoCRUD<EntrenadorDTO> {

    public EntrenadorDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(EntrenadorDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call CouchCreate(?,?,?,?)");
        stmt.setInt(1, dto.getId());
        stmt.setString(2, dto.getNombre());
        stmt.setString(3, dto.getContacto());
        stmt.setString(4, dto.getContacto());
        stmt.setString(5, dto.getEspecialidades());
        //Verifica si agrega a la bd los datos
        return stmt.executeUpdate() > 0;
    }

    @Override
    public EntrenadorDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call CouchRead(?)");
        stmt.setString(1, String.valueOf(id));
        rs = stmt.executeQuery();
        if (rs.next()) {
            return new EntrenadorDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
        }
        return null;
    }

    @Override
    public List<EntrenadorDTO> readAll() throws SQLException {
        stmt = connection.prepareStatement("call CouchReadAll()");
        rs = stmt.executeQuery();
        List<EntrenadorDTO> dtos = new ArrayList();
        while (rs.next()) {
            dtos.add(new EntrenadorDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            ));
        }
        return dtos;
    }

    @Override
    public boolean update(EntrenadorDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call CouchUpdate(?,?)");
        stmt.setString(1, dto.getContacto());
        stmt.setString(3, dto.getEspecialidades());
        return stmt.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        stmt = connection.prepareStatement("call CouchDelete(?)");
        stmt.setString(1, String.valueOf(id));
        return stmt.executeUpdate() > 0;
    }

}
