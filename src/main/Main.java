package main;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;

import input.ControllerUtils;
import input.Controls;
import input.GamepadController;
import input.JController;
import input.StickController;
import net.java.games.input.Controller;
import net.java.games.input.Rumbler;
import ui.AutonomousRobotPanel2;
import ui.UI2;

/**
 * This is the main class with the main method. 
 * @author Grayson Spidle
 *
 */
public class Main {

	public static final File recoveryFile = new File("recovery.txt");
	
	/**
	 * This dictates how many times the program will poll the controls per second. Similar to FPS for video games, but based around polling controls. 
	 * Minimum should be 10. 
	 */
	private static final int PPS_LOCK = 8;
	
	private static UI2 ui;
	private static Map<Integer, JController> controllers = new HashMap<Integer, JController>();
	private static List<JController> extraControllers = new Vector<JController>();
	
	private static boolean closeRequested = false;
	private static long last = System.currentTimeMillis();
	
	/**
	 * The main method for the entire scouting system. Run this method to start the program. 
	 * @param ui The UI for the program to show and modify.
	 */
	public static void main(String[] args) {
		ui = new UI2();
		
		Controls.setUI(ui);
		prepareControllers();
		while (!closeRequested) {
			for (Map.Entry<Integer, JController> e : controllers.entrySet()) {
				try {
					if (e.getKey() < 6) {
						Controls.pollController(e.getKey(), e.getValue());
						AutonomousRobotPanel2 p = ui.panels.get(e.getKey()).autonomous;
						p.scoreFields.get(p.fieldIndex).setOpaque(!p.scoreFields.get(p.fieldIndex).isOpaque());
					}
				} catch (NullPointerException e1) {
					if (!extraControllers.isEmpty()) {
						controllers.put(e.getKey(), extraControllers.get(0));
						extraControllers.remove(0);
						System.out.println("Controller connected.");
					} else {
						controllers.remove(e.getKey());
					}
					continue;
				}
			}
			long timeToSleep = (1000 / PPS_LOCK) - (System.currentTimeMillis() - last);
			if (timeToSleep > 0) {
				try {
					Thread.sleep(timeToSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			last = System.currentTimeMillis();
		}
	}
	
	/**
	 * Discovers connected controllers, then handles the resulting errors, then
	 * adds an ActionListener to every controller. It rumbles each controller it finds.
	 */
	private static void prepareControllers() {
		int i = 0;
		for (Controller c : ControllerUtils.getControllersOfType(Controller.Type.GAMEPAD)) {
			if (i < 6) {
				controllers.put(i, new GamepadController(c));
			} else {
				extraControllers.add(new GamepadController(c));
			}
			for (Rumbler r : c.getRumblers()) {
				r.rumble(0.5f);
			}
			i++;
		}

		for (Controller c : ControllerUtils.getControllersOfType(Controller.Type.STICK)) {
			if (i < 6) {
				controllers.put(i, new StickController(c));
			} else {
				extraControllers.add(new StickController(c));
			}
			for (Rumbler r : c.getRumblers()) {
				r.rumble(0.5f);
			}
			i++;
		}

		System.out.println(controllers.size() + "/6 controllers are connected.");
		
		if (i < 5) {
			System.err.println("Panels " + (i + 1) + "-6 are not being controlled.");
		} else if (i > 6) {
			System.err.println("Excess controllers are detected.");
		}
	}
	
	private static int[] indexesOf(String str, String delim) {
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
	
}
