/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Customer;

import Model.DAO.DaoCRUD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sofia
 */
public class CustomerDAO extends DaoCRUD<CustomerDTO> {

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(CustomerDTO dto) throws SQLException {
        stmt = connection.prepareStatement("call CustomerCreate(?,?,?,?,?)");
        stmt.setString(1, dto.getCedula());
        stmt.setString(2, dto.getNombre());
        stmt.setDate(3, dto.getFechaNacimiento());
        stmt.setString(4, dto.getContacto());
        stmt.setString(5, dto.getTipoMembresia());
        //Verifica si agrega a la bd los datos
        return stmt.executeUpdate() > 0;
    }

    @Override
    public CustomerDTO read(Object id) throws SQLException {
        stmt = connection.prepareStatement("call CustomerRead(?)");
        stmt.setString(1, String.valueOf(id));
        rs = stmt.executeQuery();
        if (rs.next()) {
            return new CustomerDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<CustomerDTO> readAll() throws SQLException {
        stmt = connection.prepareStatement("call CustomerReadAll()");
        rs = stmt.executeQuery();
        List<CustomerDTO> dtos = new ArrayList();
        while (rs.next()) {
            dtos.add(new CustomerDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDate(3),
                    rs.getString(4),
                    rs.getString(5)
            ));
        }
        return dtos;
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException {
           stmt = connection.prepareStatement("call CustomerUpdate(?,?)");
        stmt.setString(1, dto.getCedula());
        stmt.setString(2, dto.getContacto());
        stmt.setString(3, dto.getTipoMembresia());
        return stmt.executeUpdate()>0;
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        stmt = connection.prepareStatement("call CustomerDelete(?)");
        stmt.setString(1, String.valueOf(id));
        return stmt.executeUpdate()>0;
    }
    
    public boolean validatePK(Object id) throws SQLException{
        return read(id)==null;
    }

}
