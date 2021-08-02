package br.com.minhavacina.mapper;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.request.UsuarioPostRequest;
import br.com.minhavacina.request.UsuarioPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {
    public static UsuarioMapper INSTANCIA = Mappers.getMapper(UsuarioMapper.class);
    public abstract Usuario converterParaUsuario(UsuarioPostRequest usuarioPostRequest);
    public abstract Usuario converterParaUsuario(UsuarioPutRequest usuarioPutRequest);
}
