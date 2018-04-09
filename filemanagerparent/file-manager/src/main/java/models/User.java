package models;

@SuppressWarnings("unused")
public class User {
    private String name;
    private String email;
    private String username;
    private String password_hash;

    public User() {
    }

    public User(String name, String email, String username, String password_hash) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password_hash = password_hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
