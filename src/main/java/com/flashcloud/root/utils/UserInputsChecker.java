package com.flashcloud.root.utils;

import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.Note;
import com.flashcloud.root.model.User;

public class UserInputsChecker {

    public static boolean checkSignUp(User user){
        return (checkFirstName(user.getFirstName()) &&
                checkLastName(user.getLastName())) &&
                checkUsername(user.getUsername()) &&
                checkPassword(user.getPassword());
    }

    public static boolean checkNoteInputs(Note note){
        return (checkNoteTitle(note.getNoteTitle()) &&
                checkNoteTBody(note.getNoteDescription()));
    }

    public static boolean checkCredInputs(Credential cred){
        return (checKCredUrl(cred.getUrl()) &&
                checKCredUsername(cred.getUsername()));
    }

    private static boolean checKCredUrl(String url){
        return url.length() <= 100;
    }
    private static boolean checKCredUsername(String username){
        return username.length() <= 35;
    }


    private static boolean checkNoteTitle(String title){
        return title.length() <= 25;
    }
    private static boolean checkNoteTBody(String body){
        return body.length() <= 1000;
    }

    private static boolean checkFirstName(String firstName){
        return firstName.length() <= 20;
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
