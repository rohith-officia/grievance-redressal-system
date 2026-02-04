package com.example.complaint_system.Service.ServiceImp;

import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.ResponseHeadDTO;
import com.example.complaint_system.DTO.UserRequestDTO;
import com.example.complaint_system.Models.UserModel;
import com.example.complaint_system.Repository.UserRepository;
import com.example.complaint_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDTO<Map<String, Object>>> registerUser(UserRequestDTO userRequestDTO) {
        UserModel userModel = new UserModel();
        userModel.setEmail(userRequestDTO.getEmail());
        userModel.setPassword(userRequestDTO.getPassword());
        userModel.setUsername(userRequestDTO.getUsername());
        userModel.setRole(userRequestDTO.getRole());
        userRepository.save(userModel);

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", userModel.getId());
        responseData.put("email", userModel.getEmail());
        responseData.put("username", userModel.getUsername());
        responseData.put("role", userModel.getRole());


        ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO("successfully" , 201 , "User registered successfully");
        ResponseDTO<Map<String, Object>> responseDTO = new ResponseDTO(responseHeadDTO , responseData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
