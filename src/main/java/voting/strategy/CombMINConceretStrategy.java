package voting.strategy;

import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;


import java.util.HashMap;
import java.util.Map;


public class CombMINConceretStrategy implements AggregationStrategy{

	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String, Double> scores = new HashMap();
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			
			double parentScore = Double.MAX_VALUE;
			
			if (scores.containsKey(parentId))
			{
				parentScore = scores.get(parentId);
			}
			
			parentScore = Math.min(parentScore,voter.score);
			
			scores.put(parentId, parentScore);
		}
		
	  return scores;
	}


}
