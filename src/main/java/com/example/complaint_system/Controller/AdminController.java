package com.example.complaint_system.Controller;

import com.example.complaint_system.DTO.ResponseDTO;
import com.example.complaint_system.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("getComaplaints/")
    public ResponseEntity<ResponseDTO<List<Map<String , Object>>>>getComaplaints() {
        return adminService.getComaplaints();
    }

    @PostMapping("Update/Status/")
    public ResponseEntity<ResponseDTO<Map<String,Object>>> updateStatus(@RequestBody Map<String,String> status) {
        return adminService.updateStatus(status);
    }

}
