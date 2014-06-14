package common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.Game;
import model.Pick;
import model.PickStatus;
import model.Player;
import model.Team;

public class Scoring {

	
	public static Map<Player,Integer> scorePicks(Game game, Map<Player,Pick> picksByGame)
	{
	
		Map<Player, Integer> scores = new HashMap<Player,Integer>();
	
		Team winner = game.getWinner();

		
		int actualDiff = Math.abs(game.getAwayScore() - game.getHomeScore());
		
		int closestDiff = Integer.MAX_VALUE;
		
		boolean someonePickedExact = false;
		
		for (Entry<Player, Pick> e : picksByGame.entrySet())
		{
			Pick p = e.getValue();
			int selectedDiff = p.getDiff();
			Team selectedWinner = p.getWinner();
			
			if (selectedWinner == winner)
			{
				
				if (selectedDiff == actualDiff)
				{
					someonePickedExact = true;
					p.setStatus(PickStatus.EXACT);
					scores.put(e.getKey(), Constants.SCORE_EXACT);
				} else
				{
					closestDiff = Math.min(closestDiff, Math.abs(actualDiff - selectedDiff) );
					
					p.setStatus(PickStatus.CORRECT);
					scores.put(e.getKey(), Constants.SCORE_CORRECT);
				}
				
				
				
			} else
			{
				p.setStatus(PickStatus.INCORRECT);
			}
			
			
		}
		
		if (someonePickedExact)
		{
			// stop here
			return scores;
		}
		
		// now we need to look for closest
		
		for (Entry<Player, Pick> e : picksByGame.entrySet())
		{
			Pick p = e.getValue();
			int selectedDiff = p.getDiff();
			Team selectedWinner = p.getWinner();
			
			if (selectedWinner == winner && Math.abs(actualDiff - selectedDiff) == closestDiff)
			{
				p.setStatus(PickStatus.CLOSEST);
				scores.put(e.getKey(), Constants.SCORE_CLOSEST);
			}
		}

		return scores;
		
		
	}


//	TODO this needs to handle tiebreakers and multipliers
	public static Map<Player, Integer> getTotals(Map<Player, Integer> weekPointsMap) {
		Map<Player, Integer> scores = new HashMap<Player,Integer>();
		
		Collection<Integer> vals = weekPointsMap.values();
		
		
		List<Integer> sl = new ArrayList<Integer>(vals);
		Collections.sort(sl);
		Collections.reverse(sl);
		
		Integer[] sc = sl.toArray(new Integer[0]);
		
		for (Player p : weekPointsMap.keySet())
		{
			int currScore = weekPointsMap.get(p);
			
			int resultPoints = 0;
			if (currScore == sc[0])
			{
				resultPoints = 4;
			} else if (sc.length > 1 && currScore == sc[1])
			{
				resultPoints = 3;
			} else if (sc.length > 2 && currScore == sc[2])
			{
				resultPoints = 2;
			} else if (sc.length > 3 && currScore == sc[3])
			{
				resultPoints = 1;
			}
			
			scores.put(p, resultPoints); 
		}
		
		
		return scores;
	}
	
	
}
