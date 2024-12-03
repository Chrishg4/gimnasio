/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pago;

import Database.Database;
import Model.Customer.CustomerDAO;
import Model.Customer.CustomerDTO;
import Model.Customer.CustomerMapper;
import Model.Mapper.Mapper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author pamel
 */
public class PagoMapper implements Mapper<Pago, PagoDTO> {

    @Override
    public PagoDTO toDto(Pago ent) {
        if (ent == null) {
            return null;
        }
        return new PagoDTO(
                ent.getIdpago(),
                ent.getCustomer().getCedula(),
                ent.getFecha(),
                ent.getSubtotal(),
                ent.getImpuesto(),
                ent.getTotal()
        );
    }

    @Override
    public Pago toEntity(PagoDTO dto) {
        if (dto == null) {
            return null;
        }
        try {

            // Obtén la instancia de Database usando el patrón Singleton
            Database database = Database.getInstance();

            // Usa el método getConnection() de la instancia
            Connection connection = database.getConnection();

            // Recupera el cliente utilizando el DAO
            CustomerDAO customerDAO = new CustomerDAO(connection);
            CustomerDTO customer = customerDAO.read(dto.getCustomer());

            return new Pago(
                    dto.getIdPago(),
                    new CustomerMapper().toEntity(customer),
                    dto.getFecha(),
                    dto.getSubtotal(),
                    dto.getImpuesto(),
                    dto.getTotal()
            );
        } catch (SQLException ex) {
            Logger.getLogger(PagoMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
