package com.flashcloud.root;

import com.flashcloud.root.utils.EncryptionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EncryptionTest {

    private static EncryptionService encryptionService;

    @BeforeAll
    public static void init(){
        encryptionService = new EncryptionService();
    }

    @Test
    public void testEncryption(){
        String key = encryptionService.generateSalt();
        String password = "admin";
        String encryptedPassword = encryptionService.encryptValue(password, key);

        assertNotEquals(password, encryptedPassword);
    }

    @Test
    public void testDecryption(){
        String key = encryptionService.generateSalt();;
        String password = "admin";
        String encryptedPassword = encryptionService.encryptValue(password, key);
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, key);

        assertEquals(password, decryptedPassword);
    }
}
