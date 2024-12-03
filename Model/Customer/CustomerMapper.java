/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Customer;

import Model.Mapper.Mapper;
import Utils.UtilDate;

/**
 *
 * @author sofia
 */
public class CustomerMapper implements Mapper<Customer, CustomerDTO> {

    @Override
    public CustomerDTO toDto(Customer ent) {
           return new CustomerDTO(
                ent.getCedula(),
                ent.getNombre(),
                UtilDate.toSqlDate(ent.getFechaNacimiento()),
                ent.getContacto(),
                ent.getTipoMembresia()
        ); 
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
               if(dto == null) return null;
        return new Customer(
               dto.getCedula(),
               dto.getNombre(),
               UtilDate.toLocalDate(dto.getFechaNacimiento()),
               dto.getContacto(),
               dto.getTipoMembresia()
       );
    }
    
}
