package com.yaganaste.login.security.services.Impl;

import com.yaganaste.login.models.ERole;
import com.yaganaste.login.models.Role;
import com.yaganaste.login.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));


        Role role = new Role();
        role.setId(rs.getInt("role_id"));
        role.setName(ERole.valueOf(rs.getString("name")));
        user.getRoles().add(role);

        return user;
    }
}