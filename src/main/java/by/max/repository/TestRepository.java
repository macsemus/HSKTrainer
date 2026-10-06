package by.max.repository;

import by.max.model.Test;
import by.max.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByUser(User user);
}