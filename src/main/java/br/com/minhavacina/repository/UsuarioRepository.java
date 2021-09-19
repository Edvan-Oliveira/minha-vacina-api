package br.com.minhavacina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.minhavacina.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("select u from Usuario u where u.email = :email")
    List<Usuario> findByEmail(String email);
}
