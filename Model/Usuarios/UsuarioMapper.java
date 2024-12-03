/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuarios;

import Model.Mapper.Mapper;

/**
 *
 * @author chris
 */
public class UsuarioMapper implements Mapper<Usuario, UsuarioDTO>{

    @Override
    public UsuarioDTO toDto(Usuario ent) {
       if (ent == null) return null;
        return new UsuarioDTO(
            ent.getId(),
            
            ent.getPassword(),
            ent.getNombre(),
            ent.getEmail(),
            ent.getRol()
        );
    }

    @Override
    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        return new Usuario(
            dto.getId(),
            
            dto.getPassword(),
            dto.getNombre(),
            dto.getEmail(),
            dto.getRol()
        );
    }
    
}
