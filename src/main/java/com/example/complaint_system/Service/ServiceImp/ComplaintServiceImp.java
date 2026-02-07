package com.example.complaint_system.Service.ServiceImp;

import com.example.complaint_system.DTO.ComplaintRequestDTO;
import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.ResponseHeadDTO;
import com.example.complaint_system.Models.ComplaintsModel;
import com.example.complaint_system.Models.UserModel;
import com.example.complaint_system.Repository.ComplaintRespository;
import com.example.complaint_system.Repository.UserRepository;
import com.example.complaint_system.Service.ComplaintService;
import com.example.complaint_system.Utility.CPT_SYTMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ComplaintServiceImp implements ComplaintService {

    @Autowired
    private ComplaintRespository complaintRespository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDTO<Map<String, Object>>> registerComplaint(
            ComplaintRequestDTO complaintRequestDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        String email = auth.getName();

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Authenticated user not found")
                );

        ComplaintsModel complaint = new ComplaintsModel();
        complaint.setTitle(complaintRequestDTO.getTitle());
        complaint.setDescription(complaintRequestDTO.getDescription());
        complaint.setDepartment(complaintRequestDTO.getDepartment());
        complaint.setStatus(CPT_SYTMUtil.ComplaintStatus.OPEN);
        complaint.setUser(user);
        complaint.setCreatedAt(LocalDateTime.now());

        complaintRespository.save(complaint);

        Map<String, Object> complaintMap = new HashMap<>();
        complaintMap.put("id", complaint.getId());
        complaintMap.put("title", complaint.getTitle());
        complaintMap.put("description", complaint.getDescription());
        complaintMap.put("department", complaint.getDepartment());
        complaintMap.put("status", complaint.getStatus());
        complaintMap.put("createdAt", complaint.getCreatedAt());

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", user.getEmail());
        userMap.put("role", user.getRole());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("complaint", complaintMap);
        responseData.put("createdBy", userMap);

        ResponseHeadDTO head =
                new ResponseHeadDTO("success", 201, "Complaint registered successfully");

        return new ResponseEntity<>(
                new ResponseDTO<>(head, responseData),
                HttpStatus.CREATED
        );
    }
}
