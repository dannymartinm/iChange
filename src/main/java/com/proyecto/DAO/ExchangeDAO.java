package com.proyecto.DAO;

import com.proyecto.domain.Exchange;
import com.proyecto.domain.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by Usuario on 08/10/2016.
 */
@Repository
public class ExchangeDAO {

    JdbcOperations jdbcOperations;

    public ExchangeDAO (JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
/* --> Mirar bien como hacerlo
    public boolean executeExchange(){
        //Para poder producirse el intercambio los dos usuarios tienen que ponerse de acuerdo
    }
*/
    public void evaluateExchange(double value, User user, Exchange exchange){

        if(exchange.isDone()) {
            String sql = "UPDATE user SET rate = ? WHERE nickname = ?";
            jdbcOperations.update(sql, value, user.getNickname());

        }

    }
}
