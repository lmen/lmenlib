package com.github.lmen.lib.activemq;

import java.time.LocalDateTime;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueueSubscriber {
    
    private static final Logger logger = LogManager.getLogger( QueueSubscriber.class );
    
    private MessageConsumer msgConsumer;
    private Session session;
    private Connection conn;
    private String url; 
    private String queueName;
    private long timeout;
    
    public void start(JmsConfig config) throws JMSException {
        
        url = config.getUrl();
        queueName = config.getQueueOutName();
        timeout = config.getTimeout();
        logger.info("starting with url : {} queueName: {} timeout: {}", url, queueName, timeout);
        
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory( url );
        
        conn = connectionFactory.createConnection();
        
        conn.start();
        
        session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Queue queue = session.createQueue( queueName );
                
        msgConsumer = session.createConsumer( queue );            
    }
    
    public String waitUntilReceive() throws JMSException {
                
        logger.info("receiving message from queue: {} {} ", url, queueName);
        
        Message msg = msgConsumer.receive( timeout );
        
        if (msg == null) {
            throw new JMSException( "No Message was received!" );
        }
        
        if (!(msg instanceof TextMessage)) {
            throw new JMSException( "Wrong message type. Only text supported. Type received: " + msg.getClass() );
        }
        
        String content = ((TextMessage)msg).getText();
        
        msg.acknowledge();
        
        logger.info("message received from queue: {} {} : Content {}", url, queueName, content);
        return content;
    }
    
    public void close() throws JMSException {
        session.close();
        
        conn.close();
    }

    public static void main( String[] args ) throws JMSException {
        QueueSubscriber sub = new QueueSubscriber();
        QueuePublisher pub = new QueuePublisher();
        JmsConfig config = new JmsConfig();
        config.setQueueInName( "AAA" );
        config.setQueueOutName( config.getQueueInName() );
        pub.start(config);
        sub.start(config);
         try {
            pub.send( "dsds "  + LocalDateTime.now());
            pub.send( "second " + LocalDateTime.now() );
            
            System.out.println( ">" + sub.waitUntilReceive());
            System.out.println( ">" + sub.waitUntilReceive());
            System.out.println( ">" + sub.waitUntilReceive());
         } finally {
             pub.close();
             sub.close();
        }
    }
}
