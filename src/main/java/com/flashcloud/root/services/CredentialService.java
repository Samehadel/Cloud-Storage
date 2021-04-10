package com.flashcloud.root.services;

import com.flashcloud.root.model.Credential;
import com.flashcloud.root.model.Note;

import java.util.List;

public interface CredentialService {
    public int addCredential(Credential credential);
    public boolean dropCredential(int credentialId);
    public boolean editCredential(Credential credential);
    public List<Credential> getAllCredentials();
}
