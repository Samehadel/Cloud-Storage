package com.flashcloud.root.services.impl;

import com.flashcloud.root.mapper.NoteMapper;
import com.flashcloud.root.model.Note;
import com.flashcloud.root.model.User;
import com.flashcloud.root.services.NoteService;
import com.flashcloud.root.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImp implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private UserService userService;

    @Override
    public int addNote(Note note) {

        int userId = getUserId(); //Get Current User's ID
        note.setUserId(userId);

        //Edit Note If It's Exist
        if(isNoteExist(note.getNoteId()))
        {
            editNote(note);
            return note.getNoteId();
        }

        return noteMapper.insert(note);
    }


    @Override
    public boolean dropNote(int noteId) {
        return noteMapper.deleteById(noteId);
    }

    @Override
    public boolean editNote(Note note) {
        return noteMapper.update(note);
    }

    @Override
    public List<Note> getAllNotes() {

        int userId = getUserId(); //Get Current User's ID

        return noteMapper.findAll(userId);
    }

    private boolean isNoteExist(int noteId){
        Note note = noteMapper.findById(noteId);

        return note == null ? false : true;
    }
    private int getUserId(){
        //Retrieve Username From ContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //Find UserId Corresponds To Username
        User user = userService.getUser(username);

        return user.getUserId();
    }

}
