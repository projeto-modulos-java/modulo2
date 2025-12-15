package com.modulo2.auth.configurations.security;

public class Permission {
    private boolean admin;
    private boolean user;

    public Permission(boolean admin, boolean user) {
        this.admin = admin;
        this.user = user;
    }

     public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }
}
