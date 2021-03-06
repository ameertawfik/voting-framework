package voting.strategy;

import voting.AggregationStrategy;
import voting.Voter;
import voting.VoterProvider;

import java.util.HashMap;
import java.util.Map;

public class CombMNZConceretStrategy implements AggregationStrategy{

	public Map<String, Double> aggregate(VoterProvider provider) {
		Map<String, Double> scoresMap = new HashMap();
		Map<String,Integer> countsMap = new HashMap();
		
		while(provider.hasNext())
		{
			Voter voter = provider.nextVoter();
			String parentId = voter.aggregatorID;
			int parentCount = 0;
			double parentScore = 0.0;
			
			if (scoresMap.containsKey(parentId))
			{
				parentScore = scoresMap.get(parentId);
				parentCount = countsMap.get(parentId);
			}
			
			
			parentScore += voter.score;
			parentCount ++;
			scoresMap.put(parentId, parentScore);
			countsMap.put(parentId, parentCount);
		}
		
		for (String key : scoresMap.keySet()){
			scoresMap.put(key, scoresMap.get(key) * countsMap.get(key));
		}
		
	  return scoresMap;
	}

}
