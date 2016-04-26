package com.example.pc.onlinesoccer;

/**
 * Created by PC on 18-Apr-16.
 */
public class Validate {
    //Số
    public static boolean validateNumber (String id){
        if (id == null)
            return false;
        return id.matches("^\\d+");
    }
    //Username gồm chữ thường, chữ hoa, số và dấu gạch ngang ("-", "_"), độ dài từ 6-15
    public static boolean validateUsername (String userName){
        if (userName == null)
            return false;
        return userName.matches("^[a-zA-Z0-9_-]{6,15}$");
    }
    //Password gồm chữ thường, chữ hoa số và dấu gạch ngang ("-", "_"), độ dài từ 6-15
    public static boolean validatePassword (String password){
        if (password == null)
            return false;
        return password.matches("^[a-zA-Z0-9_-]{6,15}$");
    }
    //Ngày định dạng dd/MM/YY
    public static boolean isDay(final String value) {
        if (value == null)
            return false;
        return value.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((18|19|20)\\d\\d)");
    }
    //Email định dạng abc@xyz.com
    public static boolean validateEmail(String email) {
        if (email == null)
            return false;
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,6})$");
    }
}
