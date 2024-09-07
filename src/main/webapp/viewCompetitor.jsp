<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Detalles del Competidor</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="fonts.css">
</head>
<body>
<header class="bg-primary text-white py-3 mb-4">
    <div class="container">
        <h1 class="text-center">Detalles del Competidor</h1>
    </div>
</header>
<main class="container">
    <h2>${competitor.firstName} ${competitor.lastName}</h2>

    <p><strong>Fecha de Nacimiento:</strong> ${competitor.birthDate}</p>
    <p><strong>País:</strong> ${competitor.country}</p>
    <p><strong>Código de País:</strong> ${competitor.countryCode}</p>
    <p><strong>Puntaje Total:</strong> ${competitor.totalScore}</p>
    <p><strong>Descripción:</strong> ${competitor.description}</p>

    <!-- Botón para regresar con ícono -->
    <a href="index.jsp" class="btn btn-secondary mt-4">Regresar</a>
</main>
<footer class="bg-dark text-white py-3 mt-4">
    <div class="container text-center">
        <p>Fecha: <%= new java.util.Date() %></p>
        <p>Nombre Completo: Santiago Cardona López</p>
    </div>
</footer>
</body>
</html>
