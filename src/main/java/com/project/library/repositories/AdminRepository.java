package com.project.library.repositories;

import com.project.library.exceptions.NoSuchAdminException;
import com.project.library.model.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminRepository implements Deletable{

    private JdbcTemplate jdbcTemplate;

    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Admin> getAllAdmins() {
        String sql = "SELECT * FROM admin";
        return jdbcTemplate.query(sql, new RowMapper<Admin>() {
            @Override
            public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
                Admin registeredAdmin = new Admin(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3) );
                return registeredAdmin;
            }
        });
    }

    public void insertAdmin(Admin admin) {
        String sql = "INSERT INTO admin VALUES (?,?,?)";
        jdbcTemplate.update(sql,
                admin.getAdmin_id(),
                admin.getAdmin_fname(),
                admin.getAdmin_lname()
                );
    }

    public Admin getAdminById(int adminId) {
        String sql = "SELECT * FROM admin WHERE admin_id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, new Object[]{adminId}, new BeanPropertyRowMapper<>(Admin.class));
        } catch (NoSuchAdminException e) {
            throw new NoSuchAdminException("There is no such Admin");
        }
    }

    @Override
    public void deleteById(int adminId) {
        try {
            String sql = "DELETE FROM admin WHERE admin_id = ?";
            jdbcTemplate.update(sql, adminId);
        } catch (NoSuchAdminException e ){
            throw new NoSuchAdminException("There is no such Admin");
        }

    }

    public void updateAdminData(Admin updatedAdmin) {
        try {
            String sql = "UPDATE admin SET admin_fname = ?, admin_lname = ? WHERE admin_id = "+updatedAdmin.getAdmin_id()+"";
            jdbcTemplate.update(sql,
                    updatedAdmin.getAdmin_fname(),
                    updatedAdmin.getAdmin_lname());
        } catch (NoSuchAdminException e) {
            throw new NoSuchAdminException("There is no such admin");
        }
    }
}
