package com.proyecto.domain;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by DanielaMartin on 04/10/16.
 */

@Entity
public class Article {
    private int idArticle;
    private String name;
    private String description;
    private int time;
    private String yearMonth;
    private int quantity;
    private int idExchange;

    private LocalDateTime dateCreation;
    private LocalDateTime dateEdit;

    private Set<Category> categories;

    public Article(){

    }

    public Article(int idArticle, String name, String description, int time, String yearMonth, int quantity, int idExchange, LocalDateTime dateEdit, LocalDateTime dateCreation) {
        this.idArticle = idArticle;
        this.name = name;
        this.description = description;
        this.time = time;
        this.yearMonth = yearMonth;
        this.quantity = quantity;
        this.idExchange= idExchange;
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

    public int getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(int idExchange) {
        this.idExchange = idExchange;
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "article_category", joinColumns = @JoinColumn(name = "idArt", referencedColumnName = "idArticle"), inverseJoinColumns = @JoinColumn(name = "idCat", referencedColumnName = "idCategory"))
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
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
                ", idExchange=" + idExchange +
                ", dateCreation=" + dateCreation +
                ", dateEdit=" + dateEdit +
                '}';
    }
}