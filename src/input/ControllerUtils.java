package input;

import java.util.List;
import java.util.Vector;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Controller.PortType;

public class ControllerUtils {
	
	/**
	 * Gets all controllers of the specified type.
	 * @param controllerType The specified {@link net.java.games.input.Controller.Type Controller.Type}.
	 * @return Returns a {@link java.util.List List} of {@link net.java.games.input.Controller Controllers} matching the given type.
	 */
	public static List<Controller> getControllersOfType(Controller.Type controllerType) {
		List<Controller> output = new Vector<Controller>();
		for (Controller c : getConnectedControllers()) {
			if (c.getType().equals(controllerType)) {
				output.add(c);
			}
		}
		return output;
	}
	
	public static List<Controller> getControllersWithPortType(PortType portType) {
		List<Controller> output = new Vector<Controller>();
		for (Controller c : getConnectedControllers()) {
			if (c.getPortType().equals(portType)) {
				output.add(c);
			}
		}
		return output;
	}
	
	public static List<Controller> getControllersInPortNumber(int portNumber) {
		List<Controller> output = new Vector<Controller>();
		for (Controller c : getConnectedControllers()) {
			if (c.getPortNumber() == portNumber) {
				output.add(c);
			}
		}
		return output;
	}

	/**
	 * Gets all controllers regardless of type. Including mice and other input devices.
	 * @return Returns a {@link java.util.List List} of {@link net.java.games.input.Controller Controllers}. 
	 */
	public static List<Controller> getConnectedControllers() {
		List<Controller> output = new Vector<Controller>();
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (Controller c : controllers) {
			output.add(c);
		}
		return output;
	}

}
