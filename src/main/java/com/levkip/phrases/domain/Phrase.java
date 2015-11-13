package com.levkip.phrases.domain;

/**
 * Created by levy on 11.10.15.
 */
public class Phrase {
    Long id;
    Long date;
    String phrase;
    String transcription;
    String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date == null ? 0L : date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getPhrase() {
        return phrase == null ? "" : phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTranscription() {
        return transcription == null ? "" : transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "id=" + id +
                ", date=" + date +
                ", phrase='" + phrase + '\'' +
                ", transcription='" + transcription + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
