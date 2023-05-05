package br.com.minhaempresa.exceptions;

public class PessoaNaoFuncionarioException extends RuntimeException{

    public PessoaNaoFuncionarioException(String msg) {
        super(msg);
    }
}
