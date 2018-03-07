package tba.v3.models;

import org.apache.commons.lang.ArrayUtils;

/**
 * A model for an Alliance that would appear in a Match request in the version 3 of The Blue Alliance API. This is just a convenience class for the JSON parsing. 
 * @author Grayson Spidle
 *
 */
public class Match_Alliance {
	
	public int score = -1;
	public String alliance_name = "";
	public String[] team_keys;
	public String[] surrogate_team_keys;
	
	public Match_Alliance() {
		team_keys = new String[0];
		surrogate_team_keys = new String[0];
	}
	
	public Match_Alliance(String alliance_name) {
		this.alliance_name = alliance_name;
		team_keys = new String[0];
		surrogate_team_keys = new String[0];
	}
	
	public Match_Alliance(int score, String[] team_keys, String[] surrogate_team_keys) {
		this.score = score;
		this.team_keys = team_keys;
		this.surrogate_team_keys = surrogate_team_keys;
	}
	
	public Match_Alliance(int score, Iterable<String> team_keys, Iterable<String> surrogate_team_keys) {
		this.score = score;
		this.team_keys = new String[0];
		this.surrogate_team_keys = new String[0];
		for (String s : team_keys) {
			this.team_keys = (String[]) ArrayUtils.add(this.team_keys, s);
		}
		for (String s : surrogate_team_keys) {
			this.surrogate_team_keys = (String[]) ArrayUtils.add(this.surrogate_team_keys, s);
		}
	}
	
	public Match_Alliance(int score, String alliance_name, String[] team_keys, String[] surrogate_team_keys) {
		this.score = score;
		this.alliance_name = alliance_name;
		this.team_keys = team_keys;
		this.surrogate_team_keys = surrogate_team_keys;
	}
	
	public Match_Alliance(int score, String alliance_name, Iterable<String> team_keys, Iterable<String> surrogate_team_keys) {
		this.score = score;
		this.alliance_name = alliance_name;
		this.team_keys = new String[0];
		this.surrogate_team_keys = new String[0];
		for (String s : team_keys) {
			this.team_keys = (String[]) ArrayUtils.add(this.team_keys, s);
		}
		for (String s : surrogate_team_keys) {
			this.surrogate_team_keys = (String[]) ArrayUtils.add(this.surrogate_team_keys, s);
		}
	}
	
	/**
	 * Convenience method that tells if the field team_keys is empty or not.
	 * @return Returns true if it is empty or an exception was thrown. Returns false if it is not empty.
	 */
	public boolean isTeamKeysEmpty() {
		try {
			return ArrayUtils.isEmpty(team_keys);
		} catch (Exception e) {
			return true;
		}
	}
	
	/**
	 * Convenience method that tells if the field surrogate_team_keys is empty or not.
	 * @return Returns true if it is empty or an exception was thrown. Returns false if it is not empty.
	 */
	public boolean isSurrogateTeamKeysEmpty() {
		try {
			return ArrayUtils.isEmpty(surrogate_team_keys);
		} catch (Exception e) {
			return true;
		}
	}
	
}
