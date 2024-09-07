<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Clasificación Olímpica de BMX</title>
    <!-- Incluir el archivo de fuentes locales -->
    <link rel="stylesheet" href="fonts.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
<!-- Encabezado con estilo mejorado -->
<header class="bg-primary text-white py-3 mb-4">
    <div class="container">
        <!-- Título con fuente Fascinate Inline -->
        <h1>Clasificación Olímpica de BMX</h1>
    </div>
</header>

<main class="container">
    <!-- Formulario de búsqueda -->
    <form action="competitors" method="get" class="mb-4">
        <input type="hidden" name="action" value="search">
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="searchName">Nombre:</label>
                <input type="text" class="form-control" id="searchName" name="searchName" maxlength="40">
            </div>
            <div class="col-md-4 mb-3">
                <label for="searchCountry">País:</label>
                <input type="text" class="form-control" id="searchCountry" name="searchCountry" maxlength="40">
            </div>
            <div class="col-md-4 mb-3">
                <label for="searchScore">Puntaje Total Mayor a:</label>
                <input type="number" class="form-control" id="searchScore" name="searchScore">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>

    <!-- Botones de acciones -->
    <div class="mb-4">
        <a href="addCompetitor.jsp" class="btn btn-success">Añadir Competidor</a>
        <a href="competitors?action=generateRandom" class="btn btn-info">Generar Competidor Aleatorio</a>
        <a href="competitors?action=simulateRace" class="btn btn-warning">Simular Carrera</a>
    </div>

    <!-- Tabla responsiva de competidores -->
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th><a href="competitors?sortField=firstName&sortDirection=asc">Nombre ▲</a> | <a href="competitors?sortField=firstName&sortDirection=desc">▼</a></th>
                <th><a href="competitors?sortField=lastName&sortDirection=asc">Apellido ▲</a> | <a href="competitors?sortField=lastName&sortDirection=desc">▼</a></th>
                <th>Fecha de Nacimiento</th>
                <th><a href="competitors?sortField=country&sortDirection=asc">País ▲</a> | <a href="competitors?sortField=country&sortDirection=desc">▼</a></th>
                <th>Código País</th>
                <th>Puntaje R1</th>
                <th>Puntaje R2</th>
                <th>Puntaje R3</th>
                <th><a href="competitors?sortField=totalScore&sortDirection=asc">Puntaje Total ▲</a> | <a href="competitors?sortField=totalScore&sortDirection=desc">▼</a></th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="competitor" items="${competitors}" varStatus="status">
                <tr class="${status.index == 0 ? 'gold-medal' : status.index == 1 ? 'silver-medal' : status.index == 2 ? 'bronze-medal' : ''}">
                    <td>
                        <a href="competitors?action=viewDetails&index=${status.index}">${competitor.firstName}</a>
                    </td>
                    <td>${competitor.lastName}</td>
                    <td>${competitor.birthDate}</td>
                    <td>${competitor.country}</td>
                    <td>${competitor.countryCode}</td>
                    <td>${competitor.roundScores[0]}</td>
                    <td>${competitor.roundScores[1]}</td>
                    <td>${competitor.roundScores[2]}</td>
                    <td>${competitor.totalScore}</td>
                    <td>
                        <a href="editCompetitor.jsp?index=${status.index}" class="btn btn-sm btn-outline-secondary">Editar</a>
                        <a href="competitors?action=delete&index=${status.index}" class="btn btn-sm btn-outline-danger">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Botones para exportar la tabla -->
    <form action="competitors" method="get">
        <button type="submit" name="action" value="exportExcel" class="btn btn-primary">Exportar a Excel</button>
        <button type="submit" name="action" value="exportJSON" class="btn btn-primary">Exportar a JSON</button>
    </form>

</main>

<footer class="bg-dark text-white py-3 mt-4">
    <div class="container text-center">
        <p>Fecha: <%= new java.util.Date() %></p>
        <p>Nombre Completo: Santiago Cardona López</p>
    </div>
</footer>
</body>
</html>
