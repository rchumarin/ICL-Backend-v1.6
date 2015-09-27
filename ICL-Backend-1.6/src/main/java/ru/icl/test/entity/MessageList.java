package ru.icl.test.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ru.icl.test.dao.Factory;
import ru.icl.test.dao.MessageDao;

public class MessageList {    
    
    ArrayList<Message> messageList = new ArrayList<Message>();
    
    public List<Message> getMessage() throws SQLException {        
        Factory factory = Factory.getInstance();
        MessageDao messageDao = factory.getMessageDao();               
        return messageDao.getMessages();   
    }

    public List<Message> getMessageList() throws SQLException {
        if (!messageList.isEmpty()) {
            return messageList;
        } else {
            return getMessage();
        }
    }
}

