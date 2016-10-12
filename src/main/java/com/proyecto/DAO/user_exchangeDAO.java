package com.proyecto.DAO;

import com.proyecto.domain.Exchange;
import com.proyecto.domain.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by Usuario on 12/10/2016.
 */
@Repository
public class user_exchangeDAO {

    private JdbcOperations jdbcOperations;

    public user_exchangeDAO(JdbcOperations jdbcOperations) {this.jdbcOperations = jdbcOperations;}

    public int addUserExchange(User user, Exchange exchange){

        String sql = "INSERT INTO user_exchange VALUES(?,?)";
        return jdbcOperations.update(sql,user.getIdUser(), exchange.getIdExchange());

    }


}
