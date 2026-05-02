package com.exam.infrastructure;

import com.exam.domain.service.ExportService;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FileExportService implements ExportService {

    private static final String FILE_PATH = "calificaciones.txt";

    @Override
    public void exportarCalificacion(String studentId, String resultado) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {

            // 📌 Formato bonito de fecha
            String fecha = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // 📌 Escritura final
            writer.write(fecha + " | ID: " + studentId + " | " + resultado + System.lineSeparator());

        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }
}