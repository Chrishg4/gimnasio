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

import View.View;
import java.util.ArrayList;
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

    // Método para crear(guardar) una clase
    public void create(Clase clase) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

//        if (clase == null || !validateRequired(clase)) {
//            view.showError("Faltan datos requeridos");
//            return;
//        }

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
    
    public void update(Clase clase) {
    if (dao == null) {
        view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
        return;
    }

    if (clase == null || !validateRequired(clase)) {
        view.showError("Faltan datos requeridos.");
        return;
    }

    try {
        // Verificar que la clase exista antes de actualizar
        if (validatePK(clase.getId())) {
            view.showError("La clase con el ID ingresado no se encuentra registrada.");
            return;
        }

        // Actualizar la clase en la base de datos
        dao.update(mapper.toDto(clase));
        view.showMessage("Clase actualizada correctamente.");
    } catch (SQLException ex) {
        view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
    }
}

    // Método para obtener todas las clases
    public List<Clase> readAll() {
       if (dao == null) {
        view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
        return new ArrayList<>(); // Devolver lista vacía si no hay acceso a la base de datos
    }

    try {
        List<ClaseDTO> dtoList = dao.readAll();
        
 
        List<Clase> claseList = dtoList.stream()
                .map(mapper::toEntity)
                .filter(Objects::nonNull) // Filtrar cualquier entidad nula
                .collect(Collectors.toList());

        // Mostrar todas las clases en la vista
        view.showAll(claseList);
        
        // Retornar la lista de clases
        return claseList;

    } catch (SQLException ex) {
        view.showError("Error al cargar los datos: " + ex.getMessage());
        return new ArrayList<>();
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
                List<Clase> claseList = List.of(clase);
                view.showAll(claseList); // Muestra la clase encontrada como parte de una lista
            } else {
                view.showError("No se encontró la clase con ID: " + id);
            }
        } catch (SQLException ex) {
            view.showError("Error al cargar la clase: " + ex.getMessage());
        }
    }
    
    
    public void delete(int id) {
    if (dao == null) {
        view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
        return;
    }

    if (id <= 0) {
        view.showError("Debe proporcionar un ID válido.");
        return;
    }

    try {
        if (validatePK(id)) {
            view.showError("El ID ingresado no se encuentra registrado.");
            return;
        }
        dao.delete(id);
        view.showMessage("Clase eliminada correctamente.");
    } catch (SQLException ex) {
        view.showError("Ocurrió un error al eliminar los datos: " + ex.getMessage());
    }
}

    // Método para validar los datos requeridos
    private boolean validateRequired(Clase clase) {
        return clase.getId() > 0 && 
                clase.getTipoClase() != null && 
                !clase.getTipoClase().isEmpty() && 
                clase.getHorario() != null && 
                clase.getCapacidadMaxima() > 0;
        
        
        
//          return clase.getTipoClase() != null &&
//           clase.getHorario() != null &&
//           clase.getEntrenadorId() > 0 &&  // Asegura que el ID del entrenador sea mayor a 0
//           clase.getCapacidadMaxima() > 0;  // Asegura que la capacidad sea mayor a 0
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
