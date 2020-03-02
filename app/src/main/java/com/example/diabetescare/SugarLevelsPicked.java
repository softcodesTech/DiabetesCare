package com.example.diabetescare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SugarLevelsPicked {
    @SerializedName("level")
    @Expose
    private Integer Name;


    public Integer getName() {
        return Name;
    }

    public void setName(Integer name) {
        Name = name;
    }

//        public String getAge() {
//            return Age;
//        }
//
//        public void setAge(String age) {
//            Age = age;
//        }
//
//        public String getPhone() {
//            return Phone;
//        }
//
//        public void setPhone(String phone) {
//            Phone = phone;
//        }
//
//        public String getEmail() {
//            return Email;
//        }
//
//        public void setEmail(String email) {
//            Email = email;
//        }
}


