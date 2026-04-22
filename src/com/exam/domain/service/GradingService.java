package com.exam.domain.service;

import com.exam.domain.model.ExamAttempt;
import com.exam.domain.model.Question;
import com.exam.domain.vo.ValueObjects.AnswerText;
import com.exam.domain.vo.ValueObjects.Calificacion;
import java.util.List;

/**
 * Servicio de dominio que implementa la lógica de calificación de un examen.
 * Centraliza la evaluación para no sobrecargar el Agregado ExamAttempt (DDD).
 */
public class GradingService {
  public Calificacion calificar(ExamAttempt attempt) {
    List<Question> questions = attempt.getQuestions();
    int correctCount = 0;
    
    for (Question question : questions) {
      AnswerText studentAnswer = attempt.getAnswers().get(question.getId());
      if (studentAnswer != null && question.isCorrect(studentAnswer)) {
        correctCount++;
      }
    }
    
    return new Calificacion(correctCount, questions.size());
  }
}
