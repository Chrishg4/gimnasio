/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
import Model.Pago.Pago;
import Model.Pago.PagoDAO;
import Model.Pago.PagoDTO;
import Model.Pago.PagoMapper;
import java.util.List;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.swing.text.View;

/**
 *
 * @author pamel
 */
public class PagoController {

    private PagoDAO dao;
    private final View view;
    private final PagoMapper mapper;

    public PagoController(View view) {
        this.view = view;
        mapper = new PagoMapper();
        try {
            dao = new PagoDAO(Database.getInstance().getConnection());
        } catch (SQLException ex) {
            this.dao = null; // En caso de fallo, evita excepciones posteriores
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        }
    }

    public void create(Pago pago) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (pago == null || !validateRequired(pago)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        try {
            dao.create(mapper.toDto(pago));  // Llamada al método create de PagoDAO
            view.showMessage("Pago registrado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al registrar el pago: " + ex.getMessage());
        }
    }

    public void read(int idPago) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            PagoDTO dto = dao.read(idPago);
            if (dto == null) {
                view.showError("No se encontró el pago con el ID proporcionado");
                return;
            }
            Pago pago = mapper.toEntity(dto);
            view.showPago(pago);  // Mostrar los detalles del pago
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al leer los datos del pago: " + ex.getMessage());
        }
    }

    public void readAll() {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            List<PagoDTO> dtoList = dao.readAll();
            List<Pago> pagoList = dtoList.stream()
                    .map(mapper::toEntity)
                    .collect(Collectors.toList());
            view.showAllPagos(pagoList);  // Mostrar todos los pagos
        } catch (SQLException ex) {
            view.showError("Error al cargar los pagos: " + ex.getMessage());
        }
    }

    public void delete(int idPago) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            dao.delete(idPago);  // Llamada al método delete de PagoDAO
            view.showMessage("Pago eliminado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar el pago: " + ex.getMessage());
        }
    }

    private boolean validateRequired(Pago pago) {
                return pago != null
                && pago.getFecha() != null
                && pago.getSubtotal() >= 0
                && pago.getImpuesto() >= 0
                && pago.getTotal() >= 0;
    }
}
