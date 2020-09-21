package com.example.blood_bank;

public class Ambulance {

    public String name, phone, district;

    public Ambulance() {

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDistrict() {
        return district;
    }

    public Ambulance(String name, String phone, String district) {
        this.name = name;
        this.phone = phone;
        this.district = district;
    }

}
