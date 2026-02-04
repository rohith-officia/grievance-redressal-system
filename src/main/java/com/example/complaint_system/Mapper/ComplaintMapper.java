package com.example.complaint_system.Mapper;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ComplaintMapper implements RowMapper<Map<String , Object>> {

    @Override
    public Map<String , Object> mapRow(ResultSet rs ,int rowNum) throws SQLException {

        Map<String , Object> map = new HashMap<>();
        map.put("id", rs.getString("id"));
        map.put("title", rs.getString("title"));
        map.put("description", rs.getString("description"));
        map.put("date", rs.getString("date"));
        map.put("status", rs.getString("status"));
        map.put("department", rs.getString("department"));
        map.put("created_at", rs.getString("created_at"));
        return map;
    }
}
