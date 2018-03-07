package input;

import net.java.games.input.Component;

/**
 * This class manages the values of a Hat Switch {@link net.java.games.input.Component
 * Component}.
 * @author Grayson Spidle
 *
 */
public class HatSwitch implements Pollable {
	
	public static final float NEUTRAL = 0f;
	public static final float UP = 0.25f;
	public static final float RIGHT = 0.5f;
	public static final float DOWN = 0.75f;
	public static final float LEFT = 1f;
	
	private Component raw;
	private float previous = NEUTRAL;
	private boolean isFlicked = false;
	private boolean isHeld = false;

	public HatSwitch(Component raw) throws IllegalArgumentException {
		this.raw = raw;
	}

	@Override
	public void poll() {
		float current = raw.getPollData();
		isFlicked = (current != previous) && (current == NEUTRAL);
		isHeld = (current == previous) && (current != NEUTRAL);
		previous = current;
	}
	
	public float getPollData() {
		return raw.getPollData();
	}
	
	public boolean isHeld() {
		return isHeld;
	}
	
	public boolean isFlicked() {
		return isFlicked;
	}
	
}
