package by.max.repository;

import by.max.model.User;
import by.max.model.UserMistake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMistakeRepository extends JpaRepository<UserMistake, Long> {
    List<UserMistake> findByUserOrderByErrorCountDesc(User user);
    Optional<UserMistake> findByUserAndZiId(User user, Long ziId);
}
