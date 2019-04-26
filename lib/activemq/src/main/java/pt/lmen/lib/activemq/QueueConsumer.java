package slib.rmt.tools.injector;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueConsumer {

    private MessageConsumer msgConsumer;
    private Session session;
    private Connection conn;
    private String url;
    private String queueName;
    private long timeout;

    public void start( String url, String queueName, int timeout ) throws JMSException {

        this.url = url;
        this.queueName = queueName;
        this.timeout = timeout;

        System.out.format( "\nstarting with url : %s queueName: %s timeout: %s", url, queueName, timeout );

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory( url );

        conn = connectionFactory.createConnection();

        conn.start();

        session = conn.createSession( false, Session.AUTO_ACKNOWLEDGE );

        Queue queue = session.createQueue( queueName );

        msgConsumer = session.createConsumer( queue );
    }

    public String waitUntilReceive() throws JMSException {

        System.out.format( "\nreceiving message from queue: %s %s ", url, queueName );

        Message msg = msgConsumer.receive( timeout );

        try {
            if ( msg == null ) {
                // is a timeout situation
                return null;
            }

            if ( !( msg instanceof TextMessage ) ) {
                throw new JMSException( "\nWrong message type. Only text supported. Type received: " + msg.getClass() );
            }

            String content = ( (TextMessage)msg ).getText();

            System.out.format( "\nmessage received from queue: %s %s : Content %s\n", url, queueName, content );
            return content;
        }
        finally {
            if ( msg != null ) {
                msg.acknowledge();
            }
        }

    }

    public void close() throws JMSException {
        session.close();

        conn.close();
    }

    public static void main( String[] args ) throws JMSException {
        QueueConsumer sub = new QueueConsumer();
        sub.start( "failover://(tcp://localhost:61616)", "", 10 * 100 );
        try {

            System.out.format( ">" + sub.waitUntilReceive() );
            System.out.format( ">" + sub.waitUntilReceive() );
            System.out.format( ">" + sub.waitUntilReceive() );
        }
        finally {
            sub.close();
        }
    }

}
