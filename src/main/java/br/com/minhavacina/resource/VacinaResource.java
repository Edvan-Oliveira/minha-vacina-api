package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.minhavacina.shared.Constantes.*;

@RestController
@RequestMapping(VACINA)
@RequiredArgsConstructor
public class VacinaResource {

    private final VacinaService vacinaService;

    @GetMapping
    public ResponseEntity<List<Vacina>> listarTodasAsCampanhas() {
        return ResponseEntity.ok(vacinaService.listarTodasAsVacinas());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vacina> buscarVacinaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vacinaService.buscarVacinaPorId(id));
    }

    @PutMapping(ASSOCIAR_USUARIO)
    public ResponseEntity<Void> associarUsuario(@RequestBody Vacina vacina) {
        vacinaService.associarUsuario(vacina);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(DESASSOCIAR_USUARIO)
    public ResponseEntity<Void> desassociarUsuario(@RequestBody Vacina vacina) {
        vacinaService.desassociarUsuario(vacina);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
