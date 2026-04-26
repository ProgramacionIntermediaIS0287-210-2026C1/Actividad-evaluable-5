package com.exam.infrastructure.persistence.inmemory;

import com.exam.domain.model.ExamAttempt;
import com.exam.domain.repository.Repositories.ExamAttemptRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryExamAttemptRepository implements ExamAttemptRepository {

    private final List<ExamAttempt> attempts = new ArrayList<>();

    @Override
    public void save(ExamAttempt attempt) {
        attempts.add(attempt);
    }

    @Override
    public List<ExamAttempt> findAll() {
        return new ArrayList<>(attempts);
    }
}
