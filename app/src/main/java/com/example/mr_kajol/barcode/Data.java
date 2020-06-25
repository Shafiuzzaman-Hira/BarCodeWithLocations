package com.example.mr_kajol.barcode;

public class Data {
    String Name, Email;

    public void Data(){};

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Data(String name, String email) {
        Name = name;
        Email = email;
    }
}