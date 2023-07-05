package com.rpachallenge;

import java.util.HashMap;

public class DePara {
    private HashMap<String, String> map = new HashMap<>(); 
    public DePara(){
        this.map.put("labelFirstName", "First Name");
        this.map.put("labelLastName", "Last Name");
        this.map.put("labelCompanyName", "Company Name");
        this.map.put("labelRole", "Role in Company");
        this.map.put("labelAddress", "Address");
        this.map.put("labelEmail", "Email");
        this.map.put("labelPhone", "Phone Number");
    }
    public String get( String de ){
        return this.map.get(de);
    }
}