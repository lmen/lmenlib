package pt.lmen.lib.activemq;

import java.time.LocalDateTime;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueuePublisher {
    
    private static final Logger logger = LogManager.getLogger( QueuePublisher.class );
    
    private MessageProducer msgProd;
    private Session session;
    private Connection conn;
    private String url; 
    private String queueName;
    
    public void start(JmsConfig config) throws JMSException {
        
        url = config.getUrl();
        queueName = config.getQueueInName();
        logger.info("starting with url : {} queueName: {} ", url, queueName);
        
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory( url );
        
        conn = connectionFactory.createConnection();
        
        conn.start();
        
        session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Queue queue = session.createQueue( queueName );
                
        msgProd = session.createProducer( queue );        
        msgProd.setDeliveryMode( DeliveryMode.PERSISTENT );
    }
    
    public void send(String text) throws JMSException {
                
        logger.info("sending message to queue: {} {} ", url, queueName);
        
        TextMessage txtMsg = session.createTextMessage(text);
        
        msgProd.send( txtMsg );
        
        logger.info("message sent to queue: {} {} ", url, queueName);
    }
    
    public void close() throws JMSException {
        session.close();
        
        conn.close();
    }

    public static void main( String[] args ) throws JMSException {
        QueuePublisher pub = new QueuePublisher();
        pub.start(new JmsConfig());
        pub.send( "dsds "  + LocalDateTime.now());
        pub.send( "second " + LocalDateTime.now() );
        pub.close();
    }
}
