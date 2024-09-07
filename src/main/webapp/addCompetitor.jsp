<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Añadir Competidor</title>
    <!-- Incluir el archivo de fuentes locales -->
    <link rel="stylesheet" href="fonts.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<header class="bg-primary text-white py-3 mb-4">
    <div class="container">
        <!-- Título con fuente Fascinate Inline -->
        <h1 class="text-center">Añadir Nuevo Competidor</h1>
    </div>
</header>

<main class="container">
    <!-- Mostrar mensaje de error si existe -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <!-- Formulario para añadir competidor -->
    <form action="competitors" method="post">
        <input type="hidden" name="action" value="add">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="firstName">Nombre:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" maxlength="40" required>
            </div>
            <div class="form-group col-md-6">
                <label for="lastName">Apellido:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" maxlength="40" required>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="birthDate">Fecha de Nacimiento:</label>
                <input type="date" class="form-control" id="birthDate" name="birthDate" required>
            </div>
            <div class="form-group col-md-6">
                <label for="country">País:</label>
                <select id="country" class="form-control" name="country" required>
                    <option value="">Seleccione un país</option>
                    <option value="Colombia">Colombia</option>
                    <option value="France">France</option>
                    <option value="USA">USA</option>
                    <option value="Canada">Canada</option>
                    <option value="Brazil">Brazil</option>
                    <option value="Argentina">Argentina</option>
                    <option value="Mexico">Mexico</option>
                    <option value="Netherlands">Netherlands</option>
                    <option value="Australia">Australia</option>
                    <option value="Spain">Spain</option>
                    <option value="Italy">Italy</option>
                    <option value="Germany">Germany</option>
                    <option value="Japan">Japan</option>
                    <option value="China">China</option>
                    <option value="South Korea">South Korea</option>
                    <option value="Russia">Russia</option>
                    <option value="Great Britain">Great Britain</option>
                    <option value="Switzerland">Switzerland</option>
                    <option value="Belgium">Belgium</option>
                    <option value="Denmark">Denmark</option>
                    <!-- Añadir más opciones aquí -->
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="description">Descripción:</label>
            <textarea id="description" class="form-control" name="description" maxlength="200" required></textarea>
        </div>

        <!-- Botón con ícono de FontAwesome -->
        <button type="submit" class="btn btn-success">
            Añadir Competidor
        </button>
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
