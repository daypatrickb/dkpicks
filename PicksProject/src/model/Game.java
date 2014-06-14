package model;



public class Game {

	private Team home;
	
	
	private Team away;
	

	private int year;
	
	private int week;
	
	private int homeScore;
	
	private int awayScore;
	
	

	
	private GameTime time;

	

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getAway() {
		return away;
	}

	public void setAway(Team away) {
		this.away = away;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}






	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	
	
	public String getGameString()
	{
		return away + " @ " + home;
	}

	public String getActualScoreString() {
		if (homeScore == 0 && awayScore == 0)
		{
			return "";
		} else if (homeScore == awayScore)
		{
			return "TIE";
		} else if (homeScore > awayScore)
		{
			return home + " " + (homeScore - awayScore);
		} else
		{
			return away + " " + (awayScore - homeScore);
		}
	}

	
	public Team getWinner()
	{
		if (homeScore == awayScore)
		{
			return null;
		} else if (homeScore > awayScore)
		{
			return home;
		} else
		{
			return away;
		}
	}
	
	public Team getLoser()
	{
		if (homeScore == awayScore)
		{
			return null;
		} else if (homeScore > awayScore)
		{
			return away;
		} else
		{
			return home;
		}
	}
	
	
	public GameTime getTime() {
		return time;
	}

	public void setTime(GameTime time) {
		this.time = time;
	}



	
	
}
