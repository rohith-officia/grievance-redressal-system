package com.example.complaint_system.Service.ServiceImp;

import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.ResponseHeadDTO;
import com.example.complaint_system.DTO.UserRequestDTO;
import com.example.complaint_system.Models.ComplaintsModel;
import com.example.complaint_system.Models.UserModel;
import com.example.complaint_system.Repository.ComplaintRespository;
import com.example.complaint_system.Repository.UserRepository;
import com.example.complaint_system.Security.JwtService;
import com.example.complaint_system.Service.UserService;
import com.example.complaint_system.Utility.CPT_SYTMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO(CPT_SYTMUtil.SUCCESS, CPT_SYTMUtil.CREATED_CODE , CPT_SYTMUtil.REGISTRATION_SUCCESS);
        ResponseDTO<Map<String, Object>> responseDTO = new ResponseDTO(responseHeadDTO , responseData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Autowired
    JwtService  jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<ResponseDTO<Map<String, Object>>> loginUser(
            UserRequestDTO userRequestDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDTO.getEmail(),
                            userRequestDTO.getPassword()
                    )
            );

            // Store authenticated user in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateToken(authentication.getName());

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("Username", authentication.getName());
            responseData.put("roles", authentication.getAuthorities());

            ResponseHeadDTO responseHeadDTO =
                    new ResponseHeadDTO("success", 200, "User login successfully");

            ResponseDTO<Map<String, Object>> responseDTO =
                    new ResponseDTO<>(responseHeadDTO, responseData);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (BadCredentialsException ex) {

            ResponseHeadDTO responseHeadDTO =
                    new ResponseHeadDTO("failure", 401, "Invalid email or password");

            ResponseDTO<Map<String, Object>> responseDTO =
                    new ResponseDTO<>(responseHeadDTO, null);

            return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);

        } catch (UsernameNotFoundException ex) {

            ResponseHeadDTO responseHeadDTO =
                    new ResponseHeadDTO("failure", 404, "User does not exist");

            ResponseDTO<Map<String, Object>> responseDTO =
                    new ResponseDTO<>(responseHeadDTO, null);

            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    ComplaintRespository complaintRespository;

    @Override
    public ResponseEntity<ResponseDTO<List<Map<String, Object>>>> my_complaints() {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Authenticated user not found")
                );

        List<ComplaintsModel> complaints = complaintRespository.findByUser(user);
        List<Map<String, Object>> complaintList = new ArrayList<>();

        for (ComplaintsModel c : complaints) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", c.getId());
            map.put("title", c.getTitle());
            map.put("department", c.getDepartment());
            map.put("status", c.getStatus());
            map.put("createdAt", c.getCreatedAt());
            complaintList.add(map);
        }

        ResponseHeadDTO head =
                new ResponseHeadDTO("success", 200, "My complaints fetched");

        return new ResponseEntity <>(
                new ResponseDTO<>(head, complaintList) ,
                HttpStatus.OK
        );

    }
}
    