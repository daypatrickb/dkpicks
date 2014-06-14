package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import common.Constants;
import common.HibernateUtil;

public class UserService {

	
	
	
	public static User getUserByID(String gPlusID)
	{
		if (gPlusID == null || gPlusID.trim().length() == 0) return null;
		
		
		Session sess = HibernateUtil.getSession();
		
		List<User> user = sess.createCriteria(User.class)
			    .add( Restrictions.eq("googleID", gPlusID) )
			    .setMaxResults(1)
			    .list();
		
		if (user != null && !user.isEmpty())
		{
			return user.get(0);
		}
		
		return null;
	}
	
	public static User getUser(HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		if (session == null)
		{
			return null;
		}
		
		Object key = session.getAttribute(Constants.SESSION_USER_ID_KEY);
		
		if (key == null)
		{
			return null;
		} 
		
		return (User) key;
	}


	public static boolean isUserAdmin(User user) {
		if (user == null)
		{
			return false;
		}
		
		
		
		
		return user.getNickname().equalsIgnoreCase("Dylan");
	}
	
	
	
	
	
}
