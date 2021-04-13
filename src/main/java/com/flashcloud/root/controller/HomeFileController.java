package com.flashcloud.root.controller;

import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.File;
import com.flashcloud.root.model.Note;
import com.flashcloud.root.services.CredentialService;
import com.flashcloud.root.services.FileService;
import com.flashcloud.root.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeFileController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public String upload(@RequestParam("fileUpload") MultipartFile fileUpload,
                         RedirectAttributes redirectAttributes,
                         @ModelAttribute Note note,
                         @ModelAttribute Credential credential,
                         Model model) throws IOException {

        int check = fileService.addFile(fileUpload);

        if (check == -1) {
            model.addAttribute("hasError", true);
            model.addAttribute("hasSuccess", false);
            model.addAttribute("homeMessage", "Please select a file to be uploaded");
        } else if (check == 0) {
            model.addAttribute("hasError", true);
            model.addAttribute("hasSuccess", false);
            model.addAttribute("homeMessage", "File Already Exist");
        } else{
            model.addAttribute("hasError", false);
            model.addAttribute("hasSuccess", true);
            model.addAttribute("homeMessage", "You Uploaded Successfully " + fileUpload.getOriginalFilename());
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

    @GetMapping("/download/{fileId}")
    public ResponseEntity downloadFile(@PathVariable int fileId) {
        try {
            File file = fileService.getFileById(fileId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFileName() + "\"")
                    .body(file.getFileData());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId,
                             @ModelAttribute Note note,
                             @ModelAttribute Credential credential,
                             Model model) {

        fileService.deleteFile(fileId);

        //Prepare All Notes & Credentials & Files For Current Logged User
        List<Note> userNotes = noteService.getAllNotes();
        List<Credential> userCredentials = credentialService.getAllCredentials();
        List<File> userFiles = fileService.getFiles();

        model.addAttribute("notes", userNotes);
        model.addAttribute("credentials", userCredentials);
        model.addAttribute("files", userFiles);
        model.addAttribute("hasError", false);
        model.addAttribute("hasSuccess", true);
        model.addAttribute("homeMessage", "File Deleted Successfully");

        return "home";
    }

}
