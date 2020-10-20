package com.project.library.repositories;

import com.project.library.model.Cell;
import com.project.library.model.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CellRepository implements Deletable{

    private JdbcTemplate jdbcTemplate;

    public CellRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cell> showAvailableCellsInRepo() {
        String sql = "SELECT * FROM cells WHERE iscellavailable = true";
        return jdbcTemplate.query(sql, new RowMapper<Cell>() {
            @Override
            public Cell mapRow(ResultSet resultSet, int i) throws SQLException {
                Cell availableCell = new Cell(resultSet.getInt(1));
                return availableCell;
            }
        });
    }

    public void insertCells(Cell cell) {
        String sql = "INSERT INTO cells VALUES (?, ?)";
        jdbcTemplate.update(sql,
                cell.getId(),
                cell.isCellAvailable()
                );
    }

    public Cell getCellByID(int cellID) {
        String sql = "SELECT * FROM cells WHERE cell_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cellID}, new BeanPropertyRowMapper<>(Cell.class));
    }

    @Override
    public void deleteById(int cellID) {
        String sql = "DELETE FROM cells WHERE cell_id = ?";
        jdbcTemplate.update(sql, cellID);
    }

    public void updateCellData(Cell updatedCell) {
        String sql = "UPDATE cells SET iscellavailable = ? WHERE cell_id = "+updatedCell.getId()+"";
        jdbcTemplate.update(sql,
                updatedCell.isCellAvailable());
    }

    public boolean selectAvailableCell() {
        String sql = "SELECT count(*) FROM cells WHERE iscellavailable = true LIMIT 1";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class);
        if (count != null && count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
