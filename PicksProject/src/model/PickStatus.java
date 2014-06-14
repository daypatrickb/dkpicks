package model;

public enum PickStatus {

	EXACT("exact"), 
	CLOSEST("closest"), 
	CORRECT("correct"), 
	INCORRECT("incorrect"), 
	GAME_NOT_PLAYED("s24");
	

	
	private String pickClass;
	
	public String getPickClass() {
		return pickClass;
	}

	private PickStatus(String pickClass)
	{
		this.pickClass = pickClass;
	}
	
}
