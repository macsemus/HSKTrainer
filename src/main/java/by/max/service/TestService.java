package by.max.service;

import by.max.model.Test;
import by.max.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test saveTestResult(Integer blockNumber, Integer score, Integer totalQuestions) {
        boolean passed = (double) score / totalQuestions >= 0.8;
        Test testResult = new Test(
                blockNumber,
                score,
                totalQuestions,
                passed,
                LocalDateTime.now()
        );
        return testRepository.save(testResult);
    }
}