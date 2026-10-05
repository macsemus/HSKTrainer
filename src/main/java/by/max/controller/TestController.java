package by.max.controller;

import by.max.model.Test;
import by.max.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public ResponseEntity<Test> saveTest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Integer blockNumber,
            @RequestParam Integer score,
            @RequestParam Integer questionsNumber
    ) {
        // Берем username из текущего JWT токена
        String username = userDetails.getUsername();

        Test savedTest = testService.saveTestResult(username, blockNumber, score, questionsNumber);
        return ResponseEntity.ok(savedTest);
    }
}
