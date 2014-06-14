package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.UIHelper;

import service.UserService;

import model.Analytic;
import model.Game;
import model.League;
import model.Pick;
import model.Player;
import model.Table;
import model.Team;
import model.User;
import model.Week;
import model.Year;

public class AnalyticsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9082293516154514319L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		User user = UserService.getUser(request);

		if (user == null)
		{
			response.sendRedirect("/login.html");		
        	return;
		}
		
		League league = League.getTheLeague();
		
		Year yr = league.getYear(2014);
		
		
		Table<Team, Player, Analytic> playerTeamValues = new Table<Team, Player, Analytic>();
		
		
		for (Week week : yr.getWeeks())
		{
			for (Game game : week.getGames())
			{
				Map<Player,Pick> picks = week.getAllPicksByGame(game);
				
				for (Entry<Player, Pick> pick : picks.entrySet())
				{
					
					Team winner = game.getWinner();
					Team loser = game.getLoser();
					
					Team picked = pick.getValue().getWinner();

					if (picked == winner)
					{
						Analytic win = playerTeamValues.get( winner, pick.getKey() );
						
						if (win == null)
						{
							win = new Analytic();
						}
						
						win.pickedToWin(true);
						
						playerTeamValues.put( winner, pick.getKey(), win);
						
						
						Analytic lose = playerTeamValues.get( loser, pick.getKey() );
						
						if (lose == null)
						{
							lose = new Analytic();
						}
						
						lose.pickedToLose(true);
						
						playerTeamValues.put( loser, pick.getKey(), lose);

					} else if (winner != null)
					{
						Analytic win = playerTeamValues.get( winner, pick.getKey() );
						
						if (win == null)
						{
							win = new Analytic();
						}
						
						win.pickedToLose(false);
						
						playerTeamValues.put( winner, pick.getKey(), win);
						
						Analytic lose = playerTeamValues.get( loser, pick.getKey() );
						
						if (lose == null)
						{
							lose = new Analytic();
						}
						
						lose.pickedToWin(false);
						
						playerTeamValues.put( loser, pick.getKey(), lose);
						
					}
				}		
			}
		}
		
		
		double bestWinPercent = -1;
		Team bestWinPercentTeam = null;
		Player bestWinPercentPlayer = null;
		
		
		double bestLossPercent = -1;
		Team bestLossPercentTeam = null;
		Player bestLossPercentPlayer = null;
		
		
		double bestOverallPercent = -1;
		Team bestOverallPercentTeam = null;
		Player bestOverallPercentPlayer = null;
		
		
		
		for (Team t : Team.values())
		{
			 Map<Player,Analytic> row = playerTeamValues.row(t);
			
			 
			 for ( Entry<Player,Analytic> e : row.entrySet())
			 {
				 Player p = e.getKey();
				 Analytic curr = e.getValue();
				 
				 if (curr.getWinCorrectPercent() > bestWinPercent)
				 {
					 bestWinPercent = curr.getWinCorrectPercent();
					 bestWinPercentTeam = t;
					 bestWinPercentPlayer = p;
				 }
				 
				 
				 if (curr.getLoseCorrectPercent() > bestLossPercent)
				 {
					 bestLossPercent = curr.getLoseCorrectPercent();
					 bestLossPercentTeam = t;
					 bestLossPercentPlayer = p;
				 }
				 
				 
				 if (curr.getTotalCorrectPercent() > bestOverallPercent)
				 {
					 bestOverallPercent = curr.getTotalCorrectPercent();
					 bestOverallPercentTeam = t;
					 bestOverallPercentPlayer = p;
				 }

				 
			 }
		
		}
		
		
		
		
		List<Player> players = league.getPlayers();
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD>");
		out.println("  <TITLE>A Servlet</TITLE>");
		out.println("  <link rel=\"stylesheet\" href=\"static/scoreboard.css\" type=\"text/css\" />");
		out.println("  </HEAD>");
		out.println("  <BODY>");

		UIHelper.renderHeader(out, user);
		
		if (bestWinPercentTeam != null)
		{
			out.println("Best % Picking a Team to Win: ");
			out.println(bestWinPercentPlayer.getName());
			out.println(" picking ");
			out.println(bestWinPercentTeam);
			out.println(" @@ ");
			out.println(bestWinPercent*100);
			out.println("%");
			out.println("<br/>");
			out.println(playerTeamValues.get(bestWinPercentTeam, bestWinPercentPlayer));
			
			out.println("<br/><br/>");
		}
		
		
		
		if (bestLossPercentTeam != null)
		{
			out.println("Best % Picking a Team to Lose: ");
			out.println(bestLossPercentPlayer.getName());
			out.println(" picking ");
			out.println(bestLossPercentTeam);
			out.println(" @@ ");
			out.println(bestLossPercent*100);
			out.println("%");
			out.println("<br/>");
			out.println(playerTeamValues.get(bestLossPercentTeam, bestLossPercentPlayer));
			out.println("<br/><br/>");
		}
		
		
		if (bestOverallPercentTeam != null)
		{
			out.println("Most Accurate Overall: ");
			out.println(bestOverallPercentPlayer.getName());
			out.println(" picking ");
			out.println(bestOverallPercentTeam);
			out.println(" @@ ");
			out.println(bestOverallPercent*100);
			out.println("%");
			out.println("<br/>");
			out.println(playerTeamValues.get(bestOverallPercentTeam, bestOverallPercentPlayer));
			out.println("<br/><br/>");
		}
		
		out.println("  <table border=\"1\" class=\"tblGenFixed\" >");
		
		out.println("  <tr>");
		out.println("<td style=\"width:144px \" >Value</td>");
		
		for (Player p : players)
		{
			out.println("<td style=\"width:72px\" class=\""+p.getHeaderColumnClass()+"\">");
			out.println(p.getName());
			out.println("</td>");
		}
		out.println("  </tr>");
		
		
		for (Team t : playerTeamValues.rowKeySet())
		{
			out.println("  <tr>");
			out.println("<td>");
			out.println(t.getName());
			out.println("</td>");
			
			for (Player p : players)
			{
				out.println("<td>");
				out.println(playerTeamValues.get(t, p));
				out.println("</td>");
			}
			out.println("  </tr>");
		}
		
		
		out.println("  </table>");
		
		
		
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
