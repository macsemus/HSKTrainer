package by.max.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_mistakes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "zi_id"})
})
public class UserMistake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zi_id", nullable = false)
    private ChineseZi zi;

    @Column(nullable = false)
    private Integer errorCount = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ChineseZi getZi() {
        return zi;
    }

    public void setZi(ChineseZi zi) {
        this.zi = zi;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public UserMistake( User user, ChineseZi zi, Integer errorCount) {
        this.user = user;
        this.zi = zi;
        this.errorCount = errorCount;
    }

    public UserMistake() {
    }
}
