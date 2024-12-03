/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Clases;

import Model.Clases.ClaseDTO;
import Model.DAO.DaoCRUD;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sofia
 */
public class ClaseDAO extends DaoCRUD<ClaseDTO> {

    public ClaseDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(ClaseDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call ClassCreate(?,?,?,?,?)");
        stmt.setInt(1, dto.getId());
        stmt.setString(2, dto.getTipoClase());
        stmt.setTimestamp(3, Timestamp.valueOf(dto.getHorario()));
        stmt.setInt(4, dto.getEntrenadorId());
        stmt.setInt(4, dto.getCapacidadMaxima());
        return stmt.executeUpdate() > 0;
    }

    @Override
    public ClaseDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call ClassRead(?)");
        stmt.setInt(1, Integer.parseInt(String.valueOf(id)));
        rs = stmt.executeQuery();
        if (rs.next()) {
            return new ClaseDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getInt(4),
                    rs.getInt(5)
            );
        }
        return null;
    }

    @Override
    public List<ClaseDTO> readAll() throws SQLException {
        stmt = connection.prepareStatement("call ClasstReadAll()");
        rs = stmt.executeQuery();
        List<ClaseDTO> dtos = new ArrayList();
        while (rs.next()) {
            dtos.add(new ClaseDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        }
        return dtos;
    }

    @Override
    public boolean update(ClaseDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call ClassUpdate(?,?,?)");
        stmt.setInt(1, dto.getId());
        stmt.setInt(2, dto.getEntrenadorId());
        stmt.setTimestamp(3, Timestamp.valueOf(dto.getHorario()));
        return stmt.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        stmt = connection.prepareStatement("call ClassDelete(?)");
        stmt.setInt(1, Integer.parseInt(String.valueOf(id)));
        return stmt.executeUpdate() > 0;
    }

}
