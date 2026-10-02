package by.max.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChineseZiDto {

    @NotBlank(message = "Иероглиф не может быть пустым")
    @Size(max = 10, message = "Иероглиф слишком длинный")
    private String zi;

    @NotBlank(message = "Пиньинь обязателен")
    private String pinyin;

    @NotBlank(message = "Перевод обязателен")
    private String translation;

    private String description;
    private String example;

    public ChineseZiDto() {}

    public ChineseZiDto(String zi, String pinyin, String translation, String description, String example) {
        this.zi = zi;
        this.pinyin = pinyin;
        this.translation = translation;
        this.description = description;
        this.example = example;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
