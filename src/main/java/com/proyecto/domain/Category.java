package com.proyecto.domain;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;


/**
 * Created by DanielaMartin on 10/10/16.
 */

@Entity
public class Category {

    private int idCategory;
    private String nameCategory;
    private String description;

    private LocalDateTime dateCreation;
    private LocalDateTime dateEdit;

    private Set<Article> articles;

    public Category(){

    }

    public Category(int idCategory, String nameCategory, String description, LocalDateTime dateCreation, LocalDateTime dateEdit) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateEdit = dateEdit;
    }


    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }

    @ManyToMany(mappedBy = "articles")
    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", nameCategory='" + nameCategory + '\'' +
                ", description='" + description + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateEdit=" + dateEdit +
                '}';
    }
}