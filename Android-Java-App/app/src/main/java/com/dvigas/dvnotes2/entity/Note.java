package com.dvigas.dvnotes2.entity;

import java.io.Serializable;

public class Note implements Serializable {
    private Integer idnote;
    private String title;
    private String content;

    public Note(Integer idnote, String title, String content) {
        idnote = idnote;
        title = title;
        content = content;
    }

    public Note() {
    }

    public Integer getIdnote() {
        return idnote;
    }

    public void setIdnote(Integer idnote) {
        this.idnote = idnote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return title;
    }
}
