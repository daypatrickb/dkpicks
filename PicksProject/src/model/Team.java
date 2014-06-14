package model;

public enum Team {

	ARI("ARI","Arizona Cardinals"),
	ATL("ATL","Atlanta Falcons"),
	BAL("BAL","Baltimore Ravens"),
	BUF("BUF","Buffalo Bills"),
	CAR("CAR","Carolina Panthers"),
	CHI("CHI","Chicago Bears"),
	CIN("CIN","Cincinnati Bengals"),
	CLE("CLE","Cleveland Browns"),
	DAL("DAL","Dallas Cowboys"),
	DEN("DEN","Denver Broncos"),
	DET("DET","Detroit Lions"),
	GB("GB","Green Bay Packers"),
	HOU("HOU","Houston Texans"),
	IND("IND","Indianapolis Colts"),
	JAX("JAX","Jacksonville Jaguars"),
	KC("KC","Kansas City Chiefs"),
	MIA("MIA","Miami Dolphins"),
	MIN("MIN","Minnesota Vikings"),
	NE("NE","New England Patriots"),
	NO("NO","New Orleans Saints"),
	NYG("NYG","New York Giants"),
	NYJ("NYJ","New York Jets"),
	OAK("OAK","Oakland Raiders"),
	PHI("PHI","Philadelphia Eagles"),
	PIT("PIT","Pittsburgh Steelers"),
	SD("SD","San Diego Chargers"),
	SEA("SEA","Seattle Seahawks"),
	SF("SF","San Francisco 49ers"),
	STL("STL","St. Louis Rams"),
	TB("TB","Tampa Bay Buccaneers"),
	TEN("TEN","Tennessee Titans"),
	WAS("WAS","Washington Redskins");

	
	
	private String name;
	private String abbr;
	
	
	private Team(String abbr, String name)
	{
		this.abbr = abbr;
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public String getAbbr() {
		return abbr;
	}
	
	
	
}
