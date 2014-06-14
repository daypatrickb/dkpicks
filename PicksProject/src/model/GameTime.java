package model;

public enum GameTime {

	// order these in the order we want them to show up on the weekly picks
	
	THURSDAY_SINGLE("Thursday Night" , "thurssingle"), 
	
	THANKSGIVING1("Thursday 12:30 pm","thanksgiving1"), 
	THANKSGIVING2("Thursday 4:30 pm","thanksgiving2"), 
	THANKSGIVING3("Thursday 8:30 pm","thanksgiving3"), 
	
	
	
	EARLY_SUNDAY("Sunday 1:00pm" ,"sunearly"), 
	LATE_SUNDAY("Sunday 4:00pm", "sunlate"), 
	SUNDAY_NIGHT("Sunday 8:00pm", "sunnight"),
	MONDAY_NIGHT("Monday Night","monnight"), 

	PLAYOFF_SATURDAY1("Saturday Game 1", "sunearly"), 
	PLAYOFF_SATURDAY2("Saturday Game 2", "sunlate"), 
	PLAYOFF_SUNDAY1("Sunday Game 1", "sunearly"), 
	PLAYOFF_SUNDAY2("Sunday Game 2", "sunlate"),
	
	SB_FIRST_HALF("First Half", "sunearly"),
	SB_SECOND_HALF("Second Half", "sunlate");
	
	private String displayTime;


	private String timeClass;
	
	private GameTime()
	{
		this.displayTime = name();
		this.timeClass = "";
	}
	
	private GameTime(String displayTime, String timeClass)
	{
		this.displayTime = displayTime;
		this.timeClass = timeClass;
	}
	

	public String getDisplayTime() {
		return displayTime;
	}

	public String getTimeClass() {
		return timeClass;
	}
	
	
	
}
