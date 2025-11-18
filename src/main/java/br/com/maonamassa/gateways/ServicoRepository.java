package br.com.maonamassa.gateways;

import br.com.maonamassa.domains.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, UUID> {
    Page<Servico> findByCidade(String cidade, Pageable pageable);
}
