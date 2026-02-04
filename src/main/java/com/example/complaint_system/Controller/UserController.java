package com.example.complaint_system.Controller;

import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.ResponseHeadDTO;
import com.example.complaint_system.DTO.UserRequestDTO;
import com.example.complaint_system.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("register/")
    public ResponseEntity<ResponseDTO<Map<String , Object>>> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        if(userRequestDTO.getEmail() == null || userRequestDTO.getUsername() == null || userRequestDTO.getPassword() == null || userRequestDTO.getRole() == null) {
            ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO("Unsuccessfull" , 400 , "Invalid Input");
            ResponseDTO<Map<String, Object>> responseDTO = new ResponseDTO(responseHeadDTO , null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
        return userService.registerUser(userRequestDTO);
    }
}
