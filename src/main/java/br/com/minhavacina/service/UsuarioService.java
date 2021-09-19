package br.com.minhavacina.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.mapper.UsuarioMapper;
import br.com.minhavacina.repository.UsuarioRepository;
import br.com.minhavacina.request.usuario.UsuarioLoginRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import br.com.minhavacina.util.Utilitaria;
import lombok.AllArgsConstructor;

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
                .orElseThrow(() -> new LancarAdvertencia("Usuário não encontrado"));
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

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).isEmpty()
                ? null : usuarioRepository.findByEmail(email).get(0);
    }

    public Usuario realizarLogin(UsuarioLoginRequest usuarioLoginRequest) {
        Usuario usuarioLogin = buscarUsuarioPorEmail(usuarioLoginRequest.getEmail());
        if (Utilitaria.objetoEstarNuloOuVazio(usuarioLogin)) return null;
        boolean senhaValida = validarSenha(usuarioLogin, usuarioLoginRequest.getSenha());
        return senhaValida ? usuarioLogin : null;
    }

    private void criptografarSenha(Usuario usuario) {
        usuario.setSenha(codificador.encode(usuario.getSenha()));
    }

    private boolean validarSenha(Usuario usuario, String senha) {
        return codificador.matches(senha, usuario.getSenha());
    }
}
