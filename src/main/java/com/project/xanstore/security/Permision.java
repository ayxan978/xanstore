package com.project.xanstore.security;

public enum Permision {
    ADMIN_READ("data:read"),
    ADMIN_WRITE("data:write");

    private final String PermisionInfo;

    Permision(String permisionInfo) {
        PermisionInfo = permisionInfo;
    }

    public String getPermisionInfo() {
        return PermisionInfo;
    }
}
