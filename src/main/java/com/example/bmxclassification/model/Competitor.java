package com.example.bmxclassification.model;


import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

import static com.example.bmxclassification.model.CompetitorData.generateCountryCode;

public class Competitor {
    private String firstName;      // Nombre del competidor en minúsculas
    private String lastName;       // Apellido del competidor en mayúsculas
    private LocalDate birthDate;   // Fecha de nacimiento
    private String country;        // Nombre del país
    private String countryCode;    // Código ISO 3166-1 alpha-3 del país
    private int[] roundScores;     // Puntajes por ronda (hasta 10 rondas)
    private int totalScore;        // Puntaje total acumulado
    private String description;    // Descripción del competidor
    // Nueva propiedad 'index'
    private int index;
    // Constructor simplificado (5 parámetros) para el método `addCompetitor`
    public Competitor(String firstName, String lastName, LocalDate birthDate, String country, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.countryCode = generateCountryCode(country); // Usar el método para obtener el código de país
        this.roundScores = new int[10]; // Inicializar con un array de puntajes vacíos
        this.totalScore = 0; // Puntaje inicial
        this.description = description;
    }

    public Competitor(String firstName, String lastName, LocalDate birthDate, String country, String countryCode, int[] roundScores, int totalScore, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.countryCode = countryCode;
        this.roundScores = roundScores;
        this.totalScore = totalScore;
        this.description = description;
    }


    // Métodos getter y setter para la propiedad 'index'
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // Genera el código ISO 3166-1 alpha-3 del país en función del nombre del país


    // Obtiene el nombre del competidor
    public String getFirstName() {
        return firstName;
    }

    // Establece el nombre del competidor
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Obtiene el apellido del competidor
    public String getLastName() {
        return lastName;
    }

    // Establece el apellido del competidor
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Obtiene la fecha de nacimiento
    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Establece la fecha de nacimiento
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // Obtiene el país del competidor
    public String getCountry() {
        return country;
    }

    // Establece el país del competidor y actualiza el código de país
    public void setCountry(String country) {
        this.country = country;
        this.countryCode = generateCountryCode(country);
    }

    // Obtiene el código de país
    public String getCountryCode() {
        return countryCode;
    }

    // Obtiene los puntajes por ronda
    public int[] getRoundScores() {
        return roundScores;
    }

    // Establece los puntajes por ronda
    public void setRoundScores(int[] roundScores) {
        this.roundScores = roundScores;
        updateTotalScore();
    }

    // Calcula la edad del competidor
    public int getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    // Obtiene la descripción del competidor
    public String getDescription() {
        return description;
    }

    // Establece la descripción del competidor
    public void setDescription(String description) {
        this.description = description;
    }

    // Obtiene el puntaje total acumulado
    public int getTotalScore() {
        return totalScore;
    }

    // Establece el puntaje total acumulado
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    // Método para actualizar el puntaje total acumulado basado en los puntajes de las rondas
    private void updateTotalScore() {
        this.totalScore = Arrays.stream(this.roundScores).sum();
    }

    // Representación en cadena del objeto Competitor
    @Override
    public String toString() {
        return "Competitor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", roundScores=" + Arrays.toString(roundScores) +
                ", totalScore=" + totalScore +
                ", description='" + description + '\'' +
                '}';
    }

}