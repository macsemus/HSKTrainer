package by.max.Controller;

import by.max.model.Test;
import by.max.service.TestService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final TestService testService;
    @PostMapping("/api/tests")
    public Test saveTest(@RequestParam int blockNumber, @RequestParam int score, @RequestParam int questionsNumber ){
        return testService.saveTestResult(blockNumber,score,questionsNumber);
    }

    public TestController(TestService testService) {
        this.testService = testService;
    }
}
