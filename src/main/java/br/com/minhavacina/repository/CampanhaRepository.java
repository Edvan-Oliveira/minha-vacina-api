package br.com.minhavacina.repository;

import br.com.minhavacina.domain.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {

    @Query("select c from Campanha c where c.ativa = true")
    List<Campanha> listarCampanhasAtivas();

    @Query("select c from Campanha c where c.ativa = false")
    List<Campanha> listarCampanhasInativas();
}
