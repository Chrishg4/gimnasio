/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entrenadores;

import Model.Mapper.Mapper;

/**
 *
 * @author sofia
 */
public class EntrenadorMapper implements Mapper<Entrenador, EntrenadorDTO> {

    @Override
    public EntrenadorDTO toDto(Entrenador ent) {
              return new EntrenadorDTO(
                ent.getId(),
                ent.getNombre(),
                ent.getContacto(),
                ent.getEspecialidades()
        );
    }

    @Override
    public Entrenador toEntity(EntrenadorDTO dto) {
        if(dto == null) return null;
                 return new Entrenador(
                dto.getId(),
                dto.getNombre(),
                dto.getContacto(),
                dto.getEspecialidades()
        );
    }
    
}
