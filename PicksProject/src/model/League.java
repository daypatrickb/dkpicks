package model;

import java.util.ArrayList;
import java.util.List;

public class League {

	private List<Player> players;

	
	private List<Year> years;
	
	
	
	// League will be a singleton, there's only one league
	private League()
	{
		years = new ArrayList<Year>();
	}
	
	private static League INSTANCE;
	
	public static League getTheLeague()
	{
		if (INSTANCE == null) INSTANCE = new League();
		
		
		return INSTANCE;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Year> getYears() {
		return years;
	}

	public void setYears(List<Year> years) {
		this.years = years;
	}
	
	
	
	public Year getYear(int year)
	{
		for (Year y : years)
		{
			if (y.getYear() == year)
			{
				return y;
			}
		}
		
		return null;
	}
	
	
}
