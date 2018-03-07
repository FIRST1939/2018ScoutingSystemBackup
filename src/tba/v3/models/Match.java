package tba.v3.models;

import java.util.HashMap;
import java.util.Map;

/**
 * A model for a Match of the version 3 of The Blue Alliance API. This is just a convenience class for the JSON parsing. 
 * @author Grayson Spidle
 *
 */
public class Match extends Match_Simple {
	
	public Map<String, Object> score_breakdown;
	
	public Match() {
		super();
		score_breakdown = new HashMap<String, Object>();
	}
	
	
	
}
