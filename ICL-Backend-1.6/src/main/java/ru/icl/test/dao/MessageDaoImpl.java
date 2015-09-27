package ru.icl.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import ru.icl.test.entity.HibernateUtil;
import ru.icl.test.entity.Message;

public class MessageDaoImpl implements MessageDao{

    @Override
    public void addMessage(Message message) throws SQLException {
        Session sessionFactory = null;
        try{
            sessionFactory = HibernateUtil.getSessionFactory().openSession();
            sessionFactory.beginTransaction();            
            sessionFactory.save(message);
            sessionFactory.getTransaction().commit();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if((sessionFactory != null) && (sessionFactory.isOpen()))
                sessionFactory.close();                
        }       
    }

    @Override
    public void deleteMessage(Message message) throws SQLException {
        Session sessionFactory = null;
        try{
            sessionFactory = HibernateUtil.getSessionFactory().openSession();
            sessionFactory.beginTransaction();
            sessionFactory.delete(message);
            sessionFactory.getTransaction().commit();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if((sessionFactory != null) && (sessionFactory.isOpen()))
                sessionFactory.close();
        }   
    }
    
    @Override
    public Message getMessage(int id) throws SQLException {
        Session sessionFactory = null;
        Message result = null;        
        try{           
            sessionFactory = HibernateUtil.getSessionFactory().openSession();
            result = (Message) sessionFactory.get(Message.class, id);
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if((sessionFactory != null) && (sessionFactory.isOpen()))
                sessionFactory.close();
        } 
        return result;  
    }

    @Override
    public List<Message> getMessages() throws SQLException {
        Session sessionFactory = null;        
        List<Message> messages = new ArrayList<Message>();               
        try{
            sessionFactory = HibernateUtil.getSessionFactory().openSession();
            messages = sessionFactory.createCriteria(Message.class).list();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if((sessionFactory != null) && (sessionFactory.isOpen()))
                sessionFactory.close();
        }
        return messages;
    }
    
}
