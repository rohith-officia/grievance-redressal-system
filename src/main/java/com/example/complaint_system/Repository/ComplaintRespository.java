package com.example.complaint_system.Repository;

import com.example.complaint_system.Models.ComplaintsModel;
import com.example.complaint_system.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComplaintRespository extends JpaRepository<ComplaintsModel, Long> {
    List<ComplaintsModel> findByUser(UserModel user);
}
