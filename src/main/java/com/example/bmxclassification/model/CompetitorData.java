package com.example.bmxclassification.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CompetitorData {

    // Lista de competidores en memoria
    private static List<Competitor> competitors = new ArrayList<>();

    // Lista de países para seleccionar aleatoriamente
    private static final String[] COUNTRIES = {
            "Colombia", "France", "USA", "Canada", "Brazil", "Argentina",
            "Mexico", "Netherlands", "Australia", "Spain", "Italy", "Germany",
            "Japan", "China", "South Korea", "Russia", "Great Britain", "Switzerland",
            "Belgium", "Denmark"
    };
    // Método para generar el código de país en función del nombre del país
    public static String generateCountryCode(String country) {
        switch (country.toLowerCase()) {
            case "colombia": return "COL";
            case "france": return "FRA";
            case "usa": return "USA";
            case "canada": return "CAN";
            case "brazil": return "BRA";
            case "argentina": return "ARG";
            case "mexico": return "MEX";
            case "netherlands": return "NLD";
            case "australia": return "AUS";
            case "spain": return "ESP";
            case "italy": return "ITA";
            case "germany": return "DEU";
            case "japan": return "JPN";
            case "china": return "CHN";
            case "south korea": return "KOR";
            case "russia": return "RUS";
            case "great britain": return "GBR";
            case "switzerland": return "CHE";
            case "belgium": return "BEL";
            case "denmark": return "DNK";
            default: return "UNK";  // Código para país desconocido
        }
    }



    // Inicialización estática con competidores de ejemplo
    static {
        // Cargar datos iniciales de competidores
        competitors.add(new Competitor("Juan", "PÉREZ", LocalDate.of(1995, 5, 12), "Colombia", CompetitorData.generateCountryCode("Colombia"), new int[]{85, 90, 88, 92, 87, 95, 93, 89, 91, 90}, 900, "Competidor experimentado con múltiples medallas."));
        competitors.add(new Competitor("Élise", "LEFEVRE", LocalDate.of(1999, 7, 23), "France", CompetitorData.generateCountryCode("France"), new int[]{75, 80, 82, 78, 85, 88, 84, 81, 83, 79}, 815, "Promesa joven del BMX."));
        competitors.add(new Competitor("Emily", "JOHNSON", LocalDate.of(2000, 3, 9), "USA", CompetitorData.generateCountryCode("USA"), new int[]{68, 72, 70, 75, 78, 77, 79, 76, 73, 80}, 748, "Competidora destacada en eventos internacionales."));
        competitors.add(new Competitor("Liam", "SMITH", LocalDate.of(1997, 12, 15), "Canada", CompetitorData.generateCountryCode("Canada"), new int[]{80, 85, 88, 86, 84, 87, 83, 82, 81, 79}, 835, "Participante en dos juegos olímpicos."));
        competitors.add(new Competitor("Lucas", "SILVA", LocalDate.of(1996, 1, 3), "Brazil", CompetitorData.generateCountryCode("Brazil"), new int[]{90, 92, 88, 85, 84, 83, 87, 89, 91, 93}, 882, "Competidor de alto rendimiento."));
        competitors.add(new Competitor("Mateo", "FERNÁNDEZ", LocalDate.of(1998, 11, 11), "Argentina", CompetitorData.generateCountryCode("Argentina"), new int[]{65, 70, 72, 69, 68, 71, 74, 73, 76, 77}, 715, "Nuevo talento en BMX."));
        competitors.add(new Competitor("Sofía", "RAMÍREZ", LocalDate.of(2001, 6, 25), "Mexico", CompetitorData.generateCountryCode("Mexico"), new int[]{85, 87, 88, 84, 82, 81, 80, 86, 83, 89}, 845, "Competidora en ascenso."));
        competitors.add(new Competitor("Daan", "JANSEN", LocalDate.of(1995, 9, 30), "Netherlands", CompetitorData.generateCountryCode("Netherlands"), new int[]{90, 88, 86, 84, 85, 87, 89, 90, 91, 93}, 883, "Veterano con experiencia mundial."));
        competitors.add(new Competitor("Emily", "WILSON", LocalDate.of(1997, 2, 18), "Australia", CompetitorData.generateCountryCode("Australia"), new int[]{78, 80, 82, 81, 79, 77, 75, 76, 74, 73}, 775, "Compite en BMX desde los 10 años."));
        competitors.add(new Competitor("Alejandro", "HERNÁNDEZ", LocalDate.of(1999, 4, 22), "Spain", CompetitorData.generateCountryCode("Spain"), new int[]{88, 90, 87, 85, 83, 84, 82, 81, 79, 80}, 839, "Ganador de múltiples campeonatos locales."));
    }



    // Método para obtener la lista de competidores
    public static List<Competitor> getCompetitors() {
        return competitors;
    }

    // Método para añadir un nuevo competidor
    public static void addCompetitor(Competitor competitor) {
        competitors.add(competitor);
        sortCompetitorsByTotalScore(); // Ordenar después de añadir
    }

    // Método para eliminar un competidor por su índice
    public static void removeCompetitor(int index) {
        if (index >= 0 && index < competitors.size()) {
            competitors.remove(index);
            sortCompetitorsByTotalScore(); // Ordenar después de eliminar
        }
    }

    // Método para actualizar la información de un competidor
    public static void updateCompetitor(int index, Competitor competitor) {
        if (index >= 0 && index < competitors.size()) {
            competitors.set(index, competitor);
            sortCompetitorsByTotalScore(); // Ordenar después de actualizar
        }
    }

    // Método para generar un competidor aleatorio
    public static void addRandomCompetitor() {
        Random rand = new Random();
        String[] firstNames = {"Carlos", "Juan", "Ana", "Maria", "Luis"};
        String[] lastNames = {"Perez", "Garcia", "Lopez", "Martinez", "Rodriguez"};
        String[] countries = {"Colombia", "Francia", "Estados Unidos", "Canada", "Brasil", "Argentina", "Mexico", "Netherlands", "Australia", "Spain"};

        String firstName = firstNames[rand.nextInt(firstNames.length)].toLowerCase();
        String lastName = lastNames[rand.nextInt(lastNames.length)].toUpperCase();
        LocalDate birthDate = LocalDate.of(1995, 6, 15).minusYears(rand.nextInt(12)); // Fecha de nacimiento aleatoria entre 18-30 años
        String country = countries[rand.nextInt(countries.length)];
        String countryCode = CompetitorData.generateCountryCode(country); // Generar código del país
        String description = "Competidor generado aleatoriamente";

        // Puntajes aleatorios para hasta 10 rondas
        int[] roundScores = new int[10];
        int totalScore = 0;
        for (int i = 0; i < roundScores.length; i++) {
            roundScores[i] = 1 + rand.nextInt(10); // Puntajes aleatorios de 1 a 10
            totalScore += roundScores[i];
        }

        // Crear el competidor aleatorio
        Competitor randomCompetitor = new Competitor(firstName, lastName, birthDate, country, countryCode, roundScores, totalScore, description);

        // Agregar el competidor a la lista
        competitors.add(randomCompetitor);
    }




    // Método para simular una carrera que actualiza los puntajes de manera aleatoria sin empates
    public static void simulateRace() {
        Random random = new Random();
        Set<Integer> uniqueScores = new HashSet<>();

        // Generar puntajes únicos
        while (uniqueScores.size() < competitors.size()) {
            uniqueScores.add(1 + random.nextInt(1000)); // Aseguramos que haya suficiente rango de puntajes
        }

        List<Integer> scoresList = new ArrayList<>(uniqueScores);
        Collections.shuffle(scoresList); // Mezclar los puntajes para mayor aleatoriedad

        // Asignar puntajes a cada competidor
        for (int i = 0; i < competitors.size(); i++) {
            Competitor competitor = competitors.get(i);
            int totalScore = scoresList.get(i);
            int[] newScores = new int[10];

            // Distribuir el puntaje total en 10 rondas
            int scoreRemaining = totalScore;
            for (int j = 0; j < 9; j++) {
                newScores[j] = random.nextInt(scoreRemaining / 2 + 1); // Disminuir rango para asegurar que siempre haya puntaje restante
                scoreRemaining -= newScores[j];
            }
            newScores[9] = scoreRemaining; // Asignar el puntaje restante a la última ronda

            competitor.setRoundScores(newScores);
            competitor.setTotalScore(totalScore);
        }

        // Ordenar competidores por el puntaje total en orden descendente
        sortCompetitorsByTotalScore(); // Asegurar el orden después de la simulación
    }

    // Método para obtener la lista de países
    public static List<String> getCountries() {
        return List.of(COUNTRIES);
    }
    // Método para ordenar la lista de competidores por puntaje total
    public static void sortCompetitors(String sortField, String sortDirection) {
        Comparator<Competitor> comparator;

        // Definir el criterio de ordenamiento
        switch (sortField) {
            case "firstName":
                comparator = Comparator.comparing(Competitor::getFirstName);
                break;
            case "lastName":
                comparator = Comparator.comparing(Competitor::getLastName);
                break;
            case "totalScore":
            default:
                comparator = Comparator.comparingInt(Competitor::getTotalScore);
                break;
        }

        // Ordenar en orden ascendente o descendente
        if ("desc".equals(sortDirection)) {
            comparator = comparator.reversed();
        }

        competitors.sort(comparator);
    }

    // Sobrecarga del método para ordenar solo por puntaje total de manera descendente
    public static void sortCompetitorsByTotalScore() {
        competitors.sort((c1, c2) -> Integer.compare(c2.getTotalScore(), c1.getTotalScore()));
    }

}
