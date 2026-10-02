package service;

import by.max.exception.ResourceNotFoundException;
import by.max.model.ChineseZi;
import by.max.repository.ChineseZiRepository;
import by.max.service.ChineseZiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChineseZiServiceTest {

    @Mock
    private ChineseZiRepository chineseZiRepository; // Создаем фейковый репозиторий (мокаем)

    @InjectMocks
    private ChineseZiService chineseZiService;       // Внедряем его внутрь реального сервиса

    @Test
    void findOneZi_ShouldReturnZi_WhenExists() {
        // Arrange (подготовка данных)
        Long ziId = 1L;
        ChineseZi mockZi = new ChineseZi();
        mockZi.setId(ziId);
        mockZi.setZi("你");
        mockZi.setPinyin("nǐ");

        // Говорим Mockito: когда репозиторий попросят найти по ID 1, верни наш mockZi
        when(chineseZiRepository.findById(ziId)).thenReturn(Optional.of(mockZi));

        // Act (вызов метода)
        ChineseZi result = chineseZiService.findOneZi(ziId);

        // Assert (проверка результатов)
        assertNotNull(result);
        assertEquals("你", result.getZi());
        assertEquals("nǐ", result.getPinyin());

        // Проверяем, что метод репозитория реально вызывался 1 раз
        verify(chineseZiRepository, times(1)).findById(ziId);
    }

    @Test
    void findOneZi_ShouldThrowException_WhenNotFound() {
        // Arrange
        Long ziId = 99L;
        // Говорим Mockito: верни пустой Optional, если такого ID нет
        when(chineseZiRepository.findById(ziId)).thenReturn(Optional.empty());

        // Act & Assert (проверяем, что при вызове полетит наше кастомное исключение 404)
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> chineseZiService.findOneZi(ziId)
        );

        assertEquals("Иероглиф с ID 99 не найден", exception.getMessage());
        verify(chineseZiRepository, times(1)).findById(ziId);
    }
}
