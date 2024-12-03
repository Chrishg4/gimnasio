/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pago;

import Model.DAO.DaoCRD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pamel
 */
public class PagoDAO extends DaoCRD<PagoDTO>{
    
     public PagoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(PagoDTO dto) throws SQLException {
          stmt = connection.prepareStatement("call PayCreate(?,?,?,?,?)");
        stmt.setInt(1, dto.getIdPago());
        stmt.setString(2, dto.getCustomer()); 
        stmt.setDate(3, new Date(dto.getFecha().getTime()));
        stmt.setDouble(4, dto.getSubtotal());
        stmt.setDouble(5, dto.getImpuesto());
        return stmt.executeUpdate() > 0;
    }

    @Override
    public PagoDTO read(Object id) throws SQLException {
         stmt = connection.prepareStatement("call PayRead(?)");
        stmt.setInt(1, Integer.parseInt(String.valueOf(id)));
        rs = stmt.executeQuery();
        if (rs.next()) {
            return new PagoDTO(
                   rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6)
            );
        }
        return null;
    }

    @Override
    public List<PagoDTO> readAll() throws SQLException {
          stmt = connection.prepareStatement("call PayReadAll()");
        rs = stmt.executeQuery();
        List<PagoDTO> dtos = new ArrayList();
        while (rs.next()) {
            dtos.add(new PagoDTO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6)
            ));
        }
        return dtos;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
          stmt = connection.prepareStatement("call PayDelete(?)");
        stmt.setString(1, String.valueOf(id));
        return stmt.executeUpdate()>0;
    } 
}
