package com.project.library.model;

public class Admin {

    private int admin_id;
    private String admin_fname;
    private String admin_lname;

    public Admin() {
    }

    public Admin(int id, String fname, String lname) {
        this.admin_id = id;
        this.admin_fname = fname;
        this.admin_lname = lname;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_fname() {
        return admin_fname;
    }

    public void setAdmin_fname(String admin_fname) {
        this.admin_fname = admin_fname;
    }

    public String getAdmin_lname() {
        return admin_lname;
    }

    public void setAdmin_lname(String admin_lname) {
        this.admin_lname = admin_lname;
    }
}
