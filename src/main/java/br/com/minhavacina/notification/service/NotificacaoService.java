package br.com.minhavacina.notification.service;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Usuario;
import br.com.minhavacina.notification.model.TokenNotificacao;
import br.com.minhavacina.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.minhavacina.util.Utilitaria.obterUsuarioAutenticado;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final UsuarioRepository usuarioRepository;

    public void cadastrarOuAtualizarTokenDeNotificacao(TokenNotificacao tokenNotificacao) {
        Usuario usuario = obterUsuarioAutenticado();
        usuario.setTokenNotificao(tokenNotificacao.getToken());
        usuarioRepository.save(usuario);
    }

    private void enviarNotificacaoDeCampanhaAberta(Campanha campanha) {


    }
}
