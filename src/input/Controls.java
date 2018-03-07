package input;

import java.awt.Color;

import ui.AutonomousRobotPanel2;
import ui.Robot2;
import ui.ScoreField;
import ui.TeleoperatedRobotPanel2;
import ui.UI2;

/**
 * This class is a 1 instance class. In order for everything to work, you must
 * define the ui the controls are to modify,
 * 
 * @author Grayson Spidle
 */
public class Controls {
	
	public static UI2 ui;
	
	public static void pollController(int panelNumber, JController controller) {
		controller.poll();
		if (ui.panels.get(panelNumber).getSelectedIndex() == 0) {
			useAutonomousControls(panelNumber, controller);
		} else if (ui.panels.get(panelNumber).getSelectedIndex() == 1) {
			useTeleoperatedControls(panelNumber, controller);
		} else {
			return;
		}
	}
	
	private static void useAutonomousControls(int panelNumber, JController controller) {
		if (panelNumber < 0 || panelNumber > 5) 
			return;
		AutonomousRobotPanel2 panel = ui.panels.get(panelNumber).autonomous;
		ScoreField sf = panel.scoreFields.get(panel.fieldIndex);
		
		if (controller.getDPad() == 0.25f) {
			sf.setBackground(panel.color);
			if (panel.fieldIndex - 1 < 0) {
				panel.fieldIndex = panel.scoreFields.size() - 1;
			} else {
				panel.fieldIndex--;
			}
			sf = panel.scoreFields.get(panel.fieldIndex);
			sf.setBackground(Color.black);
		}
		
		if (controller.getDPad() == 0.75f) {
			sf.setBackground(panel.color);
			if (panel.fieldIndex + 1 >= panel.scoreFields.size()) {
				panel.fieldIndex = 0;
			} else {
				panel.fieldIndex++;
			}
			sf = panel.scoreFields.get(panel.fieldIndex);
			sf.setBackground(Color.BLACK);
		}
		
		if (controller.isXPressed()) {
			try {
				Integer i = Integer.parseInt(sf.getSuccessfulText());
				if (controller.isLTHeld() || controller.isRTHeld()) {
					i--;
				} else if (i < 10) {
					i++;
				}
				sf.setSuccessfulText("" + i);
			} catch (NumberFormatException e) {
				Boolean b = Boolean.parseBoolean(sf.getSuccessfulText());
				b = !b;
				sf.setSuccessfulText("" + b);
			}
		}
		
		if (controller.isBPressed()) {
			try {
				Integer i = Integer.parseInt(sf.getFailedText());
				if (controller.isLTHeld() || controller.isRTHeld()) {
					i--;
				} else if (i < 10) {
					i++;
				}
				sf.setFailedText("" + i);
			} catch (NumberFormatException e) {
				Boolean b = Boolean.parseBoolean(sf.getFailedText());
				b = !b;
				sf.setFailedText("" + b);
			}
		}
		
		if (controller.isStartPressed()) {
			ui.panels.get(panelNumber).setSelectedIndex(1);
		}
	}
	
	private static void useTeleoperatedControls(int panelNumber, JController controller) {
		if (panelNumber < 0 || panelNumber > 5) 
			return;
		TeleoperatedRobotPanel2 panel = ui.panels.get(panelNumber).teleoperated;
		ScoreField sf = panel.scoreFields.get(panel.fieldIndex);

		if (controller.getDPad() == 0.25f) {
			sf.setBackground(panel.color);
			if (panel.fieldIndex - 1 < 0) {
				panel.fieldIndex = panel.scoreFields.size() - 1;
			} else {
				panel.fieldIndex--;
			}
			sf = panel.scoreFields.get(panel.fieldIndex);
			sf.setBackground(Color.black);
		}
		
		if (controller.getDPad() == 0.75f) {
			sf.setBackground(panel.color);
			if (panel.fieldIndex + 1 >= panel.scoreFields.size()) {
				panel.fieldIndex = 0;
			} else {
				panel.fieldIndex++;
			}
			sf = panel.scoreFields.get(panel.fieldIndex);
			sf.setBackground(Color.BLACK);
		}
		
		if (controller.isXPressed()) {
			try {
				Integer i = Integer.parseInt(sf.getSuccessfulText());
				if (controller.isLTHeld() || controller.isRTHeld()) {
					i--;
				} else if (i < 10) {
					i++;
				}
				sf.setSuccessfulText("" + i);
			} catch (NumberFormatException e) {
				Boolean b = Boolean.parseBoolean(sf.getSuccessfulText());
				b = !b;
				sf.setSuccessfulText("" + b);
			}
		}
		
		if (controller.isBPressed()) {
			try {
				Integer i = Integer.parseInt(sf.getFailedText());
				if (controller.isLTHeld() || controller.isRTHeld()) {
					i--;
				} else if (i < 10) {
					i++;
				}
				sf.setFailedText("" + i);
			} catch (NumberFormatException e) {
				Boolean b = Boolean.parseBoolean(sf.getFailedText());
				b = !b;
				sf.setFailedText("" + b);
			}
		}

		if (controller.isStartPressed()) {
			((Robot2) (ui.panels.get(panelNumber))).setSelectedIndex(0);
		}
	}
	
	
	public static void setUI(UI2 arg0) {
		ui = arg0;
	}
}
