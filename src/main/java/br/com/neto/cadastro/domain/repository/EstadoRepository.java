package br.com.neto.cadastro.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neto.cadastro.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findTodasByNomeContaining(String nome);

	Optional<Estado> findByNome(String nome);

	boolean existsByNome(String nome);
}
