package br.com.minhavacina.service;

import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.repository.MunicipioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MunicipioService {
    private MunicipioRepository municipioRepository;

    public List<Municipio> listarTodosOsMunicipios() {
        return municipioRepository.findAll();
    }

    public Municipio buscarMunicipioPorId(Integer id) {
        return municipioRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Município não encontado"));
    }
}
