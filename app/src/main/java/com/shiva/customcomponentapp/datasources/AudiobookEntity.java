package com.shiva.customcomponentapp.datasources;

/**
 * Created by asus on 14-01-2018.
 */

public class AudiobookEntity extends BaseEntity{
    private String title;
    private String description;

    public AudiobookEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
