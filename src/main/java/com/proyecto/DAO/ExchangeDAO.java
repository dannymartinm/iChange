package com.proyecto.DAO;

import com.proyecto.domain.Exchange;
import com.proyecto.domain.User;
import com.proyecto.domain.Article;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Usuario on 08/10/2016.
 */
@Repository
public class ExchangeDAO {

    private JdbcOperations jdbcOperations;
    private user_exchangeDAO user_exchangeDAO;
    private UserDAO userDAO;

    public ExchangeDAO (JdbcOperations jdbcOperations, user_exchangeDAO user_exchangeDAO, UserDAO userDAO) {
        this.jdbcOperations = jdbcOperations;
        this.user_exchangeDAO = user_exchangeDAO;
        this.userDAO = userDAO;
    }


    //Solo he de hacer intercambio...,de lo demás se encargará el controlador
    public  void executeExchange(User u1, Article idA1, User u2, Article idA2, String zone){

        int new_quantity1 = getQuantity(idA1.getIdArticle());
        int new_quantity2 = getQuantity(idA2.getIdArticle());

                jdbcOperations.update("UPDATE article SET quantity = ? WHERE idArticle = ?", new_quantity1 - 1, idA1.getIdArticle());
                jdbcOperations.update("UPDATE article SET quantity = ? WHERE idArticle = ?", new_quantity2 - 1, idA2.getIdArticle());

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());


        Exchange exchange = new Exchange(getNewID(), zone, timestamp);
        save(exchange);

        user_exchangeDAO.addUserExchange(u1, u2,exchange);//añadir otro usuario más
    }

    private int getQuantity(int idArticle){
        String sql = "SELECT quantity FROM article where idArticle = ?";
        return jdbcOperations.queryForObject(sql, new Object[]{idArticle}, Integer.class);
    }
/*
    public void evaluateExchange(double value, User user){
        int idExch = getExchange(user.getIdUser());
        boolean isDone = jdbcOperations.queryForObject("SELECT isDone FROM exchange WHERE idExchange = ?", new Object[]{idExch}, Boolean.class);
        if(isDone) {
            String sql = "UPDATE user SET rate = ? WHERE nickname = ?";
            double val = getRateBD(user.getNickname());
            //Le he de enviar el idExchange
            int total = userDAO.getContR(user);
            if(val != -1 && total!= 0) {
                jdbcOperations.update(sql, ((val*total)+value)/total+1 , user.getNickname());
            }
            else{
            jdbcOperations.update(sql, value, user.getNickname());}
        }
    }*/

    private double getRateBD (String nickname){
        String sql = " SELECT rate FROM user WHERE nickname = ?";
        return jdbcOperations.queryForObject(sql,new Object[]{nickname},Double.class);
    }
    private int getExchange(int idUser){
        String sql = "SELECT idExchange FROM user_exchange WHERE idUserEx = ? ";
        return jdbcOperations.queryForObject(sql,new Object[]{idUser}, Integer.class );
    }

    public Exchange findOne(int id) {
        return jdbcOperations.queryForObject("Select * from exchange where idExchange = ?", new Object[]{id}, new exchangeMapper());
    }

    public int save(Exchange exchange) {
        jdbcOperations.update("update article set idExchange = ?", exchange.getIdExchange());
       return jdbcOperations.update("insert into exchange( zoneEx, isDone, dateEx) values( ?, ?, ?)",  exchange.getZoneEx(), 1,exchange.getDateEx());
    }
    private int getNewID(){
        return jdbcOperations.queryForObject("SELECT idEx_SEQ.NEXTVAL FROM DUAL",Integer.class);
    }

    private final class exchangeMapper implements RowMapper<Exchange> {
        @Override
        public Exchange mapRow(ResultSet rs, int row) throws SQLException {
            Exchange exchange = new Exchange();
            exchange.setIdExchange(rs.getInt("idExchange"));
            exchange.setZoneEx(rs.getString("zoneEx"));
            exchange.setDone(rs.getBoolean("isDone"));
            exchange.setDateEx(rs.getTimestamp("dateEx"));

            return exchange;
        }
    }

}

