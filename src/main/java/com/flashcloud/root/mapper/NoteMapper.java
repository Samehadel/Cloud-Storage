package com.flashcloud.root.mapper;

import com.flashcloud.root.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid)" +
            " VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    public int insert(Note note);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    public Note findById(int noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    public List<Note> findAll(int userId);

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle} AND notedescription = #{noteDescription}")
    public Note findByBody(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    public boolean deleteById(int noteId);

    @Update("UPDATE NOTES SET " +
            "notetitle = #{noteTitle}, " +
            "notedescription = #{noteDescription} " +
            "WHERE noteid = #{noteId}")
    public boolean update(Note note);
}
