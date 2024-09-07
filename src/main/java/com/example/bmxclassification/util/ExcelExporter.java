package com.example.bmxclassification.util;

import com.example.bmxclassification.model.Competitor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelExporter {

    public static void exportCompetitors(OutputStream outputStream, List<Competitor> competitors) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Competitors");

        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nombre");
        headerRow.createCell(1).setCellValue("Apellido");
        headerRow.createCell(2).setCellValue("Fecha de Nacimiento");
        headerRow.createCell(3).setCellValue("País");
        headerRow.createCell(4).setCellValue("Puntaje Total");

        // Llenar datos
        int rowCount = 1;
        for (Competitor competitor : competitors) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(competitor.getFirstName());
            row.createCell(1).setCellValue(competitor.getLastName());
            row.createCell(2).setCellValue(competitor.getBirthDate().toString());
            row.createCell(3).setCellValue(competitor.getCountry());
            row.createCell(4).setCellValue(competitor.getTotalScore());
        }

        workbook.write(outputStream);
        workbook.close();
    }
}