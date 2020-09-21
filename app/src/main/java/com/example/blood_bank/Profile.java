package com.example.blood_bank;

import java.io.Serializable;

public class Profile {
    public String name, phone, yes_no, blood, district, division, full_address,password;

    public Profile() {

    }

    public Profile(String name, String phone, String yes_no, String blood, String district, String division, String full_address,String password) {
        this.name = name;
        this.phone = phone;
        this.yes_no = yes_no;
        this.blood = blood;
        this.district = district;
        this.division = division;
        this.full_address = full_address;
        this.password = password;



    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getYes_no() {
        return yes_no;
    }

    public String getBlood() {
        return blood;
    }

    public String getDistrict() {
        return district;
    }

    public String getDivision() {
        return division;
    }

    public String getFull_address() {
        return full_address;
    }
    public String getPassword() {
        return password;
    }
}


