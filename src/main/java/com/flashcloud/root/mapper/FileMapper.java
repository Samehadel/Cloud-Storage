package com.flashcloud.root.mapper;

import com.flashcloud.root.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
            "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    public int insert(File file);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    public File findFileById(int fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}" +
            " AND contenttype = #{contentType}" +
            " AND userid = #{userId}")
    public File findFile(File file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    public List<File> findAll(int userId);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    public void delete(int fileId);
}
