package com.flashcloud.root.services.impl;

import com.flashcloud.root.mapper.FileMapper;
import com.flashcloud.root.model.File;
import com.flashcloud.root.model.User;
import com.flashcloud.root.services.FileService;
import com.flashcloud.root.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImp implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserService userService;


    @Override
    public int addFile(MultipartFile fileUpload) throws IOException {
        File file = new File();
        int userId = getUserId();

        String o = fileUpload.getOriginalFilename();
        if(fileUpload.getOriginalFilename().equals("")) return -1;

        String fileName = StringUtils.cleanPath(fileUpload.getOriginalFilename());

        //Set File Attributes
        file.setFileName(fileName);
        file.setContentType(fileUpload.getContentType());
        file.setFileSize(fileUpload.getSize());
        file.setFileData(fileUpload.getBytes());
        file.setUserId(userId);

        if(isFileExist(file)){
            return 0;
        }

        return fileMapper.insert(file);
    }

    @Override
    public List<File> getFiles() {
        int userId = getUserId();

        return fileMapper.findAll(userId);
    }

    @Override
    public File getFileById(int fileId) {
        return fileMapper.findFileById(fileId);
    }

    @Override
    public void deleteFile(int fileId) {
        fileMapper.delete(fileId);
    }

    private boolean isFileExist(File file){
        return fileMapper.findFile(file) != null;
    }

    private int getUserId(){
        //Retrieve Username From ContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //Find UserId Corresponds To Username
        User user = userService.getUser(username);

        return user.getUserId();
    }
}
