package repository;

import by.max.App; // Указываем ваш реальный главный класс
import by.max.model.ChineseZi;
import by.max.repository.ChineseZiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = App.class)
@Testcontainers
class ChineseZiRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("test_db")
            .withUsername("postgres")
            .withPassword("admin");

    // Динамически передаем параметры контейнера в настройки Spring Boot
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private ChineseZiRepository chineseZiRepository;

    @Test
    void shouldSaveAndFindChineseZi() {
        ChineseZi zi = new ChineseZi();
        zi.setZi("学");
        zi.setPinyin("xué");
        zi.setTranslation("учиться");

        ChineseZi savedZi = chineseZiRepository.save(zi);
        Optional<ChineseZi> foundZi = chineseZiRepository.findById(savedZi.getId());

        assertThat(foundZi).isPresent();
        assertThat(foundZi.get().getZi()).isEqualTo("学");
        assertThat(foundZi.get().getPinyin()).isEqualTo("xué");
    }
}