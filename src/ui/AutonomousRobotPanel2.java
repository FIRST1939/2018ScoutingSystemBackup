package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

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
 * This class governs the autonomous graphical user interface and data.
 * @author Grayson Spidle
 *
 */
public class AutonomousRobotPanel2 extends JPanel {
	
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
	
	public ScoreField scaleField;
	public ScoreField switchField;
	public ScoreField autoRunField;

	public RobotNumber name;
	
	public AtomicInteger atomFieldIndex = new AtomicInteger(2);
	public int fieldIndex = 0;
	
	public List<ScoreField> scoreFields = new Vector<ScoreField>();
	
	public Color color = Color.white;
	
	/**
	 * The constructor.
	 * @param robotNumber The team number.
	 * @param teamColor The team color.
	 */
	public AutonomousRobotPanel2(int robotNumber, Color teamColor) {
		super();
		this.color = teamColor;
		this.setBorder(new LineBorder(Color.BLACK));
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
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		name = new RobotNumber(robotNumber);
		name.setForeground(Color.WHITE);
		name.setSize(this.getWidth(), 14);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(nameFont);
		this.add(name, "2, 2, center, center");
		
		scaleField = new ScoreField("Scale: ", "0", "0");
		scaleField.setBackground(teamColor);
		scoreFields.add(scaleField);
		this.add(scaleField, "2, 3, fill, fill");
		
		switchField = new ScoreField("Switch: ", "0", "0");
		switchField.setBackground(teamColor);
		scoreFields.add(switchField);
		this.add(switchField, "2, 4, fill, fill");
		
		autoRunField = new ScoreField("Auto-Run: ", "0", "0");
		autoRunField.setBackground(teamColor);
		scoreFields.add(autoRunField);
		this.add(autoRunField, "2, 5, fill, fill");
		
		for (ScoreField sf : scoreFields) {
			sf.setEditable(false);
			sf.setBackground(color);
			sf.setOpaque(true);
			sf.getLabel().setForeground(Color.WHITE);
			sf.getLabel().setFont(scoreLabelFont);
			sf.getSuccessful().setForeground(Color.WHITE);
			sf.getSuccessful().setFont(scoreFieldFont);
			sf.getSuccessful().addFocusListener(EDITABILITY_TOGGLER);
			sf.getFailed().setForeground(Color.WHITE);
			sf.getFailed().setFont(scoreFieldFont);
			sf.getFailed().addFocusListener(EDITABILITY_TOGGLER);
		}
		
		scoreFields.get(fieldIndex).setBackground(Color.BLACK);
		
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
