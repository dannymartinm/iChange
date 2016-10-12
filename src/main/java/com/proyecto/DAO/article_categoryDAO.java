package com.proyecto.DAO;

import com.proyecto.domain.Article;
import com.proyecto.domain.Category;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by Usuario on 12/10/2016.
 */
@Repository
public class article_categoryDAO {

    private JdbcOperations jdbcOperations;

    public article_categoryDAO(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    public int addArticleCategory(Article article, Category category){
        String sql = "INSERT INTO article_category VALUES(?,?)";
        return jdbcOperations.update(sql,article.getIdArticle(), category.getIdCategory());
    }
}
