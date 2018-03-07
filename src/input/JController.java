package input;

import net.java.games.input.Controller;
import net.java.games.input.Rumbler;

/**
 * Provides the framework to create other controllers.
 * @author Grayson Spidle
 *
 */
public abstract class JController implements Pollable {

	protected Controller raw;

	/**
	 * The constructor.
	 * @param arg0 The raw controller.
	 */
	public JController(Controller arg0) {
		this.raw = arg0;
	}
	
	/**
	 * Returns if a was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isAPressed();

	/**
	 * Returns if a was held.
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isAHeld();

	/**
	 * Returns if b was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isBPressed();

	/**
	 * Returns if b was held.
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isBHeld();

	/**
	 * Returns if x was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isXPressed();

	/**
	 * Returns if x was held.
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isXHeld();

	/**
	 * Returns if y was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isYPressed();

	/**
	 * Returns if y was held.
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isYHeld();

	/**
	 * Returns if lb was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLBPressed();

	/**
	 * Returns if lb was held.
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isLBHeld();

	/**
	 * Returns if the right button was pressed.
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRBPressed();

	/**
	 * Returns if the right button was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isRBHeld();

	/**
	 * Returns if the left stick was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLSPressed();

	/**
	 * Returns if left stick was held.
	 * 
	 * @return Returns true if the stick was held and returns false if it was not held.
	 */
	public abstract boolean isLSHeld();

	/**
	 * Returns if right stick was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRSPressed();

	/**
	 * Returns if right stick was held.
	 * 
	 * @return Returns true if the stick was held and returns false if it was not held.
	 */
	public abstract boolean isRSHeld();

	/**
	 * Returns if the left trigger was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isLTPressed();

	/**
	 * Returns if the left trigger is held down.
	 * 
	 * @return Returns true if the button is held down returns false if it is not held down.
	 */
	public abstract boolean isLTHeld();
	/**
	 * Returns if rt was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isRTPressed();

	/**
	 * Returns if rt is held down.
	 * 
	 * @return Returns true if the button is held and returns false if it was not held.
	 */
	public abstract boolean isRTHeld();

	/**
	 * Returns if start was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isStartPressed();

	/**
	 * Returns if start was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isStartHeld();

	/**
	 * Returns if back was pressed.
	 * 
	 * @return Returns true if the button was pressed and returns false if it was not pressed.
	 */
	public abstract boolean isBackPressed();

	/**
	 * Returns if back was held.
	 * 
	 * @return Returns true if the button was held and returns false if it was not held.
	 */
	public abstract boolean isBackHeld();

	/**
	 * Returns the left stick's direction.
	 * 
	 * @return Returns a float reflecting the sticks direction.
	 */
	public abstract float getLeftStick();

	/**
	 * Returns the right stick's direction.
	 * 
	 * @return Returns a float reflecting the sticks direction.
	 */
	public abstract float getRightStick();

	/**
	 * Returns the dpad's direction.
	 * 
	 * @return Returns a float reflecting the directional pad's direction.
	 */
	public abstract float getDPad();
	
	public void rumble(float intensity) {
		for (Rumbler r : raw.getRumblers()) {
			r.rumble(intensity);
		}
	}
	
	public Controller getRaw() {
		return raw;
	}
}
