package by.max.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
@Table(name = "ch_zi1", schema = "public")
public class ChineseZi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zi;
    private String pinyin;
    private String translation;
    private String description;
    private String example;

    public ChineseZi(String zi) {
        this.zi = zi;
    }

    public ChineseZi(int id, String zi, String pinyin, String translation, String description, String example) {
        this.id = (long) id;
        this.zi = zi;
        this.pinyin = pinyin;
        this.translation = translation;
        this.description = description;
        this.example = example;
    }

    public ChineseZi() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }
}

