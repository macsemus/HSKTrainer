package by.max.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_sessions")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer blockNumber;
    private Integer score;
    private Integer totalQuestions;
    private boolean passed;
    private LocalDateTime createdAt;

    public Test() {
    }

    public Test(User user, Integer blockNumber, Integer score, Integer totalQuestions, boolean passed, LocalDateTime createdAt) {
        this.user = user;
        this.blockNumber = blockNumber;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.passed = passed;
        this.createdAt = createdAt;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Integer getBlockNumber() { return blockNumber; }
    public void setBlockNumber(Integer blockNumber) { this.blockNumber = blockNumber; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Integer getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(Integer totalQuestions) { this.totalQuestions = totalQuestions; }

    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}