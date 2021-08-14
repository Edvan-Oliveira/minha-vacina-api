package br.com.minhavacina.resource;

import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.service.VacinaService;
import br.com.minhavacina.shared.Constantes;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constantes.VACINA)
@AllArgsConstructor
public class VacinaResource {
    private VacinaService vacinaService;

    @GetMapping
    public ResponseEntity<List<Vacina>> listarTodasAsCampanhas() {
        return ResponseEntity.ok(vacinaService.listarTodasAsVacinas());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Vacina> buscarVacinaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vacinaService.buscarVacinaPorId(id));
    }
}
