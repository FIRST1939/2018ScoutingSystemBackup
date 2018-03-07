package input;

import java.util.List;
import java.util.Vector;
/**
 * This class handles the inputs for a controller of {@link net.java.games.input.Controller.Type Controller.Type} Gamepad.
 * @author Grayson Spidle
 */
public class GamepadController extends JController {

	protected Button a = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._0));
	protected Button b = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._1));
	protected Button x = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._2));
	protected Button y = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._3));
	protected Button lb = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._4));
	protected Button rb = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._5));
	protected Button back = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._6));
	protected Button start = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._7));
	protected Button ls = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._8));
	protected Button rs = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._9));
	
	protected Trigger lt = new Trigger(raw.getComponent(net.java.games.input.Component.Identifier.Axis.Z), -1);
	protected Trigger rt = new Trigger(raw.getComponent(net.java.games.input.Component.Identifier.Axis.Z), 1);
	
	protected ThumbStick leftStick = new ThumbStick(new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.X)), new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.Y)), false);
	protected ThumbStick rightStick = new ThumbStick(new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.RX)), new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.RY)), false);
	
	protected HatSwitch dPad = new HatSwitch(raw.getComponent(net.java.games.input.Component.Identifier.Axis.POV));
	
	private List<Pollable> pollComponents = new Vector<Pollable>();
	
	/**
	 * The constructor.
	 * @param arg0 The controller from which the inputs will be parsed.
	 * @param controlsWhichRobotPanel The designated robot panel on the UI this controller will control.
	 */
	public GamepadController(net.java.games.input.Controller arg0) {
		super(arg0);
		pollComponents.add(a);
		pollComponents.add(b);
		pollComponents.add(x);
		pollComponents.add(y);
		pollComponents.add(lb);
		pollComponents.add(rb);
		pollComponents.add(start);
		pollComponents.add(back);
		pollComponents.add(ls);
		pollComponents.add(rs);
		pollComponents.add(lt);
		pollComponents.add(rt);
		pollComponents.add(leftStick);
		pollComponents.add(rightStick);
		pollComponents.add(dPad);
	}
	
	/**
	 * Call this method to get controller input (does not contain a loop)
	 */
	@Override
	public void poll() {
		if (!raw.poll()) {
			return;
		}
		
		for (Pollable p : pollComponents) {
			p.poll();
		}
	}
	
	@Override
	public boolean isAPressed() {
		return a.isPressed();
	}
	
	@Override
	public boolean isBPressed() {
		return b.isPressed();
	}
	
	@Override
	public boolean isXPressed() {
		return x.isPressed();
	}
	
	@Override
	public boolean isYPressed() {
		return y.isPressed();
	}
	
	@Override
	public boolean isLBPressed() {
		return lb.isPressed();
	}
	
	@Override
	public boolean isRBPressed() {
		return rb.isPressed();
	}
	
	@Override
	public boolean isLSPressed() {
		return ls.isPressed();
	}
	
	@Override
	public boolean isRSPressed() {
		return rs.isPressed();
	}
	
	@Override
	public boolean isStartPressed() {
		return start.isPressed();
	}
	
	@Override
	public boolean isLTPressed() {
		return lt.isPressed();
	}
	
	@Override
	public boolean isRTPressed() {
		return rt.isPressed();
	}
	
	@Override
	public boolean isLTHeld() {
		return lt.isHeld();
	}
	
	@Override
	public boolean isRTHeld() {
		return rt.isHeld();
	}
	
	@Override
	public float getLeftStick() {
		return leftStick.getPollData();
	}

	@Override
	public float getRightStick() {
		return rightStick.getPollData();
	}
	
	@Override
	public float getDPad() {
		return dPad.getPollData();
	}
	
	@Override
	public boolean isBackPressed() {
		return back.isPressed();
	}

	@Override
	public boolean isAHeld() {
		return a.isHeld();
	}

	@Override
	public boolean isBHeld() {
		return b.isHeld();
	}

	@Override
	public boolean isXHeld() {
		return x.isHeld();
	}

	@Override
	public boolean isYHeld() {
		return y.isHeld();
	}

	@Override
	public boolean isLBHeld() {
		return lb.isHeld();
	}

	@Override
	public boolean isRBHeld() {
		return rb.isHeld();
	}

	@Override
	public boolean isLSHeld() {
		return ls.isHeld();
	}

	@Override
	public boolean isRSHeld() {
		return rs.isHeld();
	}

	@Override
	public boolean isStartHeld() {
		return start.isHeld();
	}

	@Override
	public boolean isBackHeld() {
		return back.isHeld();
	}
	
}
	
