package com.exam;

import com.exam.application.ExamApplicationService;
import com.exam.domain.repository.Repositories.ExamAttemptRepository;
import com.exam.domain.repository.Repositories.QuestionBankRepository;
import com.exam.domain.service.AttemptManager;
import com.exam.domain.service.ExportService;
import com.exam.domain.service.GradingService;
import com.exam.infrastructure.CsvQuestionBankRepository;
import com.exam.infrastructure.FileExportService;
import com.exam.infrastructure.InMemoryExamAttemptRepository;
import com.exam.presentation.SwingUI;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.SwingUtilities;

/**
 * Main para ejecutar la interfaz gráfica Swing.
 * Configura e inyecta todas las dependencias del sistema.
 */
public class MainSwing {

  public static void main(String[] args) {
    String csvPath = "banco_preguntas_test.csv";
    generarCsvDePrueba(csvPath);

    // 1. Infraestructura y Repositorios
    QuestionBankRepository questionRepo = new CsvQuestionBankRepository(csvPath);
    ExamAttemptRepository attemptRepo = new InMemoryExamAttemptRepository();

    // 2. Servicios de Dominio
    AttemptManager attemptManager = new AttemptManager(attemptRepo);
    GradingService gradingService = new GradingService();

    // 3. Servicio de Exportación (NUEVO)
    ExportService exportService = new FileExportService();

    // 4. Application Service (con TODAS las dependencias)
    ExamApplicationService appService = new ExamApplicationService(
        questionRepo,
        attemptRepo,
        attemptManager,
        gradingService,
        exportService
    );

    // 5. Lanzamiento de la interfaz gráfica
    SwingUtilities.invokeLater(() -> {
      try {
        javax.swing.UIManager.setLookAndFeel(
            javax.swing.UIManager.getSystemLookAndFeelClassName()
        );
      } catch (Exception e) {
        System.out.println("Error al cargar interfaz: " + e.getMessage());
      }

      SwingUI ui = new SwingUI(appService);
      ui.start();
    });
  }

  // Método para generar CSV de prueba
  private static void generarCsvDePrueba(String path) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
      writer.println("TIPO;ENUNCIADO;OPCIONES;RESPUESTAS_CORRECTAS");
      writer.println("SC;¿Cuál es la capital de Colombia?;Bogotá,Lima,Quito;Bogotá");
      writer.println("TF;Java es un lenguaje de programación orientado a objetos;;V");
      writer.println(
          "FB;El principio SOLID que dicta que una clase debe tener una sola razón para cambiar es el principio de _______;;responsabilidad unica");
      writer.println(
          "MC;¿Cuáles son lenguajes de programación tipados estáticamente?;Java,Python,C++,JavaScript;Java,C++");
    } catch (IOException e) {
      System.err.println("Error al crear el CSV de prueba: " + e.getMessage());
    }
  }
}