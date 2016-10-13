package com.proyecto.DAO;

import com.proyecto.domain.Exchange;
import com.proyecto.domain.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

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
    public  void executeExchange(User u1, int idA1,  User u2, int idA2 ){

        String sql = "UPDATE article SET owner = ? WHERE idArticle= ?";
        jdbcOperations.update(sql, u1.getIdUser(), idA2);
        jdbcOperations.update(sql, u2.getIdUser(), idA1);
        //restar cantidad, si tiene + de 1
        //debería de poner al exchange que el isDone = true;
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
       return jdbcOperations.update("insert into user values(?, ?, ?, ?)", exchange.getIdExchange(), exchange.getZoneEx(), Timestamp.valueOf(exchange.getDateEx()));

    }
}
