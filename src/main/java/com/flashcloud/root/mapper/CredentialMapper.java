package com.flashcloud.root.mapper;

import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, keyvalue, password, userid) " +
            "VALUES (#{url}, #{username}, #{keyValue}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    public int insert(Credential cred);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    public List<Credential> findAll(int userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    public Credential findById(int credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    public boolean deleteById(int credentialId);

    @Update("UPDATE CREDENTIALS SET " +
            "url = #{url}, " +
            "username = #{username}, " +
            "password = #{password} " +
            "WHERE credentialid = #{credentialId}")
    public boolean update(Credential credential);
}
