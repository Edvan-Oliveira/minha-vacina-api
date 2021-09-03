package br.com.minhavacina.service;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.exception.ConteudoNaoEncontrado;
import br.com.minhavacina.mapper.UsuarioMapper;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.request.UsuarioPostRequest;
import br.com.minhavacina.request.UsuarioPutRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder codificador;

    public List<Usuario> listarTodosOsUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ConteudoNaoEncontrado("Usuário não encontrado"));
    }

    public Usuario cadastrarNovoUsuario(UsuarioPostRequest usuarioPostRequest) {
        Usuario usuario = UsuarioMapper.INSTANCIA.converterParaUsuario(usuarioPostRequest);
        criptografarSenha(usuario);
        return usuarioRepository.save(usuario);
    }

    public void atualizarUsuario(UsuarioPutRequest usuarioPutRequest) {
        buscarUsuarioPorId(usuarioPutRequest.getId());
        Usuario usuario = UsuarioMapper.INSTANCIA.converterParaUsuario(usuarioPutRequest);
        criptografarSenha(usuario);
        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Integer id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }

    private void criptografarSenha(Usuario usuario) {
        usuario.setSenha(codificador.encode(usuario.getSenha()));
    }
}
