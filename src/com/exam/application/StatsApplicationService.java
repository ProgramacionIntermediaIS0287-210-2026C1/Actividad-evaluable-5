package com.exam.application;

import com.exam.domain.model.ExamAttempt;
import com.exam.domain.repository.Repositories.ExamAttemptRepository;
import java.util.List;

public class StatsApplicationService {

    private final ExamAttemptRepository attemptRepo;

    public StatsApplicationService(ExamAttemptRepository attemptRepo) {
        this.attemptRepo = attemptRepo;
    }

    public int totalEstudiantesQuePresentaron() {
        return attemptRepo.findAll().size();
    }

    public double promedioGlobal() {
        List<ExamAttempt> attempts = attemptRepo.findAll();

        double suma = 0;
        int count = 0;

        for (ExamAttempt attempt : attempts) {
            if (attempt.estaFinalizado()) {
                suma += attempt.getCalificacion().puntaje();
                count++;
            }
        }

        return count == 0 ? 0 : suma / count;
    }
}
