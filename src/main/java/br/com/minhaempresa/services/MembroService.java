package br.com.minhaempresa.services;

import br.com.minhaempresa.exceptions.PessoaNaoFuncionarioException;
import br.com.minhaempresa.models.Membro;
import br.com.minhaempresa.models.Pessoa;
import br.com.minhaempresa.models.Projeto;
import br.com.minhaempresa.repositories.MembroRepository;
import br.com.minhaempresa.repositories.PessoaRepository;
import br.com.minhaempresa.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<Membro> listaMembros(){
        return membroRepository.findAll();
    }
    public void associarPessoaAoProjeto(Long idPessoa, Long idProjeto) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com o ID: " + idProjeto));
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + idPessoa));
        if (!pessoa.isFuncionario()){
            throw new PessoaNaoFuncionarioException("Essa pessoa não é um funcionario");
        }

        Membro membro = new Membro();
        membro.setPessoa(pessoa);
        membro.setProjeto(projeto);

        membroRepository.save(membro);
    }

}



