package by.max.repository;

import by.max.model.ChineseZi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChineseZiRepository extends JpaRepository<ChineseZi,Long> {
    List<ChineseZi> findAllByOrderByIdAsc();
    List<ChineseZi> findByIdBetweenOrderByIdAsc(long startId, long endId);
}
