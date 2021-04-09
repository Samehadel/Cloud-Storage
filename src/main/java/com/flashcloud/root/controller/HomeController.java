package com.flashcloud.root.controller;

import com.flashcloud.root.model.Note;
import com.flashcloud.root.model.User;
import com.flashcloud.root.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String getHome(@ModelAttribute Note note, Model model){

        //Prepare All Notes For Current Logged User
        List<Note> userNotes = noteService.getAllNotes();

        model.addAttribute("notes", userNotes);

        return "home";
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute Note note, Model model){

        //Add The Note
        noteService.addNote(note);

        //Retrieve All Notes
        List<Note> userNotes = noteService.getAllNotes();

        model.addAttribute("notes", userNotes);

        return "home";
    }

    @GetMapping("/note/delete/{noteId}")
    public String dropNote(@PathVariable int noteId,
                           @ModelAttribute Note note,
                           Model model){

        //Drop The Note
        noteService.dropNote(noteId);

        //Retrieve All Notes
        List<Note> userNotes = noteService.getAllNotes();

        model.addAttribute("notes", userNotes);

        return "home";
    }
}
