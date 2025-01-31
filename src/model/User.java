package model;

public class User {
    private String userName;
    private String password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public boolean authenticate(String userName, String password){
        return this.userName.equals(userName) && this.password.equals(password);
    }


}
