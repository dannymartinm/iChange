package com.proyecto.DAO;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.proyecto.domain.Article;
import com.proyecto.domain.User;
import org.springframework.jdbc.core.RowMapper;


/**
 * Created by DanielaMartin on 04/10/16.
 */
public class ArticleDAO {
    private JdbcOperations jdbcOperations;

    public ArticleDAO(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    public Iterable<Article> findAll() {
        return jdbcOperations.query("Select * from article", new ArticleDAO.articleMapper());
    }

    public Article findOne(int id) {
        return jdbcOperations.queryForObject("Select * from article where id = ?", new Object[]{id}, new ArticleDAO.articleMapper());
    }

    public List<Article> findAllFromUser(String name) {
        return jdbcOperations.query("select * from article where owner = ?", new Object[]{name}, new articleMapper());
    }

    public int save(Article article, User owner) {
        return jdbcOperations.update("insert into article(id, name, description, time, yearMonth, quantity, date_creation, date_edit, owner) values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                article.getId(), article.getName(), article.getDescription(), article.getTime(), article.getYearMonth(), article.getQuantity(), Timestamp.valueOf(article.getDateCreation()), Timestamp.valueOf(article.getDateEdit()), owner.getUserName());
    }

    public int updateArticle(Article article) {
        return jdbcOperations.update("update article set id = ?, name = ?, description = ?, time = ?, yearMonth = ?, quantity = ?, date_edit = ? where date_ creation = ?",
                article. getId(), article.getName(), article.getDescription(),article.getTime(), article.getYearMonth(), article.getQuantity(), LocalDateTime.now(), article.getDateCreation());
    }


    public int[] saveUserArticles(User owner) {
        return jdbcOperations.batchUpdate("INSERT INTO article (id, name, description, time, yearMonth, quantity, date_creation, date_edit, owner) values(?, ?, ?, ?, ?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Article article = owner.getArticleList().get(i);
                ps.setInt(1, article.getId());
                ps.setString(2, article.getName());
                ps.setString(3, article.getDescription());
                ps.setInt(4, article.getTime());
                ps.setString(5, article.getYearMonth());
                ps.setInt(6, article.getQuantity());
                ps.setTimestamp(7, Timestamp.valueOf(article.getDateCreation()));
                ps.setTimestamp(8, Timestamp.valueOf(article.getDateEdit()));
                ps.setString(9, owner.getUserName());
            }

            @Override
            public int getBatchSize() {
                return owner.getArticleList().size();
            }
        });
    }


    private static final class articleMapper implements RowMapper<Article>{

        @Override
        public Article mapRow(ResultSet rs, int row) throws SQLException{
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setName(rs.getString("name"));
            article.setDescription(rs.getString("description"));
            article.setQuantity(rs.getInt("quantity"));
            article.setTime(rs.getInt("time"));
            article.setYearMonth(rs.getString("yearMonth"));
            article.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());
            article.setDateEdit(rs.getTimestamp("date_edit").toLocalDateTime());

            return article;
        }
    }

}
