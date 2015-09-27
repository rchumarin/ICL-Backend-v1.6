package ru.icl.test.entity;

public class Message implements java.io.Serializable {

     private int id;
     private User user;
     private String message;

    public Message() {
    }
	
    public Message(int id) {
        this.id = id;
    }
    public Message(int id, User user, String message) {
       this.id = id;
       this.user = user;
       this.message = message;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

}


