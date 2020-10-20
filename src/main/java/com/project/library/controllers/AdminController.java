package com.project.library.controllers;


import com.project.library.model.Admin;
import com.project.library.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path="/admins")
    public List<Admin> getAllCustomers(){
        return adminService.getAllAdmins();
    }

    @GetMapping(path="/admins/{adminId}")
    public Admin getAdminById(@PathVariable int adminId){
        return adminService.getAdminById(adminId);
    }

    @PostMapping(path = "/admins")
    public String addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return "An admin" + admin.getAdmin_id() + "has been successfully added";
    }

    @PutMapping(path = "/admins/update/{adminId}")
    public String updateAdminData(@PathVariable int adminId, @RequestParam(name = "admin_fname") String fname, @RequestParam(name = "admin_lname") String lname) {
        Admin updatedAdmin = null;
        try{
            updatedAdmin = adminService.getAdminById(adminId);
        } catch(Exception e) {
            return e.getMessage();
        }
        updatedAdmin.setAdmin_fname(fname);
        updatedAdmin.setAdmin_lname(lname);
        adminService.callAdminUpdater(updatedAdmin);
        return "The data of admin with ID " + adminId +" has been updated";
    }

    @DeleteMapping(path = "/admins/delete/{adminId}")
    public String deleteAdmin(@PathVariable int adminId) {
        try {
            adminService.deleteById(adminId);
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return "Admin with ID " + adminId + "deleted";
    }
}
