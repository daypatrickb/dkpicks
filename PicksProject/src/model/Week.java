package model;

import java.util.List;
import java.util.Map;

public class Week {

	private int year;

	private int weekNumber;

	private boolean canScore = true;

	private List<Game> games;

	private Table<Game, Player, Pick> picks;

	public Week() 
	{
		picks = new Table<Game, Player, Pick>();
	}

	public int getYear() 
	{
		return year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}

	public int getWeekNumber() 
	{
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) 
	{
		this.weekNumber = weekNumber;
	}

	public List<Game> getGames() 
	{
		return games;
	}

	public void setGames(List<Game> games) 
	{
		this.games = games;
	}

	public Table<Game, Player, Pick> getPicks() 
	{
		return picks;
	}

	public void setPicks(Table<Game, Player, Pick> picks) 
	{
		this.picks = picks;
	}

	public Pick getPick(Game game, Player player) 
	{
		return picks.get(game, player);
	}

	public void setPick(Game game, Player player, Pick pick) 
	{
		picks.put(game, player, pick);
	}

	public boolean canScore() 
	{
		return canScore;
	}

	public void setCanScore(boolean canScore) 
	{
		this.canScore = canScore;
	}

	public Map<Player, Pick> getAllPicksByGame(Game game) 
	{
		return picks.row(game);
	}

}
