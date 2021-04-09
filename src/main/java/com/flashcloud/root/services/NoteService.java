package com.flashcloud.root.services;

import com.flashcloud.root.model.Note;

import java.util.List;

public interface NoteService {

    public int addNote(Note note);
    public boolean dropNote(int noteId);
    public boolean editNote(Note note);
    public List<Note> getAllNotes();

}
