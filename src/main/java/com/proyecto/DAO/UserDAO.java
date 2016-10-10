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

    public User findOne(String nickname) {
        return jdbcOperations.queryForObject("Select * from user where nickname = ?", new Object[]{nickname}, new userMapper());
    }


    public int save(User user) {
        int userUpdate = jdbcOperations.update("insert into user values(?, ?, ?, ?, ?, ?, ?)", user.getIdUser(), user.getUserName(), user.getZone(), user.getPassword(), user.getMail(), user.getNickname(), user.getRate());
        articleDAO.saveUserArticles(user);

        return userUpdate;
    }
    public int deleteUser(User user){
        int userDeleted = jdbcOperations.update("Delete from user where nickname = ?", new Object[]{user.getNickname()});
        articleDAO.deleteArticles(user.getIdUser());
        return userDeleted;
    }

    public int updateUser(User user) {
        int userUpdate = jdbcOperations.update("update user set username=?, password=?, zone=?, mail=? WHERE idUser=?", user.getUserName(), user.getPassword(), user.getZone(), user.getMail(), user.getIdUser());
        return userUpdate;
    }

    private final class userMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet rs, int row) throws SQLException{
            User user = new User();
            user.setIdUser(rs.getInt("idUser"));
            user.setUserName(rs.getString("username"));
            user.setZone(rs.getString("zone"));
            user.setPassword(rs.getString("password"));
            user.setMail(rs.getString("mail"));
            user.setNickname(rs.getString("nickname"));
            user.setRate(rs.getInt("rate"));
            user.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());
            user.setDateEdit(rs.getTimestamp("date_edit").toLocalDateTime());

           List<Article> articles = articleDAO.findAllFromUser(user.getIdUser());
            user.addArticles(articles);

            return user;
        }
    }


}
