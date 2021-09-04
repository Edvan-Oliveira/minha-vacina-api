package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.request.usuario.UsuarioLoginRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import br.com.minhavacina.service.UsuarioService;
import br.com.minhavacina.shared.Constantes;
import br.com.minhavacina.util.Utilitaria;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity atualizarUsuario(@RequestBody @Valid UsuarioPutRequest usuarioPutRequest) {
        usuarioService.atualizarUsuario(usuarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(Constantes.LOGIN)
    public ResponseEntity<Usuario> realizarLogin(@RequestBody @Valid UsuarioLoginRequest usuarioLoginRequest) {
        Usuario usuarioLogado = usuarioService.realizarLogin(usuarioLoginRequest);
        return Utilitaria.objetoEstarNuloOuVazio(usuarioLogado)
                ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED) : new ResponseEntity(usuarioLogado, HttpStatus.OK);
    }

}
