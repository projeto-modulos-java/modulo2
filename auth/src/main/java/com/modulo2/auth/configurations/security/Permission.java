package com.modulo2.auth.configurations.security;

public class Permission {
    public boolean admin;
    public boolean user;
    
    public Permission(boolean admin, boolean user) {
        this.admin = admin;
        this.user = user;
    }
}
