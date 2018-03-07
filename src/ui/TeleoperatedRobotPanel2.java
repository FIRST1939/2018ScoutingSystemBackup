package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * This class governs the teleoperated graphical user interface.
 * @author Grayson Spidle
 *
 */
public class TeleoperatedRobotPanel2 extends JPanel {
	
	private static final long serialVersionUID = -8832379680749996395L;
	
	private static final FocusListener EDITABILITY_TOGGLER = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent e) {
			if  (e.getSource().getClass().isAssignableFrom(JTextField.class)) {
				JTextField comp = (JTextField) e.getSource();
				comp.setEditable(false);
			}
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			if  (e.getSource().getClass().isAssignableFrom(JTextField.class)) {
				JTextField comp = (JTextField) e.getSource();
				comp.setEditable(true);
			}
		}
	}; 

	public Font nameFont = new Font("Tahoma", Font.BOLD, 25);
	public Font scoreLabelFont = new Font("Roboto", Font.BOLD, 25);
	public Font scoreFieldFont = new Font("Roboto", Font.PLAIN, 25);

	public RobotNumber name;
	
	public ScoreField scaleField;
	public ScoreField switchField;
	public ScoreField powerUpField;
	public ScoreField climbField;
	public ScoreField deadBotField;

	public int fieldIndex = 0;
	
	public Color color = Color.white;
	
	public List<ScoreField> scoreFields = new Vector<ScoreField>();
	
	/**
	 * The constructor.
	 * @param robotNumber The team number.
	 * @param teamColor The team color.
	 */
	public TeleoperatedRobotPanel2(int robotNumber, Color teamColor) {
		super();
		this.color = teamColor;
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBackground(teamColor);
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				RowSpec.decode("20px:grow"),
				RowSpec.decode("20px:grow"),
				RowSpec.decode("20px:grow"),
				RowSpec.decode("20px:grow"),
				RowSpec.decode("20px:grow"),
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		name = new RobotNumber(robotNumber);
		name.setForeground(Color.WHITE);
		name.setSize(this.getWidth(), 14);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(nameFont);
		this.add(name, "2, 2, center, fill");
		
		scaleField = new ScoreField("Scale: ", "0", "0");
		scaleField.setEditable(false);
		scoreFields.add(scaleField);
		this.add(scaleField, "2, 3, fill, fill");
		
		switchField = new ScoreField("Switch: ", "0", "0");
		switchField.setEditable(false);
		scoreFields.add(switchField);
		this.add(switchField, "2, 4, fill, fill");
		
		powerUpField = new ScoreField("Power-Up: ", "0", "0");
		powerUpField.setEditable(false);
		scoreFields.add(powerUpField);
		this.add(powerUpField, "2, 5, fill, fill");
		
		climbField = new ScoreField("Climb: ", "false", "false");
		climbField.setEditable(false);
		scoreFields.add(climbField);
		this.add(climbField, "2, 6, fill, default");
		
		deadBotField = new ScoreField("Dead-Bot: ", "false", "false", false);
		deadBotField.setEditable(false);
		scoreFields.add(deadBotField);
		this.add(deadBotField, "2, 7, fill, top");
		
		for (ScoreField sf : scoreFields) {
			sf.setEditable(false);
			sf.setOpaque(true);
			sf.setBackground(teamColor);
			sf.getLabel().setForeground(Color.WHITE);
			sf.getLabel().setFont(scoreLabelFont);
			sf.getSuccessful().setForeground(Color.WHITE);
			sf.getSuccessful().setFont(scoreFieldFont);
			sf.getSuccessful().addFocusListener(EDITABILITY_TOGGLER);
			sf.getFailed().setForeground(Color.WHITE);
			sf.getFailed().setFont(scoreFieldFont);
			sf.getFailed().addFocusListener(EDITABILITY_TOGGLER);
		}
		
		this.scoreFields.get(fieldIndex).setBackground(Color.BLACK);
		
	}
	
	public JLabel[] getLabels() {
		JLabel[] output = new JLabel[scoreFields.size()];
		for (int i = 0; i < output.length; i++) {
			output[i] = scoreFields.get(i).getLabel();
		}
		return output;
	}
	
	public ScoreField[] getFields() {
		return (ScoreField[]) scoreFields.toArray();
	}

	public RobotNumber getRobotNumber() {
		return name;
	}

	

}
