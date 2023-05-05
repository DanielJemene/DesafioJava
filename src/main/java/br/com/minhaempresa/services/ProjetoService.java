package br.com.minhaempresa.services;

import br.com.minhaempresa.models.Pessoa;
import br.com.minhaempresa.models.Projeto;
import br.com.minhaempresa.models.StatusProjeto;
import br.com.minhaempresa.repositories.PessoaRepository;
import br.com.minhaempresa.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    
    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    
    public Projeto buscarPorId(Long id) {
        Optional<Projeto> optionalProjeto = projetoRepository.findById(id);
        if (optionalProjeto.isPresent()) {
            return optionalProjeto.get();
        }
        throw new RuntimeException("Projeto não encontrado com o ID: " + id);
    }

    
    public Projeto salvarProjeto(Projeto projeto) {
        Pessoa gerente = pessoaRepository.findById(projeto.getPessoa().getId())
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado com o ID: " + projeto.getPessoa().getId()));
        projeto.setPessoa(gerente);
        return projetoRepository.save(projeto);
    }

    public Projeto atualizarProjeto(Long id, Projeto projetoAtualizado) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com o ID: " + id));

        projeto.setNome(projetoAtualizado.getNome());
        projeto.setDataInicio(projetoAtualizado.getDataInicio());
        projeto.setPessoa(projetoAtualizado.getPessoa());
        projeto.setDataPrevisaoFim(projetoAtualizado.getDataPrevisaoFim());
        projeto.setDataFim(projetoAtualizado.getDataFim());
        projeto.setOrcamento(projetoAtualizado.getOrcamento());
        projeto.setDescricao(projetoAtualizado.getDescricao());
        projeto.setStatus(projetoAtualizado.getStatus());
        projeto.setRisco(projetoAtualizado.getRisco());

        projetoRepository.save(projeto);
        return projeto;
    }



    public void deletarProjeto(Long id) {
        Projeto projeto = buscarPorId(id);
        if ((StatusProjeto.INICIADO).equals(projeto.getStatus()) || (StatusProjeto.EM_ANDAMENTO).equals(projeto.getStatus())
                || (StatusProjeto.ENCERRADO).equals(projeto.getStatus())) {
            throw new RuntimeException("Não é possível excluir um projeto com status de 'INICIADO', 'EM ANDAMENTO' ou 'CANCELADO'");
        }
        projetoRepository.delete(projeto);
    }


}

