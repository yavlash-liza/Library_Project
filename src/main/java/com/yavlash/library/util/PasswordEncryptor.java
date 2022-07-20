package com.yavlash.library.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordEncryptor {
    public String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    public boolean verifyPassword(String hashPassword, String verifiablePassword) {
        BCrypt.Result verify = BCrypt.verifyer().verify(hashPassword.trim().toCharArray(), verifiablePassword.trim());
        return verify.verified;
    }
}