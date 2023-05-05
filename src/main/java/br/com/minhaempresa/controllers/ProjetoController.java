package br.com.minhaempresa.controllers;

import br.com.minhaempresa.models.Pessoa;
import br.com.minhaempresa.models.Projeto;
import br.com.minhaempresa.models.RiscoProjeto;
import br.com.minhaempresa.models.StatusProjeto;
import br.com.minhaempresa.repositories.PessoaRepository;
import br.com.minhaempresa.services.MembroService;
import br.com.minhaempresa.services.PessoaService;
import br.com.minhaempresa.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private MembroService membroService;

    @Autowired
    private PessoaService pessoaService;


    @GetMapping({"/listarProjetos"})
    public String listarProjetos(@ModelAttribute("message") String message, Model model) {
        List<Projeto> projetos = projetoService.listarProjetos();
        model.addAttribute("projetos", projetos);
        model.addAttribute("message",message);
        return "projeto";    }

    @GetMapping("/adicionarProjeto")
    public String AdicionarProjeto(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("projeto", new Projeto());
        getEnums(model);
        model.addAttribute("pessoa",pessoaService.listarPessoasFuncionario());
        return "AdicionarProjeto";
    }

    @PostMapping("/salvarProjeto")
    public String salvarProjeto(@ModelAttribute("projeto") Projeto projeto, RedirectAttributes redirectAttributes ) throws ParseException {
        projetoService.salvarProjeto(projeto);
        redirectAttributes.addFlashAttribute("opera", "success");
        redirectAttributes.addFlashAttribute("message", "Salvo com Successo");
        return "redirect:/projetos/listarProjetos";
    }

    @GetMapping("/editarProjeto/{id}")
    public String editarProjeto(@PathVariable Long id, Model model){
        model.addAttribute("projeto",projetoService.buscarPorId(id));
        getEnums(model);
        return "EditarProjeto";
    }

    @PostMapping("/EditarProjeto/{id}")
    public String editarProjeto(@PathVariable Long id, Projeto projeto, RedirectAttributes redirectAttributes){
        projetoService.atualizarProjeto(id,projeto);
        redirectAttributes.addFlashAttribute("opera", "success");
        redirectAttributes.addFlashAttribute("message", "Editado com Successo");
        return "redirect:/projetos/listarProjetos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProjetos(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            projetoService.deletarProjeto(id);
            redirectAttributes.addFlashAttribute("message", "Deletado com Sucesso");
            redirectAttributes.addFlashAttribute("opera", "success");
        } catch (RuntimeException e) {
            String message = e.getMessage();
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("opera", "error");
        }

        return "redirect:/projetos/listarProjetos";
    }

    @GetMapping("/associarMembros")
    public String associarMembros(Model model){
        List<Projeto> projetos = projetoService.listarProjetos();
        List<Pessoa> pessoasFuncionario = pessoaService.listarPessoasFuncionario();
        model.addAttribute("projetos",projetos);
        model.addAttribute("pessoas",pessoasFuncionario);
        return "AssociarMembro";
    }

    @GetMapping("/AssociarMembros/{pessoaId}/{projetoId}")
    public String associarMembros(@PathVariable Long pessoaId,@PathVariable Long projetoId, RedirectAttributes redirectAttributes ){
        try {
            membroService.associarPessoaAoProjeto(pessoaId,projetoId);
            redirectAttributes.addFlashAttribute("operacao", "success");
            redirectAttributes.addFlashAttribute("message", "Associado com Sucesso");

        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String message = e.getMessage();
            redirectAttributes.addFlashAttribute("operacao", "error");
            redirectAttributes.addFlashAttribute("message", message);
        }
        return "redirect:/projetos/listarProjetos";
    }


    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }


    private void getEnums(Model model) {
        List<StatusProjeto> status = new ArrayList<>(EnumSet.allOf(StatusProjeto.class));
        model.addAttribute("status", status);
        List<RiscoProjeto> risco = new ArrayList<>(EnumSet.allOf(RiscoProjeto.class));
        model.addAttribute("risco", risco);
    }

}

