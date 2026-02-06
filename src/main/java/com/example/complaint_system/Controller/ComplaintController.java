package com.example.complaint_system.Controller;

import com.example.complaint_system.DTO.ComplaintRequestDTO;
import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.DTO.ResponseHeadDTO;
import com.example.complaint_system.Service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("complaints/")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> registerComplaint(@RequestBody ComplaintRequestDTO  complaintRequestDTO) {
        if(complaintRequestDTO.getDepartment() == null || complaintRequestDTO.getDescription() == null || complaintRequestDTO.getTitle() == null) {
            ResponseHeadDTO responseHeadDTO = new ResponseHeadDTO("Unsuccessful" , 200 , "Invalid input");
            ResponseDTO responseDTO = new ResponseDTO(responseHeadDTO , null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
        return complaintService.registerComplaint(complaintRequestDTO);
    }
}
