
package by.max.service;

import by.max.model.Test;
import by.max.model.User;
import by.max.repository.TestRepository;
import by.max.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public TestService(TestRepository testRepository, UserRepository userRepository) {
        this.testRepository = testRepository;
        this.userRepository = userRepository;
    }

    public Test saveTestResult(String username, Integer blockNumber, Integer score, Integer totalQuestions) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        boolean passed = (double) score / totalQuestions >= 0.8;
        Test testResult = new Test(
                user,
                blockNumber,
                score,
                totalQuestions,
                passed,
                LocalDateTime.now()
        );
        return testRepository.save(testResult);
    }
}