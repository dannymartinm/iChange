package com.proyecto.DAO;

import com.proyecto.domain.Exchange;
import com.proyecto.domain.User;
import com.proyecto.domain.Article;
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
    public  void executeExchange(User u1, Article idA1,  User u2, Article idA2 ){

        int new_quantity1 = getQuantity(idA1.getIdArticle());
        int new_quantity2 = getQuantity(idA2.getIdArticle());


        String sql = "UPDATE article SET owner = ? WHERE idArticle= ?";

        if (new_quantity1 == 1 && new_quantity2==1){
            jdbcOperations.update(sql, u1.getIdUser(), idA2.getIdArticle());
            jdbcOperations.update(sql, u2.getIdUser(), idA1.getIdArticle());
        }
        else {

            if(new_quantity1 == 1 && new_quantity2>1){
                //crear un nuevo articulo al 1 con quantity 1 y restar al 2


            }
            else if(new_quantity2 == 1 && new_quantity1>1){
                //crear un nuevo articulo al 2 con quantity 1 y restar al 1

            }
            else {
                jdbcOperations.update("UPDATE article SET quantity = ? WHERE idArticle = ?", new_quantity1 - 1, idA1.getIdArticle());
                jdbcOperations.update("UPDATE article SET quantity = ? WHERE idArticle = ?", new_quantity2 - 1, idA2.getIdArticle());

                jdbcOperations.update(sql, u1.getIdUser(), idA2.getIdArticle());
                jdbcOperations.update(sql, u2.getIdUser(), idA1.getIdArticle());

            }
        }



        //restar cantidad, si tiene + de 1
        //debería de poner al exchange que el isDone = true;

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
       return jdbcOperations.update("insert into user values(?, ?, ?, ?)", exchange.getIdExchange(), exchange.getZoneEx(), Timestamp.valueOf(exchange.getDateEx()));

    }
}
