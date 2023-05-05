<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Projeto</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <link rel="stylesheet" href="../../css/Editar.css">
        <script
                src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
                src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script
                src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    </head>

    <body>
        <div class="container">
            <h1>Associar Membros</h1>
            <form onsubmit="return associarMembros()">
                <div class="row">
                    <div class="form-group col-6">
                        <label for="pessoa"> Gerente</label>
                        <select id="pessoa" class="custom-select" name="pessoa">
                            <c:forEach items="${pessoas}" var="pessoa">
                                <option class="dropdown-item" value="${pessoa.getId()}">
                                        ${pessoa.getId()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group col-6">
                        <label for="projeto">Projeto</label>
                        <select value id="projeto" class="custom-select" name="projeto">
                            <c:forEach items="${projetos}" var="projeto">
                                <option class="dropdown-item" value="${projeto.getId()}">
                                        ${projeto.getId()}
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
            </form>
        </div>
    </body>

    <script th:inline="javascript">
        window.onload = function() {
            toastr["${operacao}"]("${message}", "Notificação", {
                'positionClass': "md-toast-bottom-right"
            })

        }
            function associarMembros() {
            var pessoaId = document.getElementById("pessoa").value;
            var projetoId = document.getElementById("projeto").value;
            var url = "/projetos/AssociarMembros/"+ pessoaId  + "/" + projetoId;
            window.location.href = url;
            return false;
        }
    </script>


</html>