package com.project.library.services;

import com.project.library.model.Admin;
import com.project.library.model.Customer;
import com.project.library.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.getAllAdmins();
    }

    public void addAdmin(Admin admin) {
        adminRepository.insertAdmin(admin);
    }

    public Admin getAdminById(int adminId) {
        return adminRepository.getAdminById(adminId);
    }

    public void deleteById(int adminId) {
        adminRepository.deleteById(adminId);
    }

    public void callAdminUpdater(Admin updatedAdmin) {
        adminRepository.updateAdminData(updatedAdmin);
    }
}
