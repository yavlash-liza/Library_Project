package com.yavlash.library.util;

import java.util.ResourceBundle;

public enum PropertyManager {
    DATABASE_CONFIG(ResourceBundle.getBundle("config/database"));

    private final ResourceBundle bundle;

    PropertyManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getString(String key) {
        return bundle.getString(key);
    }
}