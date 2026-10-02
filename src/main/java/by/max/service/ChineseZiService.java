package by.max.service;

import by.max.dto.ChineseZiDto;
import by.max.exception.ResourceNotFoundException;
import by.max.model.ChineseZi;
import by.max.repository.ChineseZiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChineseZiService{
    private final ChineseZiRepository chineseZiRepository;

    public ChineseZiService(ChineseZiRepository chineseZiRepository) {
        this.chineseZiRepository = chineseZiRepository;
    }

    public List<ChineseZi> getAllWords(){
        return chineseZiRepository.findAllByOrderByIdAsc();
    }

    public ChineseZi findOneZi(long id){
        return chineseZiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Иероглиф с ID " + id + " не найден"));
    }

    public List<ChineseZi> findSomeZiForTest(long firstId, long endId){
        return chineseZiRepository.findByIdBetweenOrderByIdAsc(firstId,endId);
    }
    public void saveZi(ChineseZiDto dto) {
        ChineseZi entity = new ChineseZi();
        entity.setZi(dto.getZi());
        entity.setPinyin(dto.getPinyin());
        entity.setTranslation(dto.getTranslation());
        entity.setDescription(dto.getDescription());
        entity.setExample(dto.getExample());

        chineseZiRepository.save(entity);
    }
}
