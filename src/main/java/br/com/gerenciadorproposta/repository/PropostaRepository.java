package br.com.gerenciadorproposta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gerenciadorproposta.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findByClienteId(Long id);
}