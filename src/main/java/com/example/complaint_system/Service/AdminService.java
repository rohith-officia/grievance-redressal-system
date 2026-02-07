package com.example.complaint_system.Service;

import com.example.complaint_system.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AdminService {
    ResponseEntity<ResponseDTO<List<Map<String, Object>>>> getComaplaints();
}
