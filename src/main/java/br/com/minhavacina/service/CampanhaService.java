package br.com.minhavacina.service;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.mapper.CampanhaMapper;
import br.com.minhavacina.repository.CampanhaRepository;
import br.com.minhavacina.request.campanha.CampanhaPostRequest;
import br.com.minhavacina.request.campanha.CampanhaPutRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampanhaService {
    private CampanhaRepository campanhaRepository;

    public List<Campanha> listarTodasAsCampanhas() {
        return campanhaRepository.findAll();
    }

    public Campanha buscarCampanhaPorId(Integer id) {
        return campanhaRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Campanha não encontrada"));
    }

    public Campanha cadastrarNovaCampanha(CampanhaPostRequest campanhaPostRequest) {
        Campanha campanha = CampanhaMapper.INSTANCIA.converterParaCampanha(campanhaPostRequest);
        validarIntervalosDeDatasEIdades(campanha);
        return campanhaRepository.save(campanha);
    }

    public void atualizarCampanha(CampanhaPutRequest campanhaPutRequest) {
        buscarCampanhaPorId(campanhaPutRequest.getId());
        Campanha campanha = CampanhaMapper.INSTANCIA.converterParaCampanha(campanhaPutRequest);
        validarIntervalosDeDatasEIdades(campanha);
        campanhaRepository.save(campanha);
    }

    public void deletarCampanha(Integer id) {
        Campanha campanha = buscarCampanhaPorId(id);
        campanhaRepository.delete(campanha);
    }

    private void validarIntervalosDeDatasEIdades(Campanha campanha) {
        if (campanha.getDataInicio().getTime() >= campanha.getDataFim().getTime())
            throw new LancarAdvertencia("Data de início da campanha não pode ser maior ou igual que a data final");
        if (campanha.getIdadeMinima() >= campanha.getIdadeMaxima())
            throw new LancarAdvertencia("Idade mínima da campanha não pode ser menor que a idade máxima");
    }
}
