package com.flashcloud.root.model;

public class Credential {

    private int credentialId;
    private String url;
    private String username;
    private String keyValue;
    private String displayedPassword;
    private String password;
    private int userId;


    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDisplayedPassword(String displayedPassword) {
        this.displayedPassword = displayedPassword;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCredentialId() {
        return credentialId;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public String getDisplayedPassword() {
        return displayedPassword;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }
}
