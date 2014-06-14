package model;

public class Analytic {

	// player
	
	// team
	
	private int pickedToWin = 0;
	private int pickedWinCorrect = 0;
	
	
	private int pickedToLose = 0;
	private int pickedLoseCorrect = 0;
	
	
	
	public void pickedToWin(boolean correct)
	{
		pickedToWin++;
		if (correct) pickedWinCorrect++;
		
	}
	

	public void pickedToLose(boolean correct)
	{
		pickedToLose++;
		if (correct) pickedLoseCorrect++;
	}
	

	public String toString()
	{
		return "Picked to Win: " + pickedWinCorrect + "/" + pickedToWin + "(" +getWinCorrectPercent()+ ")<br/>Picked to Lose: " + pickedLoseCorrect + "/" + pickedToLose + "(" + getLoseCorrectPercent() + ")";
	}
	
	public double getWinCorrectPercent()
	{
		return (double)pickedWinCorrect / (double)pickedToWin;
	}
	
	
	public double getLoseCorrectPercent()
	{
		return (double)pickedLoseCorrect  / (double)pickedToLose;
	}
	
	
	public double getTotalCorrectPercent()
	{
		return ((double) pickedWinCorrect+pickedLoseCorrect )/((double) pickedToWin+pickedToLose );
	}
	
}
