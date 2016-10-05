package com.proyecto.DAO;

import com.proyecto.domain.Article;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.proyecto.domain.User;

/**
 * Created by DanielaMartin on 04/10/16.
 */

@Repository
public class UserDAO{

    private JdbcOperations jdbcOperations;
    private  ArticleDAO articleDAO;

    public UserDAO(JdbcOperations jdbcOperations, ArticleDAO articleDAO) {
        this.jdbcOperations = jdbcOperations;
        this.articleDAO = articleDAO;
    }

    public Iterable<User> findAll() {
        return jdbcOperations.query("Select * from user", new userMapper());
    }


    private final class userMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int row) throws SQLException{
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setZone(rs.getString("zone"));
            user.setPassword(rs.getString("password"));
            user.setMail(rs.getString("mail"));
            user.setNickname(rs.getString("nickname"));
            user.setRate(rs.getInt("rate"));
            user.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());
            user.setDateEdit(rs.getTimestamp("date_edit").toLocalDateTime());

            List<Article> articles = articleDAO.findAllFromUser(user.getUserName());
            user.addArticles(articles);

            return user;
        }
    }


}
