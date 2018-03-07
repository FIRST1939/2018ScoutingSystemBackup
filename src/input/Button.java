package input;

import net.java.games.input.Component;

/**
 * This class manages the values of a button on a controller. It updates its values through the {@link com.frcteam1939.controller.Pollable ControllerPollListener} interface. 
 * @author Grayson Spidle
 */
public class Button implements Pollable {
	
	private Component raw;
	
	private boolean current = false;
	private boolean previous = current;
	private boolean isPressed = false;
	private boolean isHeld = false;
	
	public Button(Component raw) throws IllegalArgumentException {
		if (raw.isAnalog()) throw new IllegalArgumentException("The component is analog driven.");
		this.raw = raw;
		current = false;
		previous = current;
		isPressed = false;
		isHeld = false;
	}
	
	@Override
	public void poll() {
		if (raw.getPollData() == 1f) {
			current = true;
		} else {
			current = false;
		}
		
		if (previous && !current) {
			isPressed = true;
			isHeld = false;
			previous = current;
		} else if (previous && current) {
			isPressed = false;
			isHeld = true;
			previous = current;
		} else {
			isPressed = false;
			isHeld = false;
			previous = current;
		}
	}
	
	protected boolean isPressed() {
		return isPressed;
	}
	
	protected boolean isHeld() {
		return isHeld;
	}
	
	public Component getRawComponent() {
		return raw;
	}
	
}
