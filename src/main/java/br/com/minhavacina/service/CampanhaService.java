package br.com.minhavacina.service;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Permissao;
import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.exception.LancarAdvertencia;
import br.com.minhavacina.mapper.CampanhaMapper;
import br.com.minhavacina.repository.CampanhaRepository;
import br.com.minhavacina.request.campanha.CampanhaPostRequest;
import br.com.minhavacina.request.campanha.CampanhaPutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static br.com.minhavacina.util.Utilitaria.*;

@Service
@RequiredArgsConstructor
public class CampanhaService {
    private final CampanhaRepository campanhaRepository;

    public List<Campanha> listarCampanhasAtivas() {
        Usuario usuario = obterUsuarioAutenticado();
        boolean usuarioApp = this.verificarUsuarioPermissaoApp(usuario);
        return usuarioApp ? campanhaRepository.listarCampanhasPorMunicipio(usuario.getMunicipio())
                : campanhaRepository.listarCampanhasAtivas();
    }

    public List<Campanha> listarCampanhasInativas() {
        return campanhaRepository.listarCampanhasInativas();
    }

    public Campanha buscarCampanhaPorId(Integer id) {
        return campanhaRepository.findById(id)
                .orElseThrow(() -> new LancarAdvertencia("Campanha não encontrada"));
    }

    public Campanha cadastrarNovaCampanha(CampanhaPostRequest campanhaPostRequest) {
        Campanha campanha = CampanhaMapper.INSTANCIA.converterParaCampanha(campanhaPostRequest);
        validarCampanha(campanha);
        return campanhaRepository.save(campanha);
    }

    public void atualizarCampanha(CampanhaPutRequest campanhaPutRequest) {
        buscarCampanhaPorId(campanhaPutRequest.getId());
        Campanha campanha = CampanhaMapper.INSTANCIA.converterParaCampanha(campanhaPutRequest);
        validarCampanha(campanha);
        campanhaRepository.save(campanha);
    }

    public void finalizarCampanha(Integer id) {
        Campanha campanha = buscarCampanhaPorId(id);
        campanha.setAtiva(false);
        campanha.setDataFim(new Date());
        campanhaRepository.save(campanha);
    }

    public void deletarCampanha(Integer id) {
        Campanha campanha = buscarCampanhaPorId(id);
        campanhaRepository.delete(campanha);
    }

    private void validarCampanha(Campanha campanha) {
        validarIdades(campanha);
        boolean continuarValidacao = validarDatasNulas(campanha);
        if (!continuarValidacao) return;
        validarIntervalosDeDatas(campanha);
        validarDataInicio(campanha);
        setarCampanhaAtiva(campanha);
    }

    private void validarIdades(Campanha campanha) {
        if (objetoEstarNuloOuVazio(campanha.getIdadeMinima())
                && objetoEstarNuloOuVazio(campanha.getIdadeMaxima()))
            return;

        if (objetoEstarNuloOuVazio(campanha.getIdadeMinima())
                && objetoNaoEstarNuloNemVazio(campanha.getIdadeMaxima()))
            return;

        if (objetoEstarNuloOuVazio(campanha.getIdadeMaxima())
                && objetoNaoEstarNuloNemVazio(campanha.getIdadeMinima()))
            return;

        if (campanha.getIdadeMinima() > campanha.getIdadeMaxima())
            throw new LancarAdvertencia("Idade mínima da campanha não pode ser menor que a idade máxima");
    }

    private boolean validarDatasNulas(Campanha campanha) {
        if (objetoEstarNuloOuVazio(campanha.getDataInicio()))
            throw new LancarAdvertencia("Data de início da campanha não pode ser nula");
        if (objetoEstarNuloOuVazio(campanha.getDataFim()))
            throw new LancarAdvertencia("Data final da campanha não pode ser nula");
        return true;
    }

    private void validarIntervalosDeDatas(Campanha campanha) {
        if (campanha.getDataInicio().getTime() > campanha.getDataFim().getTime())
            throw new LancarAdvertencia("Data de início da campanha não pode ser maior que a data final");
    }

    private void validarDataInicio(Campanha campanha) {
        Date dataAtual = converterDataTextoParaDataUtil(converterDataUtilParaDataTexto(new Date()));
        if (dataAtual.getTime() > campanha.getDataInicio().getTime())
            throw new LancarAdvertencia("Data de início da campanha não pode ser menor que a data atual");
    }

    private void setarCampanhaAtiva(Campanha campanha) {
        Date dataAtual = converterDataTextoParaDataUtil(converterDataUtilParaDataTexto(new Date()));
        campanha.setAtiva(campanha.getDataInicio().getTime() == dataAtual.getTime());
    }

    private boolean verificarUsuarioPermissaoApp(Usuario usuario) {
        for (Permissao permissao : usuario.getPermissoes()) {
            if (permissao.getId() == 1) return true;
        }
        return false;
    }
}
