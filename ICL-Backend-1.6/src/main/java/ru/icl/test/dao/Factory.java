package ru.icl.test.dao;

public class Factory {
    private static Factory instance = new Factory();
    private MessageDao messagedao;
    private UserDao userdao;
    
    private Factory() { }
    
    public static Factory getInstance() {
        return Factory.instance;
    }
    
    public MessageDao getMessageDao() {
        if(messagedao == null) messagedao = new MessageDaoImpl();
        return messagedao;                  
    }
    
    public UserDao getUserDao() {
        if(userdao == null) userdao = new UserDaoImpl();
        return userdao;                  
    }    
}

