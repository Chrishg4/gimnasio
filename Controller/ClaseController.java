/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Database;
import Model.Clases.Clase;
import Model.Clases.ClaseDAO;
import Model.Clases.ClaseDTO;
import Model.Clases.ClaseMapper;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.text.View;

/**
 *
 * @author sofia
 */
public class ClaseController {

    private ClaseDAO dao;
    private final View view;
    private final ClaseMapper mapper;

    public ClaseController(View view) {
        this.view = view;
        this.mapper = new ClaseMapper();
        try {
            this.dao = new ClaseDAO(Database.getInstance().getConnection());
        } catch (SQLException ex) {
            this.dao = null;
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        }
    }

    // Método para crear una clase
    public void create(Clase clase) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (clase == null || !validateRequired(clase)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        try {
            // Verifica si la clase ya existe (si es necesario)
            if (!validatePK(clase.getId())) {
                view.showError("La clase con el ID ingresado ya se encuentra registrada");
                return;
            }
            // Guardar la clase en la base de datos
            dao.create(mapper.toDto(clase));
            view.showMessage("Clase guardada correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al guardar los datos: " + ex.getMessage());
        }
    }

    // Método para obtener todas las clases
    public void readAll() {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            List<ClaseDTO> dtoList = dao.readAll();
            List<Clase> claseList = dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            view.showAll(claseList); // Muestra las clases en la vista
        } catch (SQLException ex) {
            view.showError("Error al cargar los datos: " + ex.getMessage());
        }
    }

    // Método para obtener una clase por ID
    public void read(int id) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            ClaseDTO dto = dao.read(id);
            if (dto != null) {
                Clase clase = mapper.toEntity(dto);
                view.showOne(clase); // Muestra la clase encontrada
            } else {
                view.showError("No se encontró la clase con ID: " + id);
            }
        } catch (SQLException ex) {
            view.showError("Error al cargar la clase: " + ex.getMessage());
        }
    }

    // Método para validar los datos requeridos
    private boolean validateRequired(Clase clase) {
        return clase.getId() > 0 && clase.getTipoClase() != null && !clase.getTipoClase().isEmpty()
                && clase.getHorario() != null && clase.getCapacidadMaxima() > 0;
    }

    // Método para verificar si la clase con el ID ya existe
    private boolean validatePK(int id) {
        try {
            ClaseDTO dto = dao.read(id);
            return dto == null; // Si la clase no existe, podemos continuar
        } catch (SQLException ex) {
            view.showError("Error al validar la existencia de la clase: " + ex.getMessage());
            return false;
        }
    }
}
