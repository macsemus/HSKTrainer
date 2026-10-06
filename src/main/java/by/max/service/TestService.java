package by.max.service;

import by.max.model.*;
import by.max.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final UserMistakeRepository userMistakeRepository;
    private final ChineseZiRepository ziRepository;

    public TestService(TestRepository testRepository,
                       UserRepository userRepository,
                       UserMistakeRepository userMistakeRepository,
                       ChineseZiRepository ziRepository) {
        this.testRepository = testRepository;
        this.userRepository = userRepository;
        this.userMistakeRepository = userMistakeRepository;
        this.ziRepository = ziRepository;
    }

    @Transactional
    public Test saveTestResult(String username, Integer blockNumber, Integer score, Integer totalQuestions, List<Long> mistakeZiIds) {
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
        Test savedTest = testRepository.save(testResult);

        // Обрабатываем ошибки, если они переданы
        if (mistakeZiIds != null && !mistakeZiIds.isEmpty()) {
            for (Long ziId : mistakeZiIds) {
                ChineseZi zi = ziRepository.findById(ziId).orElse(null);
                if (zi != null) {
                    UserMistake mistake = userMistakeRepository.findByUserAndZiId(user, ziId)
                            .orElse(new UserMistake(user, zi, 0));

                    mistake.setErrorCount(mistake.getErrorCount() + 1);
                    userMistakeRepository.save(mistake);
                }
            }
        }

        return savedTest;
    }

    // Методы для личного кабинета
    public List<Test> getUserTests(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return testRepository.findByUser(user);
    }

    public List<UserMistake> getUserMistakes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return userMistakeRepository.findByUserOrderByErrorCountDesc(user);
    }
}