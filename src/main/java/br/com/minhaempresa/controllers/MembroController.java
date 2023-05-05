package br.com.minhaempresa.controllers;

import br.com.minhaempresa.models.Membro;
import br.com.minhaempresa.services.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membros")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @GetMapping
    public List<Membro> listaMembros(){
        return membroService.listaMembros();
    }

    @PostMapping("/{idPessoa}/{idProjeto}")
    public ResponseEntity<String> associarMembroAoProjeto(@PathVariable Long idPessoa, @PathVariable Long idProjeto) {
        membroService.associarPessoaAoProjeto(idPessoa, idProjeto);
        return ResponseEntity.ok("Membro associado com sucesso ao projeto!");
    }
}
