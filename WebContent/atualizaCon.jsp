<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizar Consulta</title>
<link rel="stylesheet" href="resources/css/estilo.css">

</head>
<body>

<c:import url="cabecalho.jsp"></c:import>

<form class="form-container" action="gravar" method="post">

           
                <c:forEach var="con" items="${conlista}">
               		
                    <input class="form-field" type="hidden" name="id" size=5 maxlength=5 value="${con.id}" />
                    
                    <div class="form-title">Nome Paciente</div>
                    <select class="form-field" name="nomepac">
                    <option><c:out value="${con.pac.nome}"/></option>
                    <c:forEach var="pac" items="${paclista}">
                    <option><c:out value="${pac.nome}"/></option>
                    </c:forEach>
                    </select>
                    <div class="form-title">Nome Medico</div>
                    <select class="form-field" name="nomemed">
                    <option><c:out value="${con.med.nome}"/></option>
                    <c:forEach var="med" items="${medlista}">
                    <option><c:out value="${med.nome}"/></option>
                    </c:forEach>
                    </select>
                    <div class="form-title">Especialidade</div>
                    <select class="form-field" name="esp">
                    <option><c:out value="${con.med.especialidade}"/></option>
                    <option>Clinico Geral</option>
					<option>Cardiologista</option>
					<option>Pediatra</option>
					<option>Psicologo</option>
                    </select>
                    <div class="form-title">Data Consulta</div>
                    <input class="form-field" type="text" name="dtcons" size=10 maxlength=10 value="${con.dtcons}"pattern="\d{2}\/\d{2}\/\d{4}\" placeholder="dd/mm/aaaa" required />
                    <div class="form-title">Hora</div>
                    <input class="form-field" type="time" name="hora" size=10 maxlength=60 value="${con.hora}" required />
                    <div class="form-title">Observação</div>    
                    <textarea class="form-field" name="obs" cols=60 rows=3 required>${con.obs} </textarea><br />
                    <div class="form-title">Retorno</div>
                    <select class="form-field" name="retorno">
                    <option><c:out value="${con.retorno}"/></option>
                    <option>SIM</option>
                    <option>NAO</option>
                    </select>
                    <input type="hidden" name="opc" value="atualizacon"/></br>
                    <input class="form-title" type="submit" value="Gravar"/></br>
                    <a class="form-title" href="listarConsulta" value="Voltar">Voltar</a>
                
                </c:forEach>
            
        
</form>
<c:import url="rodape.jsp"></c:import>
</body>
</html>