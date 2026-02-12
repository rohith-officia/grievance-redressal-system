package com.example.complaint_system.Service.ServiceImp;

import com.example.complaint_system.DAO.AdminDAO;
import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.ResponseHeadDTO;
import com.example.complaint_system.Models.UserModel;
import com.example.complaint_system.Repository.UserRepository;
import com.example.complaint_system.Service.AdminService;
import com.example.complaint_system.Utility.CPT_SYTMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public ResponseEntity<ResponseDTO<List<Map<String, Object>>>> getComaplaints() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        if(CPT_SYTMUtil.CHECK_ROLE(user.getRole().toLowerCase())) {
            ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO(CPT_SYTMUtil.SUCCESS ,CPT_SYTMUtil.ACCEPTED_CODE, CPT_SYTMUtil.COMPLAINTS_FETCHED);
            ResponseDTO responseDTO = new ResponseDTO(responseHeadDTO , adminDAO.getAllUser());
            return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
        }
        ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO(CPT_SYTMUtil.FAILURE ,CPT_SYTMUtil.UNAUTHORIZED_CODE, CPT_SYTMUtil.USER_UNAUTHORISED);
        ResponseDTO responseDTO = new ResponseDTO(responseHeadDTO , null);
        return new ResponseEntity<>(responseDTO,HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<ResponseDTO<Map<String, Object>>> updateStatus(Map<String, String> status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if(CPT_SYTMUtil.CHECK_ROLE(user.getRole().toLowerCase())) {
            ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO(CPT_SYTMUtil.SUCCESS , CPT_SYTMUtil.SUCCESS_CODE ,CPT_SYTMUtil.STATUS_UPDATED_SUCCESS);
            ResponseDTO responseDTO = new ResponseDTO(responseHeadDTO , adminDAO.updateComplaintStatus(Long.parseLong(status.get("id")) , status.get("status")));
            return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
        }
        ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO(CPT_SYTMUtil.FAILURE ,CPT_SYTMUtil.UNAUTHORIZED_CODE, CPT_SYTMUtil.USER_UNAUTHORISED);
        ResponseDTO responseDTO = new ResponseDTO(responseHeadDTO , null);
        return new ResponseEntity<>(responseDTO,HttpStatus.UNAUTHORIZED);
    }
}
