package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.League;
import model.Player;
import model.User;
import model.Year;
import service.UserService;

import common.Constants;
import common.TestHelper;
import common.UIHelper;


public class ScoreboardServlet extends HttpServlet 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8590232104663867458L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		User user = UserService.getUser(req);

		if (user == null)
		{
			resp.sendRedirect(UIHelper.getLoginURL());		
        	return;
		}
		

        resp.setContentType("text/html");
        
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Scoreboard</title><link rel=\"stylesheet\" href=\"static/scoreboard.css\" type=\"text/css\" /></head><body>");


		
		       
		UIHelper.renderHeader(out, user);
		
        
        if (UserService.isUserAdmin(user))
        {

        	String refresh = req.getParameter("refresh");
     		
     		if (refresh != null)
     		{
     			TestHelper.getTestLeague();
     			
     			resp.sendRedirect("/");
     			return;
     			
     		}
            
            resp.getWriter().println("<a href=\"/?refresh=1\">Refresh league</a>");
        }
        

        
        out.println("<br/><br/>");
        
        String yearParam = req.getParameter("year");
		
        int year;
		if (yearParam == null)
		{ 
			// use current year
			year = 2014;
			
		} else
		{
			resp.getWriter().println("<br/>Year " + yearParam);
			
			year = Integer.parseInt(yearParam);
			
		}
        
		
		League league = League.getTheLeague();
		
		Year yr = league.getYear(year);
        
		
		
		if (yr == null)
		{
			out.println("<h1>There is no year " + year + "</h1>");
		} else
		{
			out.println("<h1>Year " + year + "</h1>");
			// draw the table
			renderTable(out, league, yr, user);
		}
        
        out.println("<br/><br/>");
        
        out.println("<a href=\"/logout\">Logout</a>");
        out.println("</body></html>");
            

	}
	
	private void renderTable(PrintWriter out, League league, Year year, User user)
	{
		List<Player> players = league.getPlayers();
		
		int numPlayers = players.size();
        
        out.println("<table dir=\"ltr\" class=\"tblGenFixed\" id=\"tblMain\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> ");
        out.println("<tbody>");
        
        
        // step 1, header columns set the width
        out.println("<tr class=\"rShim\">");
        out.println("	<td class=\"rShim\" style=\"width: 0;\"></td>");
        out.println("	<td class=\"rShim\" style=\"width: 120px;\"></td>");
        out.println("	<td class=\"rShim\" style=\"width: 16px;\"></td>");
        
        for (int i = 0 ; i < numPlayers ; i++)
        {
        	int colSize = Math.max(50, players.get(i).getName().length() * 9);
        	
            out.println("	<td class=\"rShim\" style=\"width: "+colSize+"px;\"></td>");
        }

        out.println("	<td class=\"rShim\" style=\"width: 16px;\"></td>");
        for (int i = 0 ; i < numPlayers ; i++)
        {
        	int colSize = Math.max(50, players.get(i).getName().length() * 9);
            out.println("	<td class=\"rShim\" style=\"width: "+colSize+"px;\"></td>");
        }

        out.println("	<td class=\"rShim\" style=\"width: 64px;\"></td>");
        out.println("</tr>");
        

        out.println("<tr dir=\"ltr\">");
        out.println("	<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
        out.println("	<td class=\"s0\"></td>");
        out.println("	<td class=\"s1\"></td>");
        out.println("	<td colspan=\""+numPlayers+"\" dir=\"ltr\" class=\"s2\">Total Weekly Points</td>");
        for (int i = 0 ; i < numPlayers - 1; i++)
        {
        	out.println("	<td style=\"display: none;\"></td>");
        }
        out.println("	<td class=\"s3\"></td>");
        out.println("	<td colspan=\""+numPlayers+"\" dir=\"ltr\" class=\"s2\">Total Points</td>");
        for (int i = 0 ; i < numPlayers - 1; i++)
        {
        	out.println("	<td style=\"display: none;\"></td>");
        }
        out.println("	<td class=\"s4\"></td>");
        out.println("</tr>");

        
        
        out.println("<tr dir=\"ltr\">");
        out.println("	<td class=\"hd\"><p style=\"height: 14px;\">.</p></td>");
        out.println("	<td class=\"s6\"></td>");
        out.println("	<td class=\"s7\"></td>");
        
        for (Player p : players)
        {
        	out.println("	<td dir=\"ltr\" class=\""+p.getHeaderColumnClass()+"\">"+p.getName()+"</td>");
        }
     
        out.println("	<td class=\"s13\"></td>");
        
        for (Player p : players)
        {
        	out.println("	<td dir=\"ltr\" class=\""+p.getHeaderColumnClass()+"\">"+p.getName()+"</td>");
        }

        out.println("	<td class=\"s14\"></td>");
        out.println("</tr>");
        
        
        out.println("<tr dir=\"ltr\">");
        out.println("	<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
        out.println("	<td class=\"s17\"></td>");
        out.println("	<td class=\"s18\"></td>");
        for (int i = 0 ; i < numPlayers ; i++)
        {
        	out.println("	<td class=\"s19\"></td>");
        }
        
        out.println("	<td class=\"s19\"></td>");
        
        for (int i = 0 ; i < numPlayers ; i++)
        {
        	out.println("	<td class=\"s19\"></td>");
        }

        out.println("	<td class=\"s14\"></td>");
        out.println("</tr>");
        
        
        out.println("<tr dir=\"ltr\">");
        out.println("	<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
        out.println("	<td dir=\"ltr\" class=\"s21\">Total</td>");
        out.println("	<td class=\"s22\"></td>");
        for (Player player : players)
        {
        	int points = year.getWeeklyPoints(Constants.WEEK_NUMBER_FOR_TOTAL, player);
        	out.println("	<td class=\"s23\">"+points+"</td>");
        }
        out.println("	<td class=\"s25\"></td>");
        for (Player player : players)
        {
        	int points = year.getTotalPoints(Constants.WEEK_NUMBER_FOR_TOTAL, player);
        	out.println("	<td class=\"s23\">"+points+"</td>");
        }
        out.println("	<td class=\"s14\"></td>");
        out.println("</tr>");
        
        
        for (int week = 1 ; week <= (17 + 4)  ; week++)
        {
       	
	        out.println("<tr dir=\"ltr\">");
	        out.println("	<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
	        out.println("	<td class=\""+UIHelper.getTitleClassByWeek(week)+"\"><a href=\"/week?week="+week+"\">"+UIHelper.getWeekTitle(week)+"</a></td>");
	        out.println("	<td class=\"s29\"></td>");
	        
	      //  int[] scores = TestHelper.getRandomWeekScores(numPlayers);
	        
	        for (Player player : players)
	        {
	        	
	        	int points = year.getWeeklyPoints(week, player);
	        	// weekly scores don't get colored
	        	out.println("	<td class=\"s30\">"+points+"</td>");
	        }
	        out.println("	<td class=\"s32\"></td>");
	        for (Player player : players)
	        {
	        	int points = year.getTotalPoints(week, player);
	        	
	        	String tdClass = UIHelper.getTDClassByPoints(points / UIHelper.getScoreMultiplierByWeek(week));
	        	
	        	out.println("	<td class=\""+tdClass+"\">"+(points /**UIHelper.getScoreMultiplierByWeek(week)*/)+"</td>");
	        }
	        
	        out.println("	<td dir=\"ltr\" class=\""+UIHelper.getScoreMultiplierColumnClassByWeek(week)
	        		+ "\">" 
	        		+ UIHelper.getScoreMultiplierColumnValueByWeek(week)
	        		+ "</td>");
	        
	        out.println("</tr>");
	        
        }

        

        
        
        out.println("<tr dir=\"ltr\">");
        out.println("	<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
        out.println("	<td dir=\"ltr\" class=\"s21\">Total</td>");
        out.println("	<td class=\"s22\"></td>");

        
        for (Player player : players)
        {
        	int points = year.getWeeklyPoints(Constants.WEEK_NUMBER_FOR_TOTAL, player);
        	out.println("	<td class=\"s23\">"+points+"</td>");
        }
        out.println("	<td class=\"s25\"></td>");
        for (Player player : players)
        {
        	int points = year.getTotalPoints(Constants.WEEK_NUMBER_FOR_TOTAL, player);
        	out.println("	<td class=\"s23\">"+points+"</td>");
        }
        
        
        
        out.println("	<td class=\"s83\"></td>");
        out.println("</tr>");
        
        out.println("</tbody>");
        out.println("</table>");
	}
	
	

	
}
