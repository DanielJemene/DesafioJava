package br.com.minhaempresa.services;

import br.com.minhaempresa.models.Pessoa;
import br.com.minhaempresa.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

 
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public List<Pessoa> listarPessoasFuncionario() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .filter(pessoa -> pessoa.isFuncionario())
                .collect(Collectors.toList());
    }
    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
    public Pessoa buscarPorId(Long id) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        if (optionalPessoa.isPresent()) {
            return optionalPessoa.get();
        }
        throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
    }

    public void deletarPessoa(Long id) {
        Pessoa pessoa = buscarPorId(id);
        if (pessoa.isFuncionario()) {
            throw new RuntimeException("Não é possível excluir um funcionário.");
        }
        pessoaRepository.delete(pessoa);
    }


}

