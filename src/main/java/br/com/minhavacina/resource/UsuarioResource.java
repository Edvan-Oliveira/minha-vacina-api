package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.request.usuario.UsuarioGetRequest;
import br.com.minhavacina.request.usuario.UsuarioLoginRequest;
import br.com.minhavacina.request.usuario.UsuarioPostRequest;
import br.com.minhavacina.request.usuario.UsuarioPutRequest;
import br.com.minhavacina.service.UsuarioService;
import br.com.minhavacina.shared.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static br.com.minhavacina.util.Utilitaria.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constantes.USUARIO)
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioGetRequest>> listarTodosOsUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosOsUsuarios());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioGetRequest> buscarUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioGetRequestPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioGetRequest> cadastrarNovoUsuario(@RequestBody @Valid UsuarioPostRequest usuarioPostRequest) {
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
        return objetoEstarNuloOuVazio(usuarioLogado)
                ? new ResponseEntity<>(HttpStatus.UNAUTHORIZED) : new ResponseEntity(usuarioLogado, HttpStatus.OK);
    }

    @GetMapping(path = Constantes.VALIDA_EMAIL)
    public ResponseEntity<Boolean> validarEmail(@PathVariable String email) {
        boolean existe = objetoNaoEstarNuloNemVazio(usuarioService.buscarUsuarioPorEmail(email));
        return new ResponseEntity<>(existe, HttpStatus.OK);
    }

}
