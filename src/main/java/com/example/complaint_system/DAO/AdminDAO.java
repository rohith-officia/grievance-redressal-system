package com.example.complaint_system.DAO;

import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.Utility.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AdminDAO {

    @Autowired
    JdbcTemplate adminjdbc;

    public List<Map<String , Object>> getAllUser() {
        return adminjdbc.queryForList(DBUtil.FIND_ALL_COMPLAINTS);
    }

    public int updateComplaintStatus(Long complaintId, String status) {
        return adminjdbc.update(
                DBUtil.UPDATE_COMPLAINT_STATUS,
                status,
                complaintId
        );
    }
}
