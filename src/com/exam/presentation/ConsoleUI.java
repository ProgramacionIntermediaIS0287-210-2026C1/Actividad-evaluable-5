package com.exam.presentation;

import com.exam.application.ExamApplicationService;
import com.exam.application.dto.DTOs.CalificacionDTO;
import com.exam.application.dto.DTOs.ExamAttemptDTO;
import com.exam.domain.model.Question;
import com.exam.domain.vo.ValueObjects.AnswerText;
import com.exam.domain.vo.ValueObjects.StudentId;
import java.util.Scanner;

/**
 * Capa de Presentación: interactúa con el usuario y traslada peticiones al
 * Application Service.
 */
public class ConsoleUI {
  private final ExamApplicationService service;
  private final Scanner scanner;

  public ConsoleUI(ExamApplicationService service) {
    this.service = service;
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    System.out.println("=== SISTEMA DE EVALUACIÓN (DDD & CLEAN ARCHITECTURE) ===");
    System.out.print("Ingrese su ID de estudiante: ");
    StudentId studentId = new StudentId(scanner.nextLine());

    try {
      ExamAttemptDTO attempt = service.iniciarExamen(studentId);
      System.out.println("\n--- EXAMEN INICIADO ---");

      int count = 1;
      for (Question q : attempt.questions()) {
        System.out.println("\nPregunta " + count++ + " de " + attempt.questions().size());
        q.displayFormat();

        System.out.print("Su respuesta: ");
        AnswerText answer = new AnswerText(scanner.nextLine());

        service.responderPregunta(studentId, q.getId(), answer);
      }

      CalificacionDTO resultado = service.finalizarExamen(studentId);

      System.out.println("\n=== RESULTADOS ===");
      System.out.println("Puntuación Final: " + resultado.puntaje() + " / " + resultado.total());
      double porcentaje = ((double) resultado.puntaje() / resultado.total()) * 100;
      System.out.println("Porcentaje de acierto: " + porcentaje + "%");

    } catch (IllegalStateException | IllegalArgumentException e) {
      System.out.println("\n[ERROR]: " + e.getMessage());
    }
  }
}