package com.flashcloud.root.services.impl;

import com.flashcloud.root.mapper.CredentialMapper;
import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.Note;
import com.flashcloud.root.model.User;
import com.flashcloud.root.services.CredentialService;
import com.flashcloud.root.services.UserService;
import com.flashcloud.root.utils.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialServiceImp implements CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptionService encryptionService;


    @Override
    public int addCredential(Credential credential) {
        int userId = getUserId(); //Get Logged User's ID
        credential.setUserId(userId);

        Credential tempCredential = getCredential(credential.getCredentialId());

        //Edit Credential If It's Exist
        if(tempCredential != null)
        {
            //Password Changed & Need Encryption
            if(!tempCredential.getPassword().equals(credential.getPassword())){
                String keyValue = tempCredential.getKeyValue();
                String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), keyValue);

                credential.setPassword(encryptedPassword); //Modify Credential Password
            }
            editCredential(credential);

            return credential.getCredentialId();
        }

        //First Time Creation
        String keyValue = encryptionService.generateSalt();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), keyValue);

        credential.setKeyValue(keyValue);

        //Update Credential Password To Encrypted One
        credential.setPassword(encryptedPassword);

        //Create Credential And Return ID
        return credentialMapper.insert(credential);
    }

    @Override
    public boolean dropCredential(int credentialId) {
        return credentialMapper.deleteById(credentialId);
    }

    @Override
    public boolean editCredential(Credential credential) {
        return credentialMapper.update(credential);
    }

    @Override
    public List<Credential> getAllCredentials() {

        int userId = getUserId(); //Get Current User's ID

        return credentialMapper.findAll(userId);

    }

    private Credential getCredential(int credentialId){
        return credentialMapper.findById(credentialId);
    }

    private int getUserId(){
        //Retrieve Username From ContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //Find UserId Corresponds To Username
        User user = userService.getUser(username);

        return user.getUserId();
    }
}
