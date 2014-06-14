package model;




public class Player {

	
	private int id;
	
	private String name;
	
	private int favoriteTeamID;


	private Team favoriteTeam;
	
	private String headerColumnClass;
	
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeaderColumnClass() {
		
		if (headerColumnClass == null && getFavoriteTeam() != null)
		{
			Team t = favoriteTeam;
			String[] names = t.getName().split(" ");
			
			headerColumnClass = names[names.length - 1].toLowerCase();
			
		}
		
		return headerColumnClass;
	}

	public void setHeaderColumnClass(String headerColumnClass) {
		this.headerColumnClass = headerColumnClass;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFavoriteTeamID() {
		return favoriteTeamID;
	}

	public void setFavoriteTeamID(int favoriteTeamID) {
		this.favoriteTeamID = favoriteTeamID;
		if (favoriteTeamID > 0)
		{
			this.favoriteTeam = Team.values()[favoriteTeamID];
		}
		
	}

	public Team getFavoriteTeam()
	{
		if (favoriteTeam == null && favoriteTeamID > 0)
		{
			this.favoriteTeam = Team.values()[favoriteTeamID];
		}
		
		return favoriteTeam;
	}

	public void setFavoriteTeam(Team favoriteTeam) {
		this.favoriteTeam = favoriteTeam;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Player))
		{
			return false;
		}
		
		Player other = (Player) obj;
		
		return this.id == other.id;
		
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}
	
}
