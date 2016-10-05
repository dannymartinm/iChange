package com.proyecto.DAO;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.proyecto.domain.User;

/**
 * Created by DanielaMartin on 04/10/16.
 */

@Repository
public class UserDAO{

    private JdbcOperations jdbcOperations;

    public UserDAO(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    public int saveUser(User user) {
        int userUpdate = jdbcOperations.update("insert into user_lab values(?, ?, ?, ?, ?, ?, ?)", user.getId(), user.getUserName(), user.getZone(), user.getPassword(), user.getMail(), user.getNickname(), user.getRate());
       // ArticleDAO.saveUserArticles(user);

        return userUpdate;
    }


}
