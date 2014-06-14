// Copyright 2008 Google Inc. All Rights Reserved.
package model;



public class Pick {
	
	private Team winner;

	private int diff;

	private PickStatus status;
	
	public Team getWinner() {
		return winner;
	}

	public void setWinner(Team winner) {
		this.winner = winner;
	}


	public int getDiff() {
		return diff;
	}

	public void setDiff(int diff) {
		this.diff = diff;
	}

	public PickStatus getStatus() {
		return status;
	}

	public void setStatus(PickStatus status) {
		this.status = status;
	}
	
	public String toString()
	{
		return winner + " " + diff;
	}
	
	
	
}
