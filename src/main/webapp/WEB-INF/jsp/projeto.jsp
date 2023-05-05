<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Projetos</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <link rel="stylesheet" href="/../../css/projeto.css">
</head>
<body>
<div class="container-fluid">
    <div class="header">
        <h1>Lista de Projetos</h1>
        <div >
            <button type="button" class=" btn btn-dark btn-lg">
                <a href="/projetos/adicionarProjeto">Criar Projeto</a>
            </button>
            <button type="button" class=" btn btn-dark btn-lg">
                <a href="/projetos/associarMembros">Associar Membro</a>
            </button>
        </div>
    </div>
    <div id="alarmmsg"></div>
    <table class="table table-bordered table-striped table-dark">
        <thead >
        <tr>
            <th>Nome</th>
            <th>Data de Início</th>
            <th>Gerente Responsável</th>
            <th>Previsão de Término</th>
            <th>Data Real de Término</th>
            <th>Orçamento Total</th>
            <th>Descrição</th>
            <th>Status</th>
            <th>Risco</th>
            <th>Editar</th>
            <th>Excluir</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projetos}" var="projeto">
            <tr>
                <td>${projeto.nome}</td>
                <td><fmt:formatDate value="${projeto.dataInicio}" pattern="yyyy-MM-dd" /></td>
                <td>${projeto.pessoa.getNome()}</td>
                <td><fmt:formatDate value="${projeto.dataPrevisaoFim}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${projeto.dataFim}" pattern="yyyy-MM-dd" /></td>
                <td>${projeto.orcamento}</td>
                <td>${projeto.descricao}</td>
                <td>${projeto.status.getDescricao()}</td>
                <td>${projeto.risco.getDescricao()}</td>
                <td>
                    <button type="button" class="btn btn-light">
                        <a href="/projetos/editarProjeto/${projeto.id}">Editar</a>
                    </button>
                </td>
                <td>
                    <button type="button" class="btn excluir btn-light">
                        <a href="/projetos/excluir/${projeto.id}">Excluir</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Adicionando o jQuery e o Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>


<script th:inline="javascript">
    window.onload = function() {

        toastr["${opera}"]("${message}", "Notificação", {
            'positionClass': "md-toast-bottom-center"
        })

    }
</script>


</body>
</html>