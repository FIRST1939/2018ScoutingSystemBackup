package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScoreField extends JPanel {

	public static void main(String[] args) {
		GridLayout grid = new GridLayout(0,1);
		JPanel panel = new JPanel();
		panel.setLayout(grid);
		ScoreField field = new ScoreField("Label: ", "0", "0");
		field.setBorder(BorderFactory.createBevelBorder(1));
		ScoreField field2 = new ScoreField("Label2: ", "0", "0");
		field2.setBorder(BorderFactory.createBevelBorder(1));
		
		panel.add(field);
		panel.add(field2);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		

	}

	private JLabel label = new JLabel();
	private JTextField successful = new JTextField();
	private JTextField failed = new JTextField();

	private String defaultSuccessfulValue = "";
	private String defaultFailedValue = "";
	
	private boolean hasAttempts = true;
	private boolean isFocused = false;

	public ScoreField(String label, String defaultSuccessfulValue, String defaultFailedValue) {
		this(label, defaultSuccessfulValue, defaultFailedValue, true);
	}

	public ScoreField(String label, String defaultSuccessfulValue, String defaultFailedValue, boolean hasAttempts) {
		this.label.setText(label);
		this.label.setOpaque(false);
		this.label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
				
		successful.setText(defaultSuccessfulValue);
		successful.setHorizontalAlignment(JTextField.CENTER);
		successful.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		successful.setOpaque(false);
		successful.setEditable(false);

		failed.setText(defaultFailedValue);
		failed.setHorizontalAlignment(JTextField.CENTER);
		failed.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
		failed.setOpaque(false);
		failed.setEditable(false);

		this.defaultSuccessfulValue = defaultSuccessfulValue;
		this.defaultFailedValue = defaultFailedValue;
		this.hasAttempts = hasAttempts;
		
		this.setLayout(new GridLayout(1, 3));
		this.add(this.label);
		this.add(successful);
		this.add(failed);
		
		if (!hasAttempts) {
			failed.setForeground(failed.getBackground());
		}
	}
	
	@Override
	public void setOpaque(boolean isOpaque) {
		if (Objects.isNull(label))
			label = new JLabel();
		
		if (Objects.isNull(successful))
			successful = new JTextField();
		
		if (Objects.isNull(failed))
			failed = new JTextField();
	
		this.label.setOpaque(isOpaque);
		this.successful.setOpaque(isOpaque);
		this.failed.setOpaque(isOpaque);
	}
	
	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		this.label.setBackground(bg);
		this.successful.setBackground(bg);
		this.failed.setBackground(bg);
	}
	
	private Font getLargestFont() {
		Font greatest = label.getFont();
		FontMetrics greatestFM = getFontMetrics(greatest);
		if (greatestFM.getHeight() < this.getFontMetrics(successful.getFont()).getHeight()) {
			greatest = successful.getFont();
			greatestFM = getFontMetrics(greatest);
		}
		
		if (greatestFM.getHeight() < this.getFontMetrics(failed.getFont()).getHeight()) {
			greatest = failed.getFont();
			greatestFM = getFontMetrics(greatest);
		}
		return greatest;
	}
	
	@Override
	public void setSize(Dimension arg0) {
		super.setSize(arg0);
		System.out.println("Set Size");
	}
	
	@Override
	public void setMaximumSize(Dimension arg0) {
		super.setMaximumSize(arg0);
		System.out.println("set max size");
	}
	
	@Override
	public void setMinimumSize(Dimension arg0) {
		super.setMinimumSize(arg0);
		System.out.println("Set min size");
	}
	
	@Override
	public void setPreferredSize(Dimension arg0) {
		super.setPreferredSize(arg0);
		System.out.println("Set pref size");
	}
	
	@Override
	public void setSize(int arg0, int arg1) {
		super.setSize(arg0, arg1);
		System.out.println("set size2");
		float step = 0.1f;
		Font font = getLargestFont();
		float f = font.getSize();
		for (f = font.getSize(); this.getFontMetrics(font.deriveFont(f)).getHeight() > this.getHeight(); f += step) {
			continue;
		}
		
		label.setFont(label.getFont().deriveFont(f - step));
		successful.setFont(successful.getFont().deriveFont(f - step));
		failed.setFont(failed.getFont().deriveFont(f - step));
	}
	
	public void setEditable(boolean isEditable) {
		successful.setEditable(isEditable);
		failed.setEditable(isEditable);
	}
	
	public boolean isEditable() {
		return successful.isEditable();
	}

	public boolean hasAttempts() {
		return hasAttempts;
	}

	public String getDefaultSuccessful() {
		return defaultSuccessfulValue;
	}

	public void setDefaultSuccessful(String defaultSuccessfulValue) {
		this.defaultSuccessfulValue = defaultSuccessfulValue;
	}

	public String getDefaultFailed() {
		return defaultFailedValue;
	}

	public void setDefaultFailed(String defaultFailedValue) {
		this.defaultFailedValue = defaultFailedValue;
	}

	public String getSuccessfulText() {
		return successful.getText();
	}

	public JTextField getSuccessful() {
		return successful;
	}

	public void setSuccessfulText(String successful) {
		this.successful.setText(successful);
	}

	public String getFailedText() {
		return failed.getText();
	}

	public void setFailedText(String failed) {
		this.failed.setText(failed);
	}

	public String getLabelText() {
		return label.getText();
	}

	public void setLabelText(String label) {
		this.label.setText(label);
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JTextField getFailed() {
		return failed;
	}

	public void setFailed(JTextField failed) {
		this.failed = failed;
	}

	public void setSuccessful(JTextField successful) {
		this.successful = successful;
	}
	
	public void toggleFocus() {
		isFocused = !isFocused;
		if (isFocused) {
			
		}
		
	}

	public void reset() {
		this.successful.setText(defaultSuccessfulValue);
		this.failed.setText(defaultFailedValue);
	}

}
