package input;

import java.util.Objects;

/**
 * This class manages the values of multiple {@link net.java.games.input.Component.Identifier.Axis Axes} to represent a thumb stick input.
 * @author Grayson Spidle
 *
 */
public class ThumbStick implements Pollable {
	
	private Axis x;
	private Axis y;
	private boolean isInverted = false;
	
	/**
	 * The constructor.
	 * @param horizontal The analog component that governs the horizontal axis.
	 * @param vertical The analog component that governs the vertical axis.
	 * @param isInverted Set this to true if you want down on the thumb stick to register as an up input. 
	 */
	public ThumbStick(final Axis horizontal, final Axis vertical, final boolean isInverted) {
		x = horizontal;
		y = vertical;
		this.isInverted = isInverted;
	}
	
	@Override
	public void poll() {
		x.poll();
		y.poll();
	}
	
	/**
	 * Gets the position where the thumb stick is pointing in a circle. 
	 * @return Returns a float representing the direction of the thumb stick in degrees.
	 */
	public float getPollData() {
		if (isInverted) {
			return (float) Math.toDegrees(Math.atan2(y.getPollData(), x.getPollData()));
		} else {
			return (float) Math.toDegrees(Math.atan2(y.getPollData() * -1, x.getPollData()));
		}
	}
	
	public boolean isFlicked() {
		return x.isFlicked() || y.isFlicked();
	}

	public boolean isHeld() {
		return x.isHeld() || y.isHeld();
	}
	
	public Axis getXAxis() {
		return x;
	}
	
	public Axis getYAxis() {
		return y;
	}
	
	public boolean isRelative() {
		return x.isRelative() && y.isRelative();
	}

	public boolean isInverted() {
		return isInverted;
	}
	
	public void setInverted(boolean isInverted) {
		this.isInverted = isInverted;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
}
