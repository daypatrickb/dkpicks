package model;

public class User {

	
	private int id;

	private String email;
	
	
	private String nickname;
	
	
	private Player player;

	
	private String googleID;

	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}




	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}


	public String getGoogleID() {
		return googleID;
	}


	public void setGoogleID(String googleID) {
		this.googleID = googleID;
	}


	public String toString()
	{
		return nickname;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
}
