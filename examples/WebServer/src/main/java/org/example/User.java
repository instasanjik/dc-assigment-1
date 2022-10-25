package org.example;

import java.net.Socket;

public class User {
    public int id;
    public String first_name;
    public String last_name;
    public String email;
    public String gender;
    public String ip_address;


    public User(int id, String first_name, String last_name, String email, String gender, String ip_address) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.ip_address = ip_address;
    }

}
