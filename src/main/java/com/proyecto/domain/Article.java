package com.proyecto.domain;

import java.time.LocalDateTime;

/**
 * Created by DanielaMartin on 04/10/16.
 */
public class Article {
    private int idArticle;
    private String name;
    private String description;
    private int time;
    private String yearMonth;
    private int quantity;

    private LocalDateTime dateCreation;
    private LocalDateTime dateEdit;

    public Article(){

    }

    public Article(int idArticle, String name, String description, int time, String yearMonth, int quantity, LocalDateTime dateEdit, LocalDateTime dateCreation) {
        this.idArticle = idArticle;
        this.name = name;
        this.description = description;
        this.time = time;
        this.yearMonth = yearMonth;
        this.quantity = quantity;
        this.dateCreation = dateCreation;
        this.setDateEdit(dateEdit);
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", yearMonth='" + yearMonth + '\'' +
                ", quantity=" + quantity +
                ", dateEdit=" + dateEdit +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
