package input;

import net.java.games.input.Component;

/**
 * This class manages the values of an {@link net.java.games.input.Component.Identifier.Axis Axis} @link net.java.games.input.Component
 * Component}. To be honest with you, the only difference between this and {@link com.frcteam1939.controller.Axis Axis} is the name of the class and a method name.
 * You can use both of these interchangeably if you wish. This is to provide more clarity to your code.
 * @author Grayson Spidle
 *
 */
public class Trigger implements Pollable {
	
	private Component raw;
	private float previous = 0f;
	private boolean isPressed = false;
	private boolean isHeld = false;
	private float maxValue = 0f;
	
	public Trigger(Component raw, float maxValue) {
		this.raw = raw;
		this.maxValue = maxValue;
	}

	@Override
	public void poll() {
		float current = raw.getPollData();
		isPressed = current != previous && current == 0;
		isHeld = current == previous && current <= maxValue && current > 0;
		previous = current;
	}
	
	public boolean isPressed() {
		return isPressed;
	}

	public boolean isHeld() {
		return isHeld;
	}
	
}
