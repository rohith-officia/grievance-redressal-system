package com.example.complaint_system.Service;

import com.example.complaint_system.DTO.ComplaintRequestDTO;
import com.example.complaint_system.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ComplaintService {
    ResponseEntity<ResponseDTO<Map<String, Object>>> registerComplaint(ComplaintRequestDTO complaintRequestDTO);
}
