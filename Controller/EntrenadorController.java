/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.SQLException;
import Database.Database;
import Model.Entrenadores.Entrenador;
import Model.Entrenadores.EntrenadorDAO;
import Model.Entrenadores.EntrenadorDTO;
import Model.Entrenadores.EntrenadorMapper;
import View.View;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author sofia
 */
public class EntrenadorController {

    private EntrenadorDAO dao;
    private final View view;
    private final EntrenadorMapper mapper;


    public EntrenadorController(View view) {
        this.view = view;
        this.mapper = new EntrenadorMapper();
        try {
            this.dao = new EntrenadorDAO(Database.getInstance().getConnection());
        } catch (SQLException ex) {
            this.dao = null;
            view.showError("Error al conectar con la Base de Datos: " + ex.getMessage());
        }
    }

    // Método para crear un entrenador
    public void create(Entrenador entrenador) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        if (entrenador == null || !validateRequired(entrenador)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        try {
            // Verifica si el entrenador ya existe (si es necesario)
            if (!validatePK(entrenador.getId())) {
                view.showError("El entrenador con el ID ingresado ya se encuentra registrado");
                return;
            }
            // Guardar el entrenador en la base de datos
            dao.create(mapper.toDto(entrenador));
            view.showMessage("Entrenador guardado correctamente");
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al guardar los datos: " + ex.getMessage());
        }
    }

    // Método para obtener todos los entrenadores
    public List<Entrenador> readAll() {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return Collections.emptyList(); // Retorna una lista vacía si hay un error
        }

        try {
            List<EntrenadorDTO> dtoList = dao.readAll();
            return dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (SQLException ex) {
            view.showError("Error al cargar los datos: " + ex.getMessage());
            return Collections.emptyList(); // Retorna una lista vacía si ocurre una excepción
        }
    }

    public void read(int id) {
       if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            EntrenadorDTO dto = dao.read(id);
        if (dto != null) {
            Entrenador entrenador = mapper.toEntity(dto);
            view.show(entrenador);
        } else {
            view.showError("No se encontró el entrenador con ID: " + id);
            }
        } catch (SQLException ex) {
            view.showError("Error al cargar el entrenador: " + ex.getMessage());
        }
    }

    // Método para actualizar un entrenador
    public void update(Entrenador entrenador) {
        if (dao == null || entrenador == null || !validateRequired(entrenador)) {
            view.showError("Faltan datos requeridos");
            return;
        }

        try {
            if (validatePK(entrenador.getId())) {
                view.showError("El ID ingresado no se encuentra registrado");
                return;
            }
            dao.update(mapper.toDto(entrenador));
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al actualizar los datos: " + ex.getMessage());
        }
    }

    // Método para eliminar un entrenador por ID
    public void delete(int id) {
        if (dao == null) {
            view.showError("El acceso a la base de datos no se ha inicializado correctamente.");
            return;
        }

        try {
            // Eliminar entrenador de la base de datos
            boolean deleted = dao.delete(id);
            if (deleted) {
                view.showMessage("Entrenador eliminado correctamente");
            } else {
                view.showError("No se pudo eliminar el entrenador con ID: " + id);
            }
        } catch (SQLException ex) {
            view.showError("Ocurrió un error al eliminar los datos: " + ex.getMessage());
        }
    }

    // Método para verificar si el entrenador con el ID ya existe
    private boolean validatePK(int id) {
        try {
            EntrenadorDTO dto = dao.read(id);
            return dto == null; // Si el entrenador no existe, podemos continuar
        } catch (SQLException ex) {
            view.showError("Error al validar la existencia del entrenador: " + ex.getMessage());
            return false;
        }
    }

    private boolean validateRequired(Entrenador entrenador) {
        return entrenador != null
                && !entrenador.getNombre().trim().isEmpty()
                && !entrenador.getContacto().trim().isEmpty()
                && !entrenador.getEspecialidades().trim().isEmpty();
    }
}
