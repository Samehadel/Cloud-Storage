package com.flashcloud.root.services;

import com.flashcloud.root.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    public int addFile(MultipartFile file) throws IOException;

    List<File> getFiles();

    File getFileById(int fileId);

    void deleteFile(int fileId);
}
