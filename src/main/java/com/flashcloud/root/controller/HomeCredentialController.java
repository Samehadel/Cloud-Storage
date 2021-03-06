package com.flashcloud.root.controller;

import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.File;
import com.flashcloud.root.model.Note;
import com.flashcloud.root.services.CredentialService;
import com.flashcloud.root.services.FileService;
import com.flashcloud.root.services.NoteService;
import com.flashcloud.root.utils.UserInputsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeCredentialController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;


    @PostMapping("/credential")
    public String addNote(@ModelAttribute Credential credential,
                          @ModelAttribute Note note,
                          Model model){

        //Add The Credential
        int check = credentialService.addCredential(credential);

        if(!UserInputsChecker.checkCredInputs(credential)){
            model.addAttribute("hasError", true);
            model.addAttribute("hasSuccess", false);
            model.addAttribute("homeMessage", "Invalid Input");
        }
        else if(check == -2){
            model.addAttribute("hasError", false);
            model.addAttribute("hasSuccess", true);
            model.addAttribute("homeMessage", "Credential Updated Successfully");
        }else{
            model.addAttribute("hasError", false);
            model.addAttribute("hasSuccess", true);
            model.addAttribute("homeMessage", "Credential Created Successfully");
        }
        //Prepare All Notes & Credentials & Files For Current Logged User
        List<Note> userNotes = noteService.getAllNotes();
        List<Credential> userCredentials = credentialService.getAllCredentials();
        List<File> userFiles = fileService.getFiles();

        model.addAttribute("notes", userNotes);
        model.addAttribute("credentials", userCredentials);
        model.addAttribute("files", userFiles);



        return "home";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String dropCredential(@PathVariable int credentialId,
                                 @ModelAttribute Note note,
                                 @ModelAttribute Credential credential,
                                 Model model){

        //Drop The Note
        credentialService.dropCredential(credentialId);

        //Prepare All Notes & Credentials & Files For Current Logged User
        List<Note> userNotes = noteService.getAllNotes();
        List<Credential> userCredentials = credentialService.getAllCredentials();
        List<File> userFiles = fileService.getFiles();

        model.addAttribute("notes", userNotes);
        model.addAttribute("credentials", userCredentials);
        model.addAttribute("files", userFiles);
        model.addAttribute("hasError", false);
        model.addAttribute("hasSuccess", true);
        model.addAttribute("homeMessage", "Credential Deleted Successfully");

        return "home";
    }
}