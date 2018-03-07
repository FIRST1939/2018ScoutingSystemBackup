package tba.v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import tba.v3.models.Match;
import tba.v3.models.Match_Alliance;
import tba.v3.models.Match_Simple;

/**
 * The integration the TheBlueAlliance API Version 3
 * @author Grayson Spidle
 *
 */
public class TBAV3 {
	
	private static final String API_AUTH_KEY = "njbJBEkw39yzSNnh0g8SEEmhzvG8xqB8xuTCqsIYMDtT7hGxL7QNu8ZBEPJiOe3h"; // Don't push to Github with the key present in the field.
	private static final String EVENT_BASE = "https://www.thebluealliance.com/api/v3/event/";
	private static final String MATCH_BASE = "https://www.thebluealliance.com/api/v3/match/";
	private static final String SUFFIX = "?X-TBA-Auth-Key=" + API_AUTH_KEY;
	
	public static boolean debugMode = false;
	
	public static void main(String[] args) {
		JsonReader eventMatches = createEventMatchesJsonReader("2017mosl");
		Match[] arr = null;
		try {
			arr = readMatchArray(eventMatches);
			for (Match m : arr) {
				System.out.println(m.key);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static JsonReader createMatchJsonReader(String matchKey) {
		if (!matchKey.contains("_")) {
			System.err.println("Match keys must have an underscore \"_\".");
			return null;
		} else {
			try {
				URL url = new URL(MATCH_BASE + matchKey + SUFFIX);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows 10; WOW64; rv:25.0) Gecko/20100101 Chrome/51.0.2704.103");
				connection.connect();
				return new JsonReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
			} catch (MalformedURLException e) {
				System.err.println("Could not create the url due to an error in forming the url." + MATCH_BASE + matchKey + SUFFIX);
				if (debugMode) e.printStackTrace();
			} catch (UnknownHostException e) {
				System.err.println("Cannot connect to www.thebluealliance.com. Check your internet connection.");
				if (debugMode) e.printStackTrace();
			} catch (IOException e) {
				System.err.println("An error occurred in connecting to www.thebluealliance.com.");
				if (debugMode) e.printStackTrace();
			}
			return null;
		}
	}
	
	public static JsonReader createEventMatchesJsonReader(String eventKey) {
		if (eventKey.contains("_")) {
			System.err.println("Event keys don't have an underscore \"_\". Truncating the key in an attempt to remedy the situation. Risks throwing a MalformedURLException.");
			eventKey = eventKey.substring(0, eventKey.indexOf('_'));
		}
		try {
			URL url = new URL(EVENT_BASE + eventKey + "/matches" + SUFFIX);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows 10; WOW64; rv:25.0) Gecko/20100101 Chrome/51.0.2704.103");
			connection.connect();
			return new JsonReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
		} catch (MalformedURLException e) {
			System.err.println("Could not create the url due to an error in forming the url." + EVENT_BASE + eventKey + "/matches" + SUFFIX);
			if (debugMode) e.printStackTrace();
		} catch (UnknownHostException e) {
			System.err.println("Cannot connect to www.thebluealliance.com. Check your internet connection.");
			if (debugMode) e.printStackTrace();
		} catch (IOException e) {
			System.err.println("An error occurred in connecting to www.thebluealliance.com.");
			if (debugMode) e.printStackTrace();
		}
		return new JsonReader(null);
	}
	
	public static Match_Simple readMatch_Simple(JsonReader reader) throws IOException {
		Match_Simple output = new Match_Simple();
		reader.beginObject();
		while (reader.hasNext()) {
			try {
				String name = reader.nextName();
				if (name.equalsIgnoreCase("key")) {
					output.key = reader.nextString();
				} else if (name.equalsIgnoreCase("comp_level")) {
					output.comp_level = reader.nextString();
				} else if (name.equalsIgnoreCase("set_number")) {
					output.set_number = reader.nextInt();
				} else if (name.equalsIgnoreCase("match_number")) {
					output.match_number = reader.nextInt();
				} else if (name.equalsIgnoreCase("alliances")) {
					output.alliances = readAlliances(reader);
				} else if (name.equalsIgnoreCase("winning_alliance_string")) {
					output.winning_alliance = reader.nextString();
				} else if (name.equalsIgnoreCase("event_key")) {
					output.event_key = reader.nextString();
				} else if (name.equalsIgnoreCase("time")) {
					output.time = reader.nextInt();
				} else if (name.equalsIgnoreCase("predicted_time")) {
					output.predicted_time = reader.nextInt();
				} else if (name.equalsIgnoreCase("actual_time")) {
					output.actual_time = reader.nextInt();
				} else {
					reader.skipValue();
				}
			} catch (IllegalStateException e) {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	public static Match readMatch(JsonReader reader) throws IOException {
		Match output = new Match();
		reader.beginObject();
		while (reader.hasNext()) {
			try {
				String name = reader.nextName();
				if (name.equalsIgnoreCase("key")) {
					output.key = reader.nextString();
				} else if (name.equalsIgnoreCase("comp_level")) {
					output.comp_level = reader.nextString();
				} else if (name.equalsIgnoreCase("set_number")) {
					output.set_number = reader.nextInt();
				} else if (name.equalsIgnoreCase("match_number")) {
					output.match_number = reader.nextInt();
				} else if (name.equalsIgnoreCase("alliances")) {
					output.alliances = readAlliances(reader);
				} else if (name.equalsIgnoreCase("winning_alliance_string")) {
					output.winning_alliance = reader.nextString();
				} else if (name.equalsIgnoreCase("event_key")) {
					output.event_key = reader.nextString();
				} else if (name.equalsIgnoreCase("time")) {
					if (reader.peek().equals(JsonToken.NULL)) output.time = -1;
					else output.time = reader.nextInt();
				} else if (name.equalsIgnoreCase("predicted_time")) {
					if (reader.peek().equals(JsonToken.NULL)) output.predicted_time = -1;
					else output.predicted_time = reader.nextInt();
				} else if (name.equalsIgnoreCase("actual_time")) {
					if (reader.peek().equals(JsonToken.NULL)) output.actual_time = -1;
					else output.actual_time = reader.nextInt();
				} else if (name.equalsIgnoreCase("score_breakdown")) {
					output.score_breakdown = readScoreBreakdown(reader);
				} else {
					reader.skipValue();
				}
			} catch (IllegalStateException e) {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	public static Match[] readMatchArray(JsonReader reader) throws IOException {
		Match[] output = new Match[0];
		reader.beginArray();
		while (reader.hasNext()) {
			if (!reader.peek().equals(JsonToken.END_ARRAY)) {
				output = (Match[]) ArrayUtils.add(output, readMatch(reader));
			} else {
				break;
			}
		}
		reader.endArray();
		Arrays.sort(output);
		return output;
	}
	
	private static Match_Alliance[] readAlliances(JsonReader reader) throws IOException {
		Match_Alliance[] output = new Match_Alliance[0];
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equalsIgnoreCase("blue") || name.equalsIgnoreCase("red")) {
				Match_Alliance alliance = readAlliance(reader);
				alliance.alliance_name = name;
				output = (Match_Alliance[]) ArrayUtils.add(output, alliance);
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	private static Match_Alliance readAlliance(JsonReader reader) throws IOException {
		Match_Alliance output = new Match_Alliance();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equalsIgnoreCase("score")) {
				output.score = reader.nextInt();
			} else if (name.equalsIgnoreCase("team_keys")) {
				output.team_keys = readStringArray(reader);
			} else if (name.equalsIgnoreCase("surrogate_team_keys")) {
				output.surrogate_team_keys = readStringArray(reader);
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	private static Map<String, Object> readScoreBreakdown(JsonReader reader) throws IOException {
		Map<String, Object> output = new HashMap<String, Object>();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (reader.peek().equals(JsonToken.BOOLEAN)) {
				output.put(name, reader.nextBoolean());
			} else if (reader.peek().equals(JsonToken.NUMBER)) {
				output.put(name, reader.nextLong());
			} else if (reader.peek().equals(JsonToken.STRING)) {
				output.put(name, reader.nextString());
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	private static String[] readStringArray(JsonReader reader) throws IOException {
		String[] output = new String[0];
		reader.beginArray();
		while (reader.hasNext()) {
			if (reader.peek().equals(JsonToken.STRING)) {
				output = (String[]) ArrayUtils.add(output, reader.nextString());
			} else {
				reader.skipValue();
			}
		}
		reader.endArray();
		return output;
	}
	
}
