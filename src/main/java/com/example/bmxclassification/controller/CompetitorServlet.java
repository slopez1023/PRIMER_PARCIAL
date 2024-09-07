package com.example.bmxclassification.controller;

import com.example.bmxclassification.model.Competitor;
import com.example.bmxclassification.model.CompetitorData;
import com.example.bmxclassification.util.ExcelExporter;
import com.example.bmxclassification.util.JSONExporter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/competitors")
public class CompetitorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "delete":
                deleteCompetitor(request, response);
                break;
            case "exportExcel":
                exportToExcel(request, response);
                break;
            case "exportJSON":
                exportToJSON(request, response);
                break;
            case "search":
                searchCompetitors(request, response);
                break;
            case "viewDetails":
                viewCompetitorDetails(request, response);
                break;
            case "simulateRace":
                simulateRace(request, response);
                break;
            case "generateRandom":
                generateRandomCompetitor(request, response);
                break;
            default:
                listCompetitors(request, response, request.getParameter("sortField"), request.getParameter("sortDirection"));
                break;
        }
    }

    private void listCompetitors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listCompetitors(request, response, null, null);
    }

    private void listCompetitors(HttpServletRequest request, HttpServletResponse response, String sortField, String sortDirection) throws ServletException, IOException {
        List<Competitor> competitors = CompetitorData.getCompetitors();

        // Configurar el índice de cada competidor en la lista
        for (int i = 0; i < competitors.size(); i++) {
            competitors.get(i).setIndex(i);
        }

        // Ordenamiento de la lista según los parámetros
        if (sortField != null && sortDirection != null) {
            Comparator<Competitor> comparator;

            switch (sortField) {
                case "firstName":
                    comparator = Comparator.comparing(Competitor::getFirstName);
                    break;
                case "lastName":
                    comparator = Comparator.comparing(Competitor::getLastName);
                    break;
                case "country":
                    comparator = Comparator.comparing(Competitor::getCountry);
                    break;
                case "totalScore":
                    comparator = Comparator.comparingInt(Competitor::getTotalScore);
                    break;
                default:
                    comparator = Comparator.comparing(Competitor::getFirstName);
                    break;
            }

            if ("desc".equals(sortDirection)) {
                comparator = comparator.reversed();
            }

            competitors = competitors.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        request.setAttribute("competitors", competitors);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                addCompetitor(request, response);
                break;
            case "edit":
                editCompetitor(request, response);
                break;
            case "generateRandom":
                generateRandomCompetitor(request, response);
                break;
            case "simulateRace":
                simulateRace(request, response);
                break;
            default:
                listCompetitors(request, response);
                break;
        }
    }

    private void addCompetitor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String birthDateStr = request.getParameter("birthDate");
            String country = request.getParameter("country");
            String description = request.getParameter("description");

            // Verificar que todos los parámetros no sean nulos o vacíos
            if (firstName == null || firstName.isEmpty() ||
                    lastName == null || lastName.isEmpty() ||
                    birthDateStr == null || birthDateStr.isEmpty() ||
                    country == null || country.isEmpty() ||
                    description == null || description.isEmpty()) {
                request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("/addCompetitor.jsp").forward(request, response);
                return;
            }

            // Validar el formato de la fecha
            LocalDate birthDate;
            try {
                birthDate = LocalDate.parse(birthDateStr);
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Formato de fecha inválido.");
                request.getRequestDispatcher("/addCompetitor.jsp").forward(request, response);
                return;
            }

            // Crear un nuevo competidor
            Competitor newCompetitor = new Competitor(firstName.toLowerCase(), lastName.toUpperCase(), birthDate, country, description);
            CompetitorData.addCompetitor(newCompetitor);
            response.sendRedirect("competitors");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al añadir competidor.");
        }
    }

    private void editCompetitor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(request.getParameter("index"));
            Competitor competitor = CompetitorData.getCompetitors().get(index);

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String birthDateStr = request.getParameter("birthDate");
            String country = request.getParameter("country");
            String description = request.getParameter("description");

            // Verificar que todos los parámetros estén presentes
            if (firstName == null || lastName == null || birthDateStr == null || country == null || description == null) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            // Validar el formato de la fecha
            LocalDate birthDate;
            try {
                birthDate = LocalDate.parse(birthDateStr);
            } catch (Exception e) {
                throw new IllegalArgumentException("Formato de fecha inválido.");
            }

            // Actualizar los datos del competidor
            competitor.setFirstName(firstName.toLowerCase());
            competitor.setLastName(lastName.toUpperCase());
            competitor.setBirthDate(birthDate);
            competitor.setCountry(country);
            competitor.setDescription(description);

            CompetitorData.updateCompetitor(index, competitor);
            response.sendRedirect("competitors");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al editar el competidor.");
        }
    }

    private void showEditCompetitorForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        Competitor competitor = CompetitorData.getCompetitors().get(index);

        List<String> countries = CompetitorData.getCountries();
        request.setAttribute("competitor", competitor);
        request.setAttribute("countries", countries);

        request.getRequestDispatcher("/editCompetitor.jsp").forward(request, response);
    }

    private void deleteCompetitor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        CompetitorData.removeCompetitor(index);
        response.sendRedirect("competitors");
    }

    private void exportToExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=competitors.xlsx");
        ExcelExporter.exportCompetitors(response.getOutputStream(), CompetitorData.getCompetitors());
    }

    private void exportToJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=competitors.json");
        JSONExporter.exportCompetitors(response.getWriter(), CompetitorData.getCompetitors());
    }

    private void generateRandomCompetitor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompetitorData.addRandomCompetitor();
        response.sendRedirect("competitors");
    }

    private void simulateRace(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompetitorData.simulateRace();
        response.sendRedirect("competitors");
    }

    private void searchCompetitors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchName = request.getParameter("searchName");
        String searchCountry = request.getParameter("searchCountry");
        String searchScoreStr = request.getParameter("searchScore");

        List<Competitor> filteredCompetitors = CompetitorData.getCompetitors().stream()
                .filter(c -> (searchName == null || searchName.isEmpty() || c.getFirstName().toLowerCase().contains(searchName.toLowerCase())))
                .filter(c -> (searchCountry == null || searchCountry.isEmpty() || c.getCountry().toLowerCase().contains(searchCountry.toLowerCase())))
                .filter(c -> {
                    if (searchScoreStr == null || searchScoreStr.isEmpty()) return true;
                    try {
                        int searchScore = Integer.parseInt(searchScoreStr);
                        return c.getTotalScore() > searchScore;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());

        request.setAttribute("competitors", filteredCompetitors);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void viewCompetitorDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        Competitor competitor = CompetitorData.getCompetitors().get(index);

        request.setAttribute("competitor", competitor);
        request.getRequestDispatcher("/viewCompetitor.jsp").forward(request, response);
    }
}
