package br.com.minhavacina.service;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.mapper.UsuarioMapper;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.request.usuario.UsuarioGetRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.minhavacina.util.Utilitaria.criptografarSenha;

@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(usuarioRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário [ " + email + " ] não encontrado"));
    }

    public List<UsuarioGetRequest> listarTodosOsUsuarios() {
        return usuarioMapper.converterParaListaUsuarioGetRequest(usuarioRepository.findAll());
    }

    public UsuarioGetRequest buscarUsuarioGetRequestPorId(Integer id) {
        return usuarioMapper.converterParaUsuarioGetRequest(usuarioRepository.findById(id)
            .orElseThrow(() -> new LancarAdvertencia("Usuário não encntrado")));
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Usuário não encontrado"));
    }

    public UsuarioGetRequest cadastrarNovoUsuario(UsuarioPostRequest usuarioPostRequest) {
        Usuario usuario = usuarioMapper.converterParaUsuario(usuarioPostRequest);
        criptografarSenha(usuario);
        return usuarioMapper.converterParaUsuarioGetRequest(usuarioRepository.save(usuario));
    }

    public void atualizarUsuario(UsuarioPutRequest usuarioPutRequest) {
        buscarUsuarioPorId(usuarioPutRequest.getId());
        Usuario usuario = usuarioMapper.converterParaUsuario(usuarioPutRequest);
        criptografarSenha(usuario);
        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Integer id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
