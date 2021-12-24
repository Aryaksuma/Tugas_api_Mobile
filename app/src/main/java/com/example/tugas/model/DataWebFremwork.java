package com.example.tugas.model;

import java.io.Serializable;

public class DataWebFremwork implements Serializable {
    String nameFramework, original_author, official_web,  description, image;

    public DataWebFremwork(String nameFramework, String original_author, String official_web, String description, String image) {
        this.nameFramework = nameFramework;
        this.original_author = original_author;
        this.official_web = official_web;
        this.description = description;
        this.image = image;
    }

    public String getNameFramework() {
        return nameFramework;
    }

    public String getOriginal_author() {
        return original_author;
    }

    public String getOfficial_web() {
        return official_web;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}