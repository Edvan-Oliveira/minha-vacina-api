package br.com.minhavacina.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.request.usuario.UsuarioLoginRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import br.com.minhavacina.service.UsuarioService;
import br.com.minhavacina.shared.Constantes;
import br.com.minhavacina.util.Utilitaria;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(Constantes.USUARIO)
public class UsuarioResource {

    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosOsUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosOsUsuarios());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarNovoUsuario(@RequestBody @Valid UsuarioPostRequest usuarioPostRequest) {
        return new ResponseEntity<>(usuarioService.cadastrarNovoUsuario(usuarioPostRequest), HttpStatus.CREATED);
    }

    @PutMapping @Transactional
    public ResponseEntity<Void> atualizarUsuario(@RequestBody @Valid UsuarioPutRequest usuarioPutRequest) {
        usuarioService.atualizarUsuario(usuarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(Constantes.LOGIN)
    public ResponseEntity<Usuario> realizarLogin(@RequestBody @Valid UsuarioLoginRequest usuarioLoginRequest) {
        Usuario usuarioLogado = usuarioService.realizarLogin(usuarioLoginRequest);
        return Utilitaria.objetoEstarNuloOuVazio(usuarioLogado)
                ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED) : new ResponseEntity<Usuario>(usuarioLogado, HttpStatus.OK);
    }

    @GetMapping(path = Constantes.VALIDA_EMAIL)
    public ResponseEntity<Boolean> validarEmail(@PathVariable String email) {
        boolean existe = Utilitaria.objetoNaoEstarNuloNemVazio(usuarioService.buscarUsuarioPorEmail(email));
        return new ResponseEntity<>(existe, HttpStatus.OK);
    }

}
