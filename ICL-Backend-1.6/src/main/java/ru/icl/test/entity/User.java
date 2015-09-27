package ru.icl.test.entity;

import java.util.HashSet;
import java.util.Set;

public class User  implements java.io.Serializable {

     private String clientid;
     private String fname;
     private Set messages = new HashSet(0);

    public User() {
    }
	
    public User(String clientid) {
        this.clientid = clientid;
    }
    public User(String clientid, String fname, Set messages) {
       this.clientid = clientid;
       this.fname = fname;
       this.messages = messages;
    }
   
    public String getClientid() {
        return this.clientid;
    }
    
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    public String getFname() {
        return this.fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }
    public Set getMessages() {
        return this.messages;
    }
    
    public void setMessages(Set messages) {
        this.messages = messages;
    }

}


