/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
import Model.Customer.Customer;
import Model.Customer.CustomerDAO;
import Model.Customer.CustomerDTO;
import Model.Customer.CustomerMapper;
import javax.swing.text.View;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author sofia
 */
public class CustomerController {

    private CustomerDAO dao;
    private final View view;
    private final CustomerMapper mapper;

    public CustomerController(View view) {
        this.view = view;
        mapper = new CustomerMapper();
        try {
            dao = new CustomerDAO(Database.getInstance().getConnection());
        } catch (SQLException ex) {
            this.dao = null; // En caso de fallo, evita excepciones posteriores
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        }
    }

    public void create(Customer customer) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (customer == null || !validateRequired(customer)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        try {
            if (!validatePK(customer.getCedula())) {
                view.showError("La cédula ingresada ya se encuentra registrada");
                return;
            }
            dao.create(mapper.toDto(customer));
            view.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al guardar los datos: " + ex.getMessage());
        }
    }

    public void read(String cedula) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (cedula == null || cedula.trim().isEmpty()) {
            view.showError("Debe proporcionar una cédula válida");
            return;
        }

        try {
            CustomerDTO dto = dao.read(cedula);
            if (dto == null) {
                view.showError("No se encontró un cliente con la cédula ingresada");
                return;
            }
            Customer customer = mapper.toEntity(dto);
            view.showCustomer(customer);
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al leer los datos: " + ex.getMessage());
        }
    }

    public void readAll() {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            List<CustomerDTO> dtoList = dao.readAll();
            List<Customer> customerList = dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            view.showAll(customerList);
        } catch (SQLException ex) {
            view.showError("Error al cargar los datos: " + ex.getMessage());
        }
    }

    public void update(Customer customer) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (customer == null || !validateRequired(customer)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        try {
            if (validatePK(customer.getCedula())) {
                view.showError("La cédula ingresada no se encuentra registrada");
                return;
            }
            dao.update(mapper.toDto(customer));
            view.showMessage("Datos actualizados correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
        }
    }

    public void delete(String cedula) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (cedula == null || cedula.trim().isEmpty()) {
            view.showError("Debe proporcionar una cédula válida");
            return;
        }

        try {
            if (validatePK(cedula)) {
                view.showError("La cédula ingresada no se encuentra registrada");
                return;
            }
            dao.delete(cedula);
            view.showMessage("Cliente eliminado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar los datos: " + ex.getMessage());
        }
    }

    public boolean validatePK(String cedula) {
        try {
            return dao.validatePK(cedula);
        } catch (SQLException ex) {
            view.showError("Error al validar la cédula: " + ex.getMessage());
            return false;
        }
    }

    private boolean validateRequired(Customer customer) {
        return customer != null
                && !customer.getCedula().trim().isEmpty()
                && !customer.getNombre().trim().isEmpty()
                && customer.getFechaNacimiento() != null
                && !customer.getContacto().trim().isEmpty()
                && customer.getTipoMembresia() != null && !customer.getTipoMembresia().trim().isEmpty();  // Verifica que tipoMembresía no esté vacío ni sea null
    }
}
