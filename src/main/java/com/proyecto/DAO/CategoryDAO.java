package com.proyecto.DAO;


import com.proyecto.domain.Category;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by DanielaMartin on 10/10/16.
 */
@Repository
public class CategoryDAO {

    private JdbcOperations jdbcOperations;

    public CategoryDAO(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public Iterable<Category> findAll() {
        return jdbcOperations.query("Select * from category", new CategoryDAO.categoryMapper());
    }

    public int save(Category category) {
        return jdbcOperations.update("insert into category(idCategory, nameCategory, date_creation, date_edit) values(?, ?, ?, ?)",
                category.getIdCategory(), category.getNameCategory(), category.getDescription(), Timestamp.valueOf(category.getDateCreation()), Timestamp.valueOf(category.getDateEdit()));
    }

    private final class categoryMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int row) throws SQLException {
            Category category = new Category();
            category.setIdCategory(rs.getInt("idCategory"));
            category.setNameCategory(rs.getString("nameCategory"));
            category.setDescription(rs.getString("description"));
            category.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());
            category.setDateEdit(rs.getTimestamp("date_edit").toLocalDateTime());

            return category;
        }
    }
}
