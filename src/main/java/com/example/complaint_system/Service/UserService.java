package com.example.complaint_system.Service;

import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {
    ResponseEntity<ResponseDTO<Map<String, Object>>> registerUser(UserRequestDTO userRequestDTO);

    ResponseEntity<ResponseDTO<Map<String, Object>>> loginUser(UserRequestDTO userRequestDTO);
}
