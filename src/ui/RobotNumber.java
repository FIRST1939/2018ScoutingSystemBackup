package ui;

import javax.swing.JLabel;

/**
 * This class is used to help other processes get the correct inputs. It is a subclass of {@link javax.swing.JLabel JLabel}  
 * Implement this class for robot numbers in future subclasses of {@link com.frcteam1939.ui.RobotPanel RobotPanel}.
 * @author Grayson Spidle
 *
 */
public final class RobotNumber extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public RobotNumber() {
		super("" + null);
		this.putClientProperty("JComponent.sizeVariant", "large");
	}
	
	public RobotNumber(int arg0) {
		super(String.valueOf(arg0));
		this.putClientProperty("JComponent.sizeVariant", "large");
	}
	
	@Override
	public void setText(String arg0) {
		String actual = "";
		for (char c : arg0.toCharArray()) {
			if (Character.isDigit(c))
				actual += c;
		}
		super.setText(actual);
	}
	
	public void set(int arg0) {
		super.setText(String.valueOf(arg0));
	}
	
	/**
	 * Convenience method for <code>Integer.parseInt(number.getText())</code>
	 * @return Returns an Integer representation of what is in the UI component.
	 * @throws Thrown when the setText method has been overridden to accept Strings that aren't Integers.
	 */
	public int get() {
		return Integer.parseInt(super.getText());
	}

}
