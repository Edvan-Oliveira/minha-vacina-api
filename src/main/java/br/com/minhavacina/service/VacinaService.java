package br.com.minhavacina.service;

import br.com.minhavacina.domain.Vacina;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.repository.VacinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VacinaService {
    private VacinaRepository vacinaRepository;

    public List<Vacina> listarTodasAsVacinas() {
        return vacinaRepository.findAll();
    }

    public Vacina buscarVacinaPorId(Integer id) {
        return vacinaRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Vacina não encontrada"));
    }
}
