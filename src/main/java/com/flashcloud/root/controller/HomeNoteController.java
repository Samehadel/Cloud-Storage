package com.flashcloud.root.controller;

import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.File;
import com.flashcloud.root.model.Note;
import com.flashcloud.root.model.User;
import com.flashcloud.root.services.CredentialService;
import com.flashcloud.root.services.FileService;
import com.flashcloud.root.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeNoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;


    @GetMapping
    public String getHome(@ModelAttribute Credential credential,
                          @ModelAttribute Note note,
                          Model model){

        //Prepare All Notes & Credentials & Files For Current Logged User
        List<Credential> userCredentials = credentialService.getAllCredentials();
        List<Note> userNotes = noteService.getAllNotes();
        List<File> userFiles = fileService.getFiles();

        model.addAttribute("notes", userNotes);
        model.addAttribute("credentials", userCredentials);
        model.addAttribute("files", userFiles);

        return "home";
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute Note note,
                          @ModelAttribute Credential credential,
                          Model model){

        //Add The Note
        int check = noteService.addNote(note);

        if(check == -1){
            model.addAttribute("hasError", true);
            model.addAttribute("hasSuccess", false);
            model.addAttribute("homeMessage", "Note Already Exists! Try Another Shot");

        }else if(check == 0){
            model.addAttribute("hasError", true);
            model.addAttribute("hasSuccess", false);
            model.addAttribute("homeMessage", "Error While Saving Note. Please Try Again");

        }else if(check == -2){
            model.addAttribute("hasError", false);
            model.addAttribute("hasSuccess", true);
            model.addAttribute("homeMessage", "Note Updated Successfully");

        }else{
            model.addAttribute("hasError", false);
            model.addAttribute("hasSuccess", true);
            model.addAttribute("homeMessage", "Note Added Successfully");

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

    @GetMapping("/note/delete/{noteId}")
    public String dropNote(@PathVariable int noteId,
                           @ModelAttribute Note note,
                           @ModelAttribute Credential credential,
                           Model model){

        //Drop The Note
        noteService.dropNote(noteId);

        //Prepare All Notes & Credentials & Files For Current Logged User
        List<Note> userNotes = noteService.getAllNotes();
        List<Credential> userCredentials = credentialService.getAllCredentials();
        List<File> userFiles = fileService.getFiles();

        model.addAttribute("notes", userNotes);
        model.addAttribute("credentials", userCredentials);
        model.addAttribute("files", userFiles);
        model.addAttribute("hasError", false);
        model.addAttribute("hasSuccess", true);
        model.addAttribute("homeMessage", "Note Deleted Successfully");

        return "home";
    }
}
