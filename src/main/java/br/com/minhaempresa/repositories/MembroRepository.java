package br.com.minhaempresa.repositories;

import br.com.minhaempresa.models.Membro;
import br.com.minhaempresa.models.MembroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<Membro, MembroId> {

}


