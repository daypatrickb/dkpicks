package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import model.Game;
import model.GameTime;
import model.League;
import model.Pick;
import model.Player;
import model.Team;
import model.User;
import model.Week;
import model.Year;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

public class TestHelper 
{

	private static List<User> users;
	
	
	public static List<User> getTestUsers()
	{
		if (users == null)
		{
			
			
			Session session = HibernateUtil.getSession();
				
			
			session.beginTransaction();
	    	users = session.createQuery( "from User" ).list();

	    	session.getTransaction().rollback();
	    	session.close();
			
//			users = new ArrayList<User>();
//			
//			User u = new User();
//			u.setEmail("test@test.test");
//			u.setNickname("Dave");
//			u.setGoogleID("113587464232141390323");
//			users.add(u);
//			
//			u = new User();
//			u.setEmail("dehall622@gmail.com");
//			u.setNickname("Dylan");
//			u.setGoogleID("118273317212870330010");
//			users.add(u);
//			
//			u = new User();
//			u.setEmail("test@test.test");
//			u.setNickname("Jeff");
//			u.setGoogleID("103851251872932007865");
//			users.add(u);
//			
//			
//			u = new User();
//			u.setEmail("test@test.test");
//			u.setNickname("Pat");
//			u.setGoogleID("104760251617423522347");
//			users.add(u);
//			
//			u = new User();
//			u.setEmail("test@test.test");
//			u.setNickname("Dennis");
//			u.setGoogleID("109064731349904213414");
//			users.add(u);
//			
//			
//			u = new User();
//			u.setEmail("test@test.test");
//			u.setNickname("Justin");
//			u.setGoogleID("105630898613663508672");
//			users.add(u);
			
			
		}
		
		
		return users;
	}
	
	
	private static void setUserForPlayer(Player p)
	{
		List<User> users = getTestUsers();
		
		for (User u : users)
		{
			if (u.getNickname().equals(p.getName()))
			{
				p.setUser(u);
				u.setPlayer(p);
				break;
			}
		}
		
	}
	
	
	public static League getTestLeague()
	{
		League league = League.getTheLeague();
				
		
		Session session = HibernateUtil.getSession();
		
		//List<Player> players = new ArrayList<Player>();
				
		
//		session.beginTransaction();
//    	List<Player> players = session.createQuery( "from Player" ).list();
//    	for ( Player player :  players ) {
//    	    System.out.println( player.getName()  );
//    	}
//    	session.getTransaction().rollback();
//    	session.close();
    	
    	List<Player> players = session.createCriteria(Player.class)
    		    .addOrder( Order.asc("name") )
//    		    .addOrder( Order.desc("age") )
    		    .list();

		
//		Player p = new Player();
//		p.setName("Dave");
//		p.setHeaderColumnClass("giants");
//		players.add(p);
//		setUserForPlayer(p);
//		
//
//		p = new Player();
//		p.setName("Dennis");
//		p.setHeaderColumnClass("bengals");
//		players.add(p);
//		setUserForPlayer(p);
//
//		
//		
//		
//		p = new Player();
//		p.setName("Dylan");
//		p.setHeaderColumnClass("pats-alt");
//		players.add(p);
//		setUserForPlayer(p);
//
//		
//
//		
//		
//		p = new Player();
//		p.setName("Jeff");
//		p.setHeaderColumnClass("patriots");
//		players.add(p);
//		setUserForPlayer(p);
//
//		
//		
//		
//		
//		p = new Player();
//		p.setName("Justin");
//		p.setHeaderColumnClass("vikings");
//		players.add(p);
//		setUserForPlayer(p);
//		
//		
//		
//		
//		p = new Player();
//		p.setName("Pat");
//		p.setHeaderColumnClass("patriots");
//		players.add(p);
//		setUserForPlayer(p);
//
//		
//		p = new Player();
//		p.setName("Mexisnow");
//		p.setHeaderColumnClass("mexisnow");
//		players.add(p);
		
		league.setPlayers(players);
		
		
		List<Year> years = new ArrayList<Year>();
		
		Year y = new Year();
		y.setYear(2014);
		years.add(y);
		
		Week[] weeks = new Week[21];
		
		Random r = new Random();
		
		for (int w = 1 ; w <= 21 ; w++)
		{
			Week week = new Week();
			week.setWeekNumber(w);
			
			weeks[w-1] = week;
			
			List<Game> games = new ArrayList<Game>(16);
			
			List<Team> teams = new LinkedList<Team>();
			
			for (int i = 0 ; i < Team.values().length ; i++)
			{
				teams.add(Team.values()[i]);
			}
			
			Collections.shuffle(teams);
			
			int numGames = 16;
			
			if (w == 18 || w == 19) numGames = 4;
			else if (w == 20 || w == 21) numGames = 2;
			//else if (w == 21) numGames = 2;
			
			for (int i = 0 ; i < numGames ; i++)
			{
				Game game = new Game();
				game.setWeek(w);
				game.setYear(2014);
				
				if (w == 13)
				{
					// thanksgiving!!!!
					if (i == 0) game.setTime(GameTime.THANKSGIVING1);
					else if (i == 1) game.setTime(GameTime.THANKSGIVING2);
					else if (i == 2) game.setTime(GameTime.THANKSGIVING3);
					else if (i <= 9) game.setTime(GameTime.EARLY_SUNDAY);
					else if (i <= 13) game.setTime(GameTime.LATE_SUNDAY);
					else if (i == 14) game.setTime(GameTime.SUNDAY_NIGHT);
					else if (i == 15) game.setTime(GameTime.MONDAY_NIGHT);
					
				} else if (w <= 17)
				{
				if (i == 0) game.setTime(GameTime.THURSDAY_SINGLE);
				else if (i <= 9) game.setTime(GameTime.EARLY_SUNDAY);
				else if (i <= 13) game.setTime(GameTime.LATE_SUNDAY);
				else if (i == 14) game.setTime(GameTime.SUNDAY_NIGHT);
				else if (i == 15) game.setTime(GameTime.MONDAY_NIGHT);
				} else if (w == 18 || w == 19)
				{
					if (i == 0) game.setTime(GameTime.PLAYOFF_SATURDAY1);
					else if (i == 1) game.setTime(GameTime.PLAYOFF_SATURDAY2);
					else if (i == 2) game.setTime(GameTime.PLAYOFF_SUNDAY1);
					else if (i == 3) game.setTime(GameTime.PLAYOFF_SUNDAY2);
				}
				else if (w == 20) 
				{
					if (i == 0) game.setTime(GameTime.PLAYOFF_SUNDAY1);
					else if (i == 1) game.setTime(GameTime.PLAYOFF_SUNDAY2);

				}
				else if (w == 21)
				{
					if (i == 0) game.setTime(GameTime.SB_FIRST_HALF);
					else if (i == 1) game.setTime(GameTime.SB_SECOND_HALF);
				}
				
				
				//game.setTime(GameTime.values()[r.nextInt(14)]);
				
				
				game.setHome(teams.remove(0));
				game.setAway(teams.remove(0));
				games.add(game);
				
				game.setHomeScore(r.nextInt(60));
				game.setAwayScore(r.nextInt(60));
				
				
				for (Player player : players)
				{
					Pick pick = new Pick();
					
					Team winner;
					
					if ( r.nextBoolean() )
					{
						winner = game.getAway();
					} else
					{
						winner = game.getHome();
					}
					
					pick.setWinner( winner );
					pick.setDiff(r.nextInt(43)+1);
					
					week.setPick(game, player, pick);
				}
				
			}
			
			Collections.sort(games, new Comparator<Game>(){

				@Override
				public int compare(Game o1, Game o2) {
					// TODO Auto-generated method stub
					return o1.getTime().compareTo(o2.getTime());
				}
				
			});
			
			week.setGames(games);
			
			
			//int[] scores = getRandomWeekScores(players.size());
			
//			for (int i = 0 ; i < players.size() ; i++)
//			{
//				Player player = players.get(i);
//				y.setWeeklyPoints(w, player, scores[2*i]);
//				y.setTotalPoints(w, player, scores[(2*i)+1]);
//			}			
		}
		
		y.setWeeks(weeks);
		y.recalculateAllPoints();
		
		league.setYears(years);
				
		return league;
	}
	
	
	public static int[] getRandomWeekScores(final int numPlayers)
	{
		Random r = new Random();
		int[] result = new int[numPlayers * 2];
		
		Set<Integer> scores = new TreeSet<Integer>();
		for (int i = 0 ; i < numPlayers ; i++)
		{
			int currScore = r.nextInt(25); // nobody ever scored more than 24 
			
			scores.add(currScore);
			
			result[2*i] = currScore;
		}
		
		
		List<Integer> sl = new ArrayList<Integer>(scores);
		
		Collections.reverse(sl);
		
		Integer[] sc = sl.toArray(new Integer[0]);
		
		for (int i = 0 ; i < numPlayers ; i++)
		{
			int currScore = result[2*i];
			
			int resultPoints = 0;
			if (currScore == sc[0])
			{
				resultPoints = 4;
			} else if (sc.length > 1 && currScore == sc[1])
			{
				resultPoints = 3;
			} else if (sc.length > 2 && currScore == sc[2])
			{
				resultPoints = 2;
			} else if (sc.length > 3 && currScore == sc[3])
			{
				resultPoints = 1;
			}
			
			result[(2*i)+1] = resultPoints; 
		}
		
		return result;
		
	}
	
	
}
