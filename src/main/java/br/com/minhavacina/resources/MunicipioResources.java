package br.com.minhavacina.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("minha-vacina-api/municipios")
public class MunicipioResources {
    @GetMapping
    public List<String> listar() {
        return Arrays.asList("Cajueiro", "Campestre", "Maceió");
    }
}
