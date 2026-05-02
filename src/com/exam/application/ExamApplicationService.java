package com.exam.application;

import com.exam.application.dto.DTOs.CalificacionDTO;
import com.exam.application.dto.DTOs.ExamAttemptDTO;
import com.exam.domain.model.ExamAttempt;
import com.exam.domain.model.Question;
import com.exam.domain.repository.Repositories.ExamAttemptRepository;
import com.exam.domain.repository.Repositories.QuestionBankRepository;
import com.exam.domain.service.AttemptManager;
import com.exam.domain.service.ExportService;
import com.exam.domain.service.GradingService;
import com.exam.domain.vo.ValueObjects.AnswerText;
import com.exam.domain.vo.ValueObjects.Calificacion;
import com.exam.domain.vo.ValueObjects.QuestionId;
import com.exam.domain.vo.ValueObjects.StudentId;
import java.util.List;

/**
 * Application Service que orquesta los casos de uso.
 */
public class ExamApplicationService {
  private final QuestionBankRepository questionRepo;
  private final ExamAttemptRepository attemptRepo;
  private final AttemptManager attemptManager;
  private final GradingService gradingService;
  private final ExportService exportService;
  


  public ExamApplicationService(QuestionBankRepository qRepo, ExamAttemptRepository aRepo,
    AttemptManager aManager, GradingService gService, ExportService exportService)  {
    this.questionRepo = qRepo;
    this.attemptRepo = aRepo;
    this.attemptManager = aManager;
    this.gradingService = gService;
    this.exportService = exportService;
  }

  public ExamAttemptDTO iniciarExamen(StudentId studentId) {
    attemptManager.verificarIntentoActivo(studentId);

    List<Question> questions = questionRepo.findAll();
    if (questions.isEmpty()) {
      throw new IllegalStateException("El banco de preguntas está vacío.");
    }

    ExamAttempt attempt = new ExamAttempt(studentId, questions);
    attemptRepo.save(attempt); // Persiste el inicio

    return new ExamAttemptDTO(studentId, questions);
  }

  public void responderPregunta(StudentId studentId, QuestionId qId, AnswerText answer) {
    ExamAttempt attempt = attemptRepo.findActiveByStudent(studentId)
        .orElseThrow(() -> new IllegalStateException("No se encontró intento activo."));

    attempt.responder(qId, answer);
    attemptRepo.save(attempt);
  }

  public CalificacionDTO finalizarExamen(StudentId studentId) {
    ExamAttempt attempt = attemptRepo.findActiveByStudent(studentId)
        .orElseThrow(() -> new IllegalStateException("No se encontró intento activo."));

    Calificacion calificacion = gradingService.calificar(attempt);
    attempt.finalizar(calificacion);
    attemptRepo.save(attempt);

    // 📌 Obtener fecha actual
    String fecha = java.time.LocalDate.now().toString();

String resultado = fecha + " | ID: " + studentId + " | " 
    + calificacion.puntaje() + "/" + calificacion.total();

exportService.exportarCalificacion(
    studentId.toString(),
    resultado
);

    return new CalificacionDTO(calificacion.puntaje(), calificacion.total());
}
  
}