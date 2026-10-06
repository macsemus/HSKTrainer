package by.max.controller;

import by.max.model.Test;
import by.max.model.UserMistake;
import by.max.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    public static class TestResultRequest {
        private Integer blockNumber;
        private Integer score;
        private Integer totalQuestions;
        private List<Long> mistakeZiIds;

        // Геттеры и сеттеры
        public Integer getBlockNumber() { return blockNumber; }
        public void setBlockNumber(Integer blockNumber) { this.blockNumber = blockNumber; }
        public Integer getScore() { return score; }
        public void setScore(Integer score) { this.score = score; }
        public Integer getTotalQuestions() { return totalQuestions; }
        public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }
        public List<Long> getMistakeZiIds() { return mistakeZiIds; }
        public void setMistakeZiIds(List<Long> mistakeZiIds) { this.mistakeZiIds = mistakeZiIds; }
    }

    @PostMapping("/save")
    public ResponseEntity<Test> saveTest(@RequestBody TestResultRequest request, Principal principal) {
        // principal.getName() достает username текущего залогиненного пользователя (если используется Spring Security + JWT)
        // Если у тебя передается username иначе, поменяй principal.getName() на свой источник
        String username = principal.getName();

        Test savedTest = testService.saveTestResult(
                username,
                request.getBlockNumber(),
                request.getScore(),
                request.getTotalQuestions(),
                request.getMistakeZiIds()
        );

        return ResponseEntity.ok(savedTest);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Test>> getUserTests(Principal principal){
        List<Test> tests = testService.getUserTests(principal.getName());
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/mistakes")
    public ResponseEntity<List<UserMistake>> getUserMistakes(Principal principal) {
        List<UserMistake> mistakes = testService.getUserMistakes(principal.getName());
        return ResponseEntity.ok(mistakes);
    }
}
