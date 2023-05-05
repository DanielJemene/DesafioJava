package br.com.minhaempresa.repositories;

import br.com.minhaempresa.models.Projeto;
import br.com.minhaempresa.models.RiscoProjeto;
import br.com.minhaempresa.models.StatusProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
    List<Projeto> findByStatus(StatusProjeto status);

    List<Projeto> findByRisco(RiscoProjeto risco);
}
