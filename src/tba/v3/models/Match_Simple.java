package tba.v3.models;

import java.util.StringJoiner;

import org.apache.commons.lang.ArrayUtils;

/**
 * A model for a Simple Match for a result of a Match request for version 3 of The Blue Alliance API. This is just a convenience class for the JSON parsing. 
 * @author Grayson Spidle
 *
 */
public class Match_Simple implements Comparable<Match_Simple> {
	
	public static final String[] MATCH_TYPES = new String[] {"qm", "qf", "sf", "f", "ef"};
	
	public String key = "";
	public String comp_level = "qm";
	public int set_number = -1;
	public int match_number = -1;
	public Match_Alliance[] alliances;
	public String winning_alliance = "";
	public String event_key = "";
	public int time = -1;
	public int predicted_time = -1;
	public int actual_time = -1;
	
	public Match_Simple() {
		alliances = new Match_Alliance[0];
	}
	
	public Match_Simple(String key, String comp_level, int set_number, int match_number, Match_Alliance[] alliances, String event_key) {
		this.key = key;
		this.comp_level = comp_level;
		this.set_number = set_number;
		this.match_number = match_number;
		this.alliances = alliances;
		this.event_key = event_key;
	}
	
	public Match_Simple(String key, String comp_level, int set_number, int match_number, Iterable<Match_Alliance> alliances, String event_key) {
		this.key = key;
		this.comp_level = comp_level;
		this.set_number = set_number;
		this.match_number = match_number;
		for (Match_Alliance a : alliances) {
			this.alliances = (Match_Alliance[]) ArrayUtils.add(this.alliances, a);
		}
		this.event_key = event_key;
	}
	
	public Match_Simple(String key, String comp_level, int set_number, int match_number, Match_Alliance[] alliances, String winning_alliance, String event_key, int time, int predicted_time, int actual_time) {
		this.key = key;
		this.comp_level = comp_level;
		this.set_number = set_number;
		this.match_number = match_number;
		this.alliances = alliances;
		this.event_key = event_key;
		this.time = time;
		this.predicted_time = predicted_time;
		this.actual_time = actual_time;
	}
	
	public Match_Simple(String key, String comp_level, int set_number, int match_number, Iterable<Match_Alliance> alliances, String winning_alliance, String event_key, int time, int predicted_time, int actual_time) {
		this.key = key;
		this.comp_level = comp_level;
		this.set_number = set_number;
		this.match_number = match_number;
		for (Match_Alliance a : alliances) {
			this.alliances = (Match_Alliance[]) ArrayUtils.add(this.alliances, a);
		}
		this.event_key = event_key;
		this.time = time;
		this.predicted_time = predicted_time;
		this.actual_time = actual_time;
	}
	
	/**
	 * Convenience method.
	 * @return Returns true if the field alliances is empty or an exception was thrown.
	 */
	public boolean isAlliancesEmpty() {
		try {
			return ArrayUtils.isEmpty(alliances);
		} catch (Exception e) {
			return true;
		}
	}
	
	/**
	 * Convenience method. 
	 * @return Returns the red alliance. If no red alliance is found, then null is returned.
	 */
	public Match_Alliance getRedAlliance() {
		for (Match_Alliance ma : alliances) {
			if (ma.alliance_name.equalsIgnoreCase("red")) {
				return ma;
			}
		}
		return null;
	}
	/**
	 * Convenience method. 
	 * @return Returns the blue alliance. If no blue alliance is found, then null is returned.
	 */
	public Match_Alliance getBlueAlliance() {
		for (Match_Alliance ma : alliances) {
			if (ma.alliance_name.equalsIgnoreCase("blue")) {
				return ma;
			}
		}
		return null;
	}
	
	@Override
	public int compareTo(Match_Simple o) { // TESTME
		int level = ArrayUtils.indexOf(MATCH_TYPES, comp_level) - ArrayUtils.indexOf(MATCH_TYPES, o.comp_level);
		if (level == 0) {
			int set = Integer.compare(set_number, o.set_number);
			if (set == 0) {
				return Integer.compare(match_number, o.match_number);
			} else {
				return set;
			}
		} else {
			return level;
		}
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",");
		for (String s : getRedAlliance().team_keys) {
			sj.add(s.replace("frc", ""));
		}
		
		for (String s : getBlueAlliance().team_keys) {
			sj.add(s.replace("frc", ""));
		}
		return sj.toString();
	}

}
