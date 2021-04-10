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
    public String getHome(@ModelAttribute Note note,
                          @ModelAttribute Credential credential,
                          Model model){

        //Prepare All Notes & Credentials & Files For Current Logged User
        List<Note> userNotes = noteService.getAllNotes();
        List<Credential> userCredentials = credentialService.getAllCredentials();
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
        noteService.addNote(note);

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

        return "home";
    }
}
