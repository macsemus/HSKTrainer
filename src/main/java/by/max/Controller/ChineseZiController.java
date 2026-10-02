package by.max.Controller;

import by.max.dto.ChineseZiDto;
import by.max.model.ChineseZi;
import by.max.service.ChineseZiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zi")
@Tag(name = "Chinese Zi Controller", description = "Управление китайскими иероглифами (добавление, получение)")
public class ChineseZiController {
    private final ChineseZiService chineseZiService;

    public ChineseZiController(ChineseZiService chineseZiService) {
        this.chineseZiService = chineseZiService;
    }

    @GetMapping
    @Operation(summary = "Получить все иероглифы", description = "Возвращает полный список всех сохраненных в базе иероглифов")
    public List<ChineseZi> getAllWords() {
        return chineseZiService.getAllWords();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить иероглиф по ID", description = "Ищет и возвращает конкретный иероглиф по его уникальному идентификатору")
    public ChineseZi getWordById(@PathVariable long id) {
        return chineseZiService.findOneZi(id);
    }

    @GetMapping("/test")
    @Operation(summary = "Получить диапазон иероглифов для теста", description = "Возвращает список иероглифов в заданном диапазоне ID от firstId до endId")
    public List<ChineseZi> getSomeWords(@RequestParam long firstId, @RequestParam long endId) {
        return chineseZiService.findSomeZiForTest(firstId, endId);
    }

    @PostMapping
    @Operation(summary = "Создать новый иероглиф", description = "Валидирует и сохраняет новый иероглиф в базу данных")
    public ResponseEntity<String> createZi(@Valid @RequestBody ChineseZiDto dto) {
        chineseZiService.saveZi(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Иероглиф успешно добавлен!");
    }
}
