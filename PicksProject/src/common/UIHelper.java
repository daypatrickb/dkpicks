package common;

import java.io.PrintWriter;

import service.UserService;

import model.User;



public class UIHelper {

	
	
	private static String[] playoffTitleClasses = {"s61","s68","s71","s77"};
	private static int[] playoffScoreMultipliers = {2,3,4,6};
	private static String[] playoffScoreColumnClasses = {"s66","s70","s76","s81"};
	
	

	
	public static void renderHeader(PrintWriter out, User user)
	{
		out.println(user.getNickname());
		
		out.println(":  <a href=\"/\" > Scoreboard </a> | ");
		
		if (UserService.isUserAdmin(user)) 
		{
			out.println("<a href=\"/admin\"> Admin Console </a> | ");
		}
		
		out.println("<a href=\"/analytics\"> Analytics </a> | ");

		out.println("<a href=\"/logout\"> Logout </a>");

		out.println("<br/><br/>");
		
		out.println("");
	}
	
	
	public static String getWeekTitle(int week)
	{
		if (week < 0 || week > 21)
		{
			return "";
		} else if (week < 18)
		{
			return "Week " + week;
		} else
		{
			switch (week)
			{
			case 18: return "Wild Card";
			case 19: return "Divisional";
			case 20: return "Championship";
			case 21: return "Super Bowl";
			default: return "";
			}
		}
	}
	
	
	public static String getTitleClassByWeek(int week)
	{
		if (week < 0 || week > 21)
		{
			return "";
		} else if (week < 18)
		{
			return "s28";
		} else
		{
			return playoffTitleClasses[week - 18];
		}
	}
	
	public static String getScoreMultiplierColumnClassByWeek(int week)
	{
		if (week < 0 || week > 21)
		{
			return "";
		} else if (week < 18)
		{
			return "s14";
		} else
		{
			return playoffScoreColumnClasses[week - 18];
		}
	}
	
	public static String getScoreMultiplierColumnValueByWeek(int week)
	{
		if (week < 18 || week > 21)
		{
			return "";
		}else
		{
			return "Points x"+playoffScoreMultipliers[week - 18];
		}
	}
	
	
	public static int getScoreMultiplierByWeek(int week)
	{
		if (week < 18 || week > 21)
		{
			return 1; // normal scoring
		} else
		{
			return playoffScoreMultipliers[week - 18];
		}
	}
	

	public static String getTDClassByPoints(int points)
	{
		
    	// here's how the colors go
    	// 4 == 1st  == s33
    	// 3 == 2nd  == s35
    	// 2 == 3rd  == s37
    	// 1 == 4th  == s34
    	// 0 == last == s36
		
		String tdClass = "s36";
    	
    	switch(points)
    	{
    	case 4:
    		tdClass = "s33";
    		break;
    	case 3:
    		tdClass = "s35";
    		break;
    	case 2:
    		tdClass = "s37";
    		break;
    	case 1:
    		tdClass = "s34";
    		break;
    	default:
    			
    	}
    	
    	return tdClass;
	}

	public static String getLoginURL()
	{
		if (PicksProperties.get("debug.login").equals("true"))
		{
			return "/debuglogin?user="+getDebugUserID();
		} else
		{
			return "/login.html";
		}
	}
	
	public static String getDebugUserID()
	{
		return PicksProperties.get("debug.login.userid");
	}

	
}
