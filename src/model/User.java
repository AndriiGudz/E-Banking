package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public enum Role {USER, ADMIN}

    private static int nexId = 1;
    private final int userId;
    private String username;
    private String email;
    private String password;
    private  Role role;
    private boolean isAdmin;

    public User(String email, String password) {
        this.userId = nexId++;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAdmin = false;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }


}