package ui.queue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import tba.v3.TBAV3;
import tba.v3.models.Match;
import tba.v3.models.Match_Simple;
import utils.Images;

public class AddToQueueWindow extends JFrame { // TODO make this frame look better, make a catch clause that catches when the user just presses the submit button without anything in the id field
	
	private static final long serialVersionUID = -4670408286763278402L;
	public JTextField idField = new JTextField();
	public JTextField indexField = new JTextField();
	public JButton buttonSubmit = new JButton();
	public JCheckBox checkboxQm = new JCheckBox("qm");
	public JCheckBox checkboxQf = new JCheckBox("qf");
	public JCheckBox checkboxSf = new JCheckBox("sf");
	public JCheckBox checkboxF = new JCheckBox("f");
	public JCheckBox checkboxEf = new JCheckBox("ef");
	public JCheckBox checkboxManual = new JCheckBox("Manual Input");
	public JLabel labelIndex = new JLabel("Index");
	public Queuer parent;
	
	
	public AddToQueueWindow(Queuer parent) {
		this.parent = parent;
		buttonSubmit.setText("Submit");
		buttonSubmit.setBounds(12, 96, 470, 108);
		buttonSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
//				if (checkboxManual.isSelected()) {
//					parent.add(new Match_Simple("null", -1, 0, 0, 0, null, event_key, time, predicted_time, actual_time));
//					StringTokenizer st = new StringTokenizer(id, ",");
//					if (st.countTokens() >= 5) {
//						for (int i = 0; i < 5; i++) {
//						}
//					}
//				}
				int index = Integer.parseInt(indexField.getText());
				if (index < 0) index = parent.queue.size();
				if (id.contains("_")) {
					Match match = new Match();
					try {
						match = TBAV3.readMatch(TBAV3.createMatchJsonReader(id));
					} catch (IOException e01) {
						e01.printStackTrace();
					}
					if (!match.isAlliancesEmpty() && !match.alliances[0].isTeamKeysEmpty() && !match.alliances[1].isTeamKeysEmpty()) {
						boolean[] checkboxes = new boolean[] {checkboxQm.isSelected(), checkboxQf.isSelected(), checkboxSf.isSelected(), checkboxF.isSelected(), checkboxEf.isSelected()};
						if (checkboxes[ArrayUtils.indexOf(Match_Simple.MATCH_TYPES, match.comp_level)]) {
							parent.add(index, match);
							System.out.println("Added match " + id + " to queue.");
						}
					} else {
						System.err.println("Failed to add match " + id + " to queue.");
					}
				} else if (!id.contains("_")) {
					try {
						Match[] matches = TBAV3.readMatchArray(TBAV3.createEventMatchesJsonReader(id));
						boolean[] checkboxes = new boolean[] {checkboxQm.isSelected(), checkboxQf.isSelected(), checkboxSf.isSelected(), checkboxF.isSelected(), checkboxEf.isSelected()};
						for (Match m : matches) {
							if (checkboxes[ArrayUtils.indexOf(Match_Simple.MATCH_TYPES, m.comp_level)]) {
								parent.add(index, m);
							}
							index++;
						}
						System.out.println("Added " + matches.length + " matches to the queue.");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				} else {
					System.err.println("error"); // TODO make this more clear
				}
				AddToQueueWindow.this.dispose();
			}
			
			private int[] indexesOf(String str, String delim) {
				int[] output = new int[0];
				StringTokenizer st = new StringTokenizer(str, delim);
				int offset = 0;
				while (st.hasMoreTokens()) {
					String token = st.nextToken();
					output = ArrayUtils.add(output, token.length() + offset);
					offset += token.length() + delim.length();
				}
				return output;
			}
		});
		getContentPane().add(buttonSubmit);
		
		idField.setBounds(12, 16, 470, 37);
		getContentPane().add(idField);
		
		checkboxManual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox box = (JCheckBox) e.getSource();
				labelIndex.setEnabled(!box.isSelected());
				indexField.setEnabled(!box.isSelected());
				checkboxQm.setEnabled(!box.isSelected());
				checkboxQf.setEnabled(!box.isSelected());
				checkboxSf.setEnabled(!box.isSelected());
				checkboxF.setEnabled(!box.isSelected());
				checkboxEf.setEnabled(!box.isSelected());
				
			}
		});
		
		indexField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if ((!Character.isDigit(e.getKeyChar())) || (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V)) {
					indexField.setText(eliminateNonNumerics(indexField.getText()));
				}
			}
			
			private String eliminateNonNumerics(String arg0) {
				String output = "";
				for (char c : arg0.toCharArray()) {
					if (Character.isDigit(c)) {
						output += c; 
					}
				}
				return output;
			}
			
		});
		indexField.setText("" + parent.queue.size());
		indexField.setBounds(265, 65, 33, 22);
		indexField.setColumns(10);
		this.add(indexField);
		
		checkboxQm.setBounds(8, 62, 47, 25);
		checkboxQm.setSelected(true);
		getContentPane().add(checkboxQm);
		
		checkboxQf.setBounds(59, 62, 39, 25);
		checkboxQf.setSelected(true);
		getContentPane().add(checkboxQf);
		
		
		checkboxSf.setBounds(102, 62, 39, 25);
		checkboxSf.setSelected(true);
		getContentPane().add(checkboxSf);
		
		checkboxF.setBounds(145, 62, 33, 25);
		checkboxF.setSelected(true);
		getContentPane().add(checkboxF);
		
		checkboxEf.setBounds(182, 62, 39, 25);
		checkboxEf.setSelected(true);
		getContentPane().add(checkboxEf);
		
		getContentPane().setLayout(null);
		
		labelIndex.setBounds(229, 66, 33, 16);
		getContentPane().add(labelIndex);
		
		checkboxManual.setBounds(300, 66, 100, 16);
		getContentPane().add(checkboxManual);
		
		this.setSize(500, 250);
		this.setResizable(false);
		this.setTitle("Add to Queue");
		this.setIconImage(Images.ADD_TO_QUEUE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}
