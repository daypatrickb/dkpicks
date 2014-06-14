package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Game;
import model.GameTime;
import model.League;
import model.Pick;
import model.PickStatus;
import model.Player;
import model.User;
import model.Week;
import model.Year;
import service.UserService;

import common.UIHelper;


public class WeekServlet extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4831051302762201063L;


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		User user = UserService.getUser(req);

		if (user == null)
		{
			resp.sendRedirect("/login.html");		
        	return;
		}
		
		renderPage(req, resp, user);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
		Map<String,String[]> params = req.getParameterMap();
		
		for (Entry<String,String[]> e : params.entrySet())
		{
			System.out.println(e.getKey() + ":  " + Arrays.toString(e.getValue()));
			
			// TODO need to map these values to picks
		}
		
		
		doGet(req, resp);
	}
	
	
	private void renderPage(HttpServletRequest req, HttpServletResponse resp, User user) throws IOException
	{

		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>Weekly Picks</title>");
//		out.println("<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js\">");
//		out.println("<script type=\"text/javascript\">");
//		out.println("$( \"input[type='text']\" ).change(function() { alert('change'); });");
//		
//		out.println("</script>");
		out.println("<link rel=stylesheet href=\"static/weekly.css\" type=\"text/css\"></head><body>");
        
		UIHelper.renderHeader(out, user);

		
		out.println("Here are all of the picks you made");
		
		String weekParam = req.getParameter("week");
		
		if (weekParam != null)
		{
			out.println("in week " + UIHelper.getWeekTitle(Integer.parseInt(weekParam)));
		}
		
		out.println("<br/>");
		out.println("User " + user.getNickname());
	
		
        String yearParam = req.getParameter("year");
		
        int year;
		if (yearParam == null)
		{ 
			// use current year
			year = 2014;
			
		} else
		{
			out.println("<br/>Year " + yearParam);
			
			year = Integer.parseInt(yearParam);
			
		}
        

		League league = League.getTheLeague();
	
		Year yr = league.getYear(year);
    	
		if (yr == null)
		{
			out.println("<h1>not a valid year</h1>");
		} else
		{
			Week wk = yr.getWeek(Integer.parseInt(weekParam));
	    	
			if (wk == null)
			{
				out.println("<h1>not a valid week</h1>");
			}else
			{
				renderTable(out, league, yr, wk, user);
			}
		}

		out.println("<a href=\"/logout\">Logout</a>");
		
	}
	
	
	private static void renderTable(PrintWriter out, League league, Year yr, Week week, User user)
	{
		
		boolean weekLocked = (week.getWeekNumber() < 14); // TODO
		
		List<Player> players = league.getPlayers();
		
		final int numPlayers = players.size();
		
		out.println("<form method=\"post\" action=\"/week?week="+week.getWeekNumber()+"\" >");
		
		out.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td>");
		out.println("	<table dir=\"ltr\" class=\"tblGenFixed\"  border=\"0\"");
		out.println("		cellpadding=\"0\" cellspacing=\"0\">");
		out.println("		<tbody>");
		out.println("			<tr class=\"rShim\">");
		out.println("				<td class=\"rShim\" style=\"width: 0;\"></td>");
		out.println("				<td class=\"rShim\" style=\"width: 99px;\"></td>");
		out.println("				<td class=\"rShim\" style=\"width: 21px;\"></td>");
		
        for (int i = 0 ; i < numPlayers ; i++)
        {
        	int colSize = Math.max(75, players.get(i).getName().length() * 12);
        	
            out.println("	<td class=\"rShim\" style=\"width: "+colSize+"px;\"></td>");
        }
		

		out.println("				<td class=\"rShim\" style=\"width: 21px;\"></td>");
		out.println("				<td class=\"rShim\" style=\"width: 56px;\"></td>");
		out.println("				<td class=\"rShim\" style=\"width: 16px;\"></td>");
		out.println("			</tr>");
		
		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td dir=\"ltr\" class=\"s0\">"+UIHelper.getWeekTitle(week.getWeekNumber())+"</td>");
		out.println("				<td class=\"s1\"></td>");
		
		for (Player p : players)
        {
        	out.println("	<td dir=\"ltr\" class=\""+p.getHeaderColumnClass()+"\">"+p.getName()+"</td>");
        }
		
		out.println("				<td class=\"s1\"></td>");
		out.println("				<td dir=\"ltr\" class=\"s7\">Actual</td>");
		out.println("				<td class=\"s8\"></td>");
		out.println("			</tr>");
		
		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td class=\"s12\"></td>");
		out.println("				<td class=\"s13\"></td>");
		for (int i = 0 ; i < numPlayers ; i++)
        {
			out.println("				<td class=\"s14\"></td>");
        }
		out.println("				<td class=\"s13\"></td>");
		out.println("				<td class=\"s13\"></td>");
		out.println("				<td class=\"s16\"></td>");
		out.println("			</tr>");
		
		List<GameTime> gametimes = new ArrayList<GameTime>();
		
		for (Game game : week.getGames())
		{
		
			if (!gametimes.contains(game.getTime()))
			{
				gametimes.add(game.getTime());
			}
			
			String timeClass =  (game == null || game.getTime() == null? "" : game.getTime().getTimeClass() );
			
		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td dir=\"ltr\" class=\""+timeClass+"\">"+game.getGameString()+"</td>");
		out.println("				<td class=\"s13\"></td>");
		
		for (Player p : players)
		{
			Pick pick = week.getPick(game, p);
			
			String pStr = (pick == null) ? "" : pick.toString();
			
			String pickClass = (pick == null || pick.getStatus() == null) ? "" : pick.getStatus().getPickClass();				
			
			out.println("				<td dir=\"ltr\" class=\""+timeClass+" "+pickClass+"\">");
			
			boolean currentPlayer = p.equals(user.getPlayer()); 
			
			
			if (currentPlayer && !weekLocked)
			{
				out.println("<input name=\""+game.hashCode()+"\" value=\""+pStr+"\" class=\""+timeClass+"\" style=\"background: transparent;border: none;border-color: transparent;height: 16px;\"/>");
			} else
			{
				out.println(pStr);
			}
			
			
			out.println("</td>");
		}

		out.println("				<td class=\"s13\"></td>");
		out.println("				<td dir=\"ltr\" class=\""+timeClass+"\">"+game.getActualScoreString()+"</td>");
		out.println("				<td class=\"s16\"></td>");
		out.println("			</tr>");
		
		}
		

		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td class=\"s57\"></td>");
		out.println("				<td class=\"s37\"></td>");
		for (Player p : players)
		{
			out.println("				<td class=\"s58\">"+yr.getWeeklyPoints(week.getWeekNumber(), p)+"</td>");
		}
		out.println("				<td class=\"s37\"></td>");
		out.println("				<td class=\"s59\"></td>");
		out.println("				<td class=\"s16\"></td>");
		out.println("			</tr>");
		out.println("		</tbody>");
		out.println("	</table></td><td>");
		
				
		
		out.println("	<table dir=\"ltr\" class=\"tblGenFixed\" border=\"0\"");
		out.println("		cellpadding=\"0\" cellspacing=\"0\">");
		out.println("		<tbody>");
		out.println("			<tr class=\"rShim\">");
		out.println("				<td class=\"rShim\" style=\"width: 0;\"></td>");
		out.println("				<td class=\"rShim\" style=\"width: 120px;\"></td>");
		out.println("				<td class=\"rShim\" style=\"width: 43px;\"></td>");
		out.println("			</tr>");
		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td class=\"s13\"></td>");
		out.println("				<td class=\"s13\"></td>");
		out.println("			</tr>");
		
		int addedRows = 0;
		
		if (week.getWeekNumber() <= 17)
		{
			// don't show the times for the playoffs
			// now we need 1 row for each of the different times used
			
			for (GameTime g : gametimes)
			{
				out.println("			<tr dir=\"ltr\">");
				out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
				out.println("				<td dir=\"ltr\" class=\""+g.getTimeClass()+"\">"+g.getDisplayTime()+"</td>");
				out.println("				<td class=\"s13\"></td>");
				out.println("			</tr>");
				
				addedRows++;
				
			}
			
			
			
			// then 1 spacer
			out.println("			<tr dir=\"ltr\">");
			out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
			out.println("				<td class=\"s13\"></td>");
			out.println("				<td class=\"s13\"></td>");
			out.println("				<td></td>");
			out.println("			</tr>");
			addedRows++;
		}
		// then the 3 scores
		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td dir=\"ltr\" class=\""+PickStatus.CORRECT.getPickClass()+"\">1 Point</td>");
		out.println("				<td class=\"s13\"></td>");
		out.println("			</tr>");
		
		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td dir=\"ltr\" class=\""+PickStatus.CLOSEST.getPickClass()+"\">2 Points</td>");
		out.println("				<td class=\"s13\"></td>");
		out.println("			</tr>");
		
		out.println("			<tr dir=\"ltr\">");
		out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
		out.println("				<td dir=\"ltr\" class=\""+PickStatus.EXACT.getPickClass()+"\">3 Points</td>");
		out.println("				<td class=\"s13\"></td>");
		out.println("			</tr>");
		
		for (int i = 0 ; i < (week.getGames().size() + 3) - (addedRows + 1 + 3); i++)
		{
			out.println("			<tr dir=\"ltr\">");
			out.println("				<td class=\"hd\"><p style=\"height: 16px;\">.</p></td>");
			out.println("				<td class=\"s13\"></td>");
			out.println("				<td class=\"s13\"></td>");
			out.println("			</tr>");
		}

		out.println("		</tbody>");
		out.println("	</table>  </td></tr></table>");
		
		
		if (!weekLocked)
		{
			out.println("<br /><br/>");
			// TODO only show the buttons if there was a change
			out.println("<input type=\"submit\" /><input type=\"reset\" />");
		}
		
		out.println("</form>");

		
		
	}
	
	
}
