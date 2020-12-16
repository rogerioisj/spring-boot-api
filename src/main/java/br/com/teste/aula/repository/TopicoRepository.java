package br.com.teste.aula.repository;

import br.com.teste.aula.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    List<Topico> findByCursoNome(String nomeCurso);
    Optional<Topico> findById(Long id);
}
