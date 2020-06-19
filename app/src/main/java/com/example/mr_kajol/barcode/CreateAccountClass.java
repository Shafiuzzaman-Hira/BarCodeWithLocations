package com.example.mr_kajol.barcode;

import org.w3c.dom.CDATASection;

public class CreateAccountClass {
    private String Name,Address,Email,Phone,Password, Date;

    public CreateAccountClass(){};

    public CreateAccountClass(String name, String address, String email, String phone, String password, String date) {
       this.Name = name;
        this.Address = address;
        this.Email = email;
        this.Phone = phone;
        this.Password = password;
        this.Date = date;
    }
}
