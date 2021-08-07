package br.com.minhavacina.service;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.mapper.UsuarioMapper;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodosOsUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Usuário não encontrado"));
    }

    public Usuario cadastrarNovoUsuario(UsuarioPostRequest usuarioPostRequest) {
        Usuario usuario = UsuarioMapper.INSTANCIA.converterParaUsuario(usuarioPostRequest);
        return usuarioRepository.save(usuario);
    }

    public void atualizarUsuario(UsuarioPutRequest usuarioPutRequest) {
        buscarUsuarioPorId(usuarioPutRequest.getId());
        Usuario usuario = UsuarioMapper.INSTANCIA.converterParaUsuario(usuarioPutRequest);
        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Integer id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }
}
