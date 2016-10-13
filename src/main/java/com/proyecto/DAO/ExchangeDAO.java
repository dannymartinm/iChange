package com.proyecto.DAO;

import com.proyecto.domain.Exchange;
import com.proyecto.domain.User;
import com.proyecto.domain.Article;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Usuario on 08/10/2016.
 */
@Repository
public class ExchangeDAO {

    JdbcOperations jdbcOperations;

    public ExchangeDAO (JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    //Solo he de hacer intercambio...,de lo demás se encargará el controlador
    public  void executeExchange(User u1, Article idA1, User u2, Article idA2, String zone){

        int new_quantity1 = getQuantity(idA1.getIdArticle());
        int new_quantity2 = getQuantity(idA2.getIdArticle());

                jdbcOperations.update("UPDATE article SET quantity = ? WHERE idArticle = ?", new_quantity1 - 1, idA1.getIdArticle());
                jdbcOperations.update("UPDATE article SET quantity = ? WHERE idArticle = ?", new_quantity2 - 1, idA2.getIdArticle());

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        int idEx = jdbcOperations.queryForObject("SELECT idExchange FROM exchange", Integer.class);
        Exchange exchange = new Exchange(idEx, zone, timestamp);
        save(exchange);

/*
        int idEx = jdbcOperations.queryForObject("SELECT idExchange FROM exchange", Integer.class);
        jdbcOperations.update("UPDATE article SET idExchange = ? where idArticle = ?", idEx, idA1.getIdArticle());
        jdbcOperations.update("UPDATE article SET idExchange = ? where idArticle = ?", idEx, idA2.getIdArticle());

        jdbcOperations.update("UPDATE exchange SET isDone = ? where idExchange = ?", 1, idEx);
*/
    }

    private int getQuantity(int idArticle){
        String sql = "SELECT quantity FROM article where idArticle = ?";
        return jdbcOperations.queryForObject(sql, new Object[]{idArticle}, Integer.class);
    }

    public void evaluateExchange(double value, User user, Exchange exchange){

        if(exchange.isDone()) {
            String sql = "UPDATE user SET rate = ? WHERE nickname = ?";
            double val = getRateBD(user.getNickname());
            int total = getTotalExchange(user.getIdUser());
            if(val != -1) {
                jdbcOperations.update(sql, ((val*total)+value)/total+1 , user.getNickname());
            }
            else{
            jdbcOperations.update(sql, value, user.getNickname());}
        }
    }

    private int getTotalExchange(int idUser){
        String sql = "SELECT count(*) FROM user_exchange WHERE idUserEx = ? ";
        return jdbcOperations.queryForObject(sql, new Object[]{idUser}, Integer.class);
    }
    private double getRateBD (String nickname){
        String sql = " SELECT rate FROM user WHERE nickname = ?";
        return jdbcOperations.queryForObject(sql,new Object[]{nickname},Double.class);

    }

    public int save(Exchange exchange) {
        jdbcOperations.update("update article set idExchange = ?", exchange.getIdExchange());
       return jdbcOperations.update("insert into exchange( zoneEx, isDone, dateEx) values( ?, ?, ?)",  exchange.getZoneEx(), 1,exchange.getDateEx());
    }
   /* public int getNewId(){

    }*/
}

