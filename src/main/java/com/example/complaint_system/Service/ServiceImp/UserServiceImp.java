package com.example.complaint_system.Service.ServiceImp;

import ch.qos.logback.core.joran.action.ResourceAction;
import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.ResponseHeadDTO;
import com.example.complaint_system.DTO.UserRequestDTO;
import com.example.complaint_system.Models.UserModel;
import com.example.complaint_system.Repository.UserRepository;
import com.example.complaint_system.Security.JwtService;
import com.example.complaint_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<ResponseDTO<Map<String, Object>>> registerUser(UserRequestDTO userRequestDTO) {
        UserModel userModel = new UserModel();
        userModel.setEmail(userRequestDTO.getEmail());
        userModel.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
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

    @Autowired
    JwtService  jwtService;

    @Override
    public ResponseEntity<ResponseDTO<Map<String, Object>>> loginUser(UserRequestDTO userRequestDTO) {
        Optional<UserModel> userModel =  userRepository.findByEmail(userRequestDTO.getEmail());

        if(!userModel.isEmpty()){
            UserModel user = userModel.get();
            if(passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())){
                String token = jwtService.generateToken(user.getEmail());
                ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO("successfully" , 200 , "User login successfully");
                ResponseDTO responseDTO = new ResponseDTO (responseHeadDTO , token);
                return new ResponseEntity<>(responseDTO , HttpStatus.OK);
            }
            else{
                ResponseHeadDTO responseHeadDTO =  new ResponseHeadDTO("failure" , 401 , "Invalid username or password");
                ResponseDTO responseDTO = new ResponseDTO(responseHeadDTO , null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
            }
        }
            ResponseHeadDTO responseHeadDTO =   new ResponseHeadDTO("failure" , 401 , "User does not exist");
            ResponseDTO responseDTO = new ResponseDTO(responseHeadDTO , null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
    