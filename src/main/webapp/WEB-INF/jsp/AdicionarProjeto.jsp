<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

    <head>
        <meta charset="UTF-8">
        <title>Criar um Projeto</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <link rel="stylesheet" href="../css/EditarSalvarProjeto.css">
        <script
                src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
                src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script
                src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    </head>
    <body>
        <div class="container">
            <h1 class="p-3"> Criar um Projeto </h1>
            <form:form action="/projetos/salvarProjeto" method="post" modelAttribute="projeto">
                <div class="row">
                    <div class="form-group col-6">
                        <label for="nome">Nome</label>
                        <div>
                            <form:input type="text" path="nome" id="nome"
                                        class="form-control input-sm" required="required" />
                        </div>
                    </div>
                    <div class="form-group col-6">
                        <label for="dataInicio">Data de Inicio</label>
                        <div>
                            <form:input type="date" path="dataInicio" id="dataInicio"
                                        class="form-control input-sm"  />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-6">
                        <label for="dataPrevisaoFim">Previsão de Fim</label>
                        <div>
                            <form:input type="date" path="dataPrevisaoFim" id="dataPrevisaoFim"
                                        class="form-control input-sm"  />
                        </div>
                    </div>
                    <div class="form-group col-6">
                        <label for="dataFim">Data Oficial de Fim</label>
                        <div>
                            <form:input type="date" path="dataFim" id="dataFim"
                                        class="form-control input-sm"  />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-6">
                        <label for="descricao">Descrição</label>
                        <div>
                            <form:textarea rows="4" cols="50" type="textarea" path="descricao" id="descricao"
                                        class="form-control input-sm"  />
                        </div>
                    </div>
                    <div class="form-group col-6">
                        <label for="orcamento">Orçamento</label>
                        <div>
                            <form:input type="number" path="orcamento" id="orcamento"
                                        class="form-control"  />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-4">
                        <label for="pessoa">Gerente</label>
                        <select id="pessoa" class="custom-select" name="pessoa">
                            <c:forEach items="${pessoa}" var="pessoa" >
                                <option class="dropdown-item" value="${pessoa.getId()}">
                                        ${pessoa.getNome()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group col-4">
                        <label for="risco">Risco</label>
                        <select id="risco" class="custom-select" name="risco">
                            <c:forEach items="${risco}" var="risco">
                                <option class="dropdown-item" value="${risco}">
                                        ${risco.getDescricao()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group col-4 ">
                        <label for="status">Status</label>
                        <select id="status" class="custom-select" name="status">
                            <c:forEach items="${status}" var="status">
                                <option class="dropdown-item" value="${status}">
                                        ${status.getDescricao()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-2">
                        <button type="submit" value="Salvar" class="btn btn-success">Salvar</button>
                    </div>
                    <div class="col-md-2">
                        <button value="Voltar" class="btn btn-danger">
                            <a href="/projetos/listarProjetos">Voltar</a>
                        </button>
                    </div>
                </div>

            </form:form>
        </div>
    </body>

</html>