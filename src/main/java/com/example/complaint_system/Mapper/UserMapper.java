package com.example.complaint_system.Mapper;

import com.example.complaint_system.Models.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserMapper implements RowMapper<Map<String , Object>> {

    @Override
    public Map<String , Object> mapRow(ResultSet rs , int rowNum) throws SQLException{

        Map<String , Object> map = new HashMap<>();
        map.put("id", rs.getString("id"));
        map.put("username", rs.getString("username"));
        map.put("email", rs.getString("email"));
        map.put("password", rs.getString("password"));
        map.put("role" , rs.getString("role"));
        return map;
    }
}
