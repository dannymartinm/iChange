package com.proyecto.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DanielaMartin on 04/10/16.
 */
public class User {
    private int id;
    private String username;
    private String zone;
    private String password;
    private String mail;
    private String nickname;
    private double rate;
    private List<Article> articleList;

    private LocalDateTime dateEdit;
    private LocalDateTime dateCreation;

    public User(){

    }


    public User(int id, String username, String zone, String password, String mail, String nickname, double rate, LocalDateTime dateEdit, LocalDateTime dateCreation) {
        this.id= id;
        this.username = username;
        this.zone = zone;
        this.password = password;
        this.mail = mail;
        this.nickname = nickname;
        this.rate = rate;
        this.dateCreation = dateCreation;
        this.setDateEdit(dateEdit);
        this.articleList = new ArrayList<Article>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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

    public List<Article> getArticleList() {
        return this.articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }


    public Article addArticle(Article article) {
        getArticleList().add(article);
        return article;
    }

    public Article removeArticle(Article article) {
        getArticleList().remove(article);
        return article;
    }

    public void addArticles(List<Article> articles) {
        this.articleList.addAll(articles);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "id='" + id + '\'' +
                ", zone='" + zone + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", nickname='" + nickname + '\'' +
                ", rate=" + rate +
                ", dateEdit=" + dateEdit +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
