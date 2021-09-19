package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.request.campanha.CampanhaPostRequest;
import br.com.minhavacina.request.campanha.CampanhaPutRequest;
import br.com.minhavacina.service.CampanhaService;
import br.com.minhavacina.shared.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constantes.CAMPANHA)
public class CampanhaResource {
    private final CampanhaService campanhaService;

    @GetMapping
    public ResponseEntity<List<Campanha>> listarTodasAsCampanhas() {
        return ResponseEntity.ok(campanhaService.listarTodasAsCampanhas());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Campanha> buscarCampanhaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(campanhaService.buscarCampanhaPorId(id));
    }

    @PostMapping @Transactional
    public ResponseEntity<Campanha> cadastrarNovaCampanha(@RequestBody @Valid CampanhaPostRequest campanhaPostRequest) {
        return new ResponseEntity<>(campanhaService.cadastrarNovaCampanha(campanhaPostRequest), HttpStatus.CREATED);
    }

    @PutMapping @Transactional
    public ResponseEntity atualizarCampanha(@RequestBody @Valid CampanhaPutRequest campanhaPutRequest) {
        campanhaService.atualizarCampanha(campanhaPutRequest);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletarCampanha(@PathVariable Integer id) {
        campanhaService.deletarCampanha(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
