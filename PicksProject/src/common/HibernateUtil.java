package common;

import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import model.Player;
import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
  
public class HibernateUtil {  
      
    private static final SessionFactory sessionFactory;  

      
    static {  
    	// A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();   
    }  
      
    private static SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
    
    public static Session getSession()
    {
    	return sessionFactory.openSession();
    }
    
    public static void main(String[] args) {
		
    	
    	Session session = sessionFactory.openSession();
//    	session.beginTransaction();
//    	session.save( new Player( "Our very first event!", new Date() ) );
//    	session.save( new Player( "A follow up event", new Date() ) );
//    	session.getTransaction().commit();
//    	session.close();
//    
    	
    	session.beginTransaction();
    	List result = session.createQuery( "from User" ).list();
    	for ( User player : (List<User>) result ) {
    	    System.out.println( player.getNickname() + " " + player.getGoogleID() + " " + player.getPlayer() );
    	}
    	session.getTransaction().commit();
    	session.close();
    	
    	
    	Session sess = sessionFactory.openSession();
    	
    	List<Player> cats = sess.createCriteria(Player.class)
    		    .add( Restrictions.like("name", "D%") )
    		    //.add( Restrictions.between("weight", minWeight, maxWeight) )
    		    .list();
    	
    	
    	for (Player o : cats) System.out.println(o.getName());
    	
	}
    
    
    
    
    
    
    
    
    
    
    
    
}  