package com.example.complaint_system.DAO;

import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.Mapper.UserMapper;
import com.example.complaint_system.Utility.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDAO {

    @Autowired
    JdbcTemplate userjdbc;

    public Map<String, Object> complaints(Long id) {
        return userjdbc.queryForMap(DBUtil.FIND_COMPLAINT_BY_ID , id);
    }
}
