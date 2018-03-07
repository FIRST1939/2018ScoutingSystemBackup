package input;

import net.java.games.input.Component;

/**
 * This class manages the values of an {@link net.java.games.input.Component.Identifier.Axis Axis} {@link net.java.games.input.Component
 * Component}.
 * 
 * @author Grayson Spidle
 *
 */
public class Axis implements Pollable {

	private Component raw;
	private float previous = 0f;
	private boolean isFlicked = false;
	private boolean isHeld = false;

	public Axis(Component raw) {
		this.raw = raw;
	}

	@Override
	public void poll() {
		float current = raw.getPollData();
		isFlicked = current != previous && current == 0;
		isHeld = current == previous && current != 0;
		previous = current;
	}

	public boolean isFlicked() {
		return isFlicked;
	}

	public boolean isHeld() {
		return isHeld;
	}

	public float getPollData() {
		return raw.getPollData();
	}

	public boolean isRelative() {
		return raw.isRelative();
	}

	public Component getRaw() {
		return raw;
	}

}
