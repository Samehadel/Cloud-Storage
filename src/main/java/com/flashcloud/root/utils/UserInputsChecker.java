package com.flashcloud.root.utils;

import com.flashcloud.root.model.User;

public class UserInputsChecker {

    public static boolean checkSignUp(User user){
        return (checkFirstName(user.getFirstName()) &&
                checkLastName(user.getLastName())) &&
                checkUsername(user.getUsername()) &&
                checkPassword(user.getPassword());
    }

    private static boolean checkFirstName(String firstName){
        if(firstName.length() > 20) return false;
        return true;
    }
    private static boolean checkLastName(String lastName){

        if(lastName.length() > 20) return false;

        return true;
    }

    private static boolean checkUsername(String username){
        if(username.length() > 35) return false;

        return true;
    }
    private static boolean checkPassword(String password){
        if(password.length() > 25) return false;

        return true;
    }


}
