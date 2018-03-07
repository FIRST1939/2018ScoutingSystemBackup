package input;

import java.util.List;
import java.util.Vector;

import net.java.games.input.Controller;

/**
 * This class handles the inputs for controllers of
 * {@link net.java.games.input.Controller.Type.Stick Controller.Type.Stick}.
 * 
 * @author Grayson Spidle
 */
public class StickController extends JController {

	protected Button a = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._1));
	protected Button b = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._2));
	protected Button x = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._0));
	protected Button y = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._3));
	protected Button lb = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._4));
	protected Button rb = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._5));
	protected Button start = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._9));
	protected Button back = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._8));
	protected Button ls = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._10));
	protected Button rs = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._11));
	protected Button lt = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._6));
	protected Button rt = new Button(raw.getComponent(net.java.games.input.Component.Identifier.Button._7));
	
	protected ThumbStick leftStick = new ThumbStick(new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.X)), new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.Y)), false);
	protected ThumbStick rightStick = new ThumbStick(new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.RX)), new Axis(raw.getComponent(net.java.games.input.Component.Identifier.Axis.RY)), false);
	protected HatSwitch dPad = new HatSwitch(raw.getComponent(net.java.games.input.Component.Identifier.Axis.POV));
	
	private List<Pollable> pollComponents = new Vector<Pollable>();

	/**
	 * The constructor.
	 * 
	 * @param arg0
	 *            The controller from which the inputs will be parsed.
	 * @param controlsWhichRobotPanel
	 *            The designated robotPanel on the UI that this controller will
	 *            control.
	 */
	public StickController(Controller arg0) {
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
		
		for (Pollable cpl : pollComponents) {
			cpl.poll();
		}
	}

	@Override
	public boolean isAPressed() {
		return this.a.isPressed();
	}

	@Override
	public boolean isBPressed() {
		return this.b.isPressed();
	}

	@Override
	public boolean isXPressed() {
		return this.x.isPressed();
	}

	@Override
	public boolean isYPressed() {
		return this.y.isPressed();
	}

	@Override
	public boolean isLBPressed() {
		return this.lb.isPressed();
	}

	@Override
	public boolean isRBPressed() {
		return this.rb.isPressed();
	}

	@Override
	public boolean isLSPressed() {
		return this.ls.isPressed();
	}

	@Override
	public boolean isRSPressed() {
		return this.rs.isPressed();
	}

	@Override
	public boolean isLTPressed() {
		return this.lt.isPressed();
	}

	@Override
	public boolean isLTHeld() {
		return this.lt.isHeld();
	}

	@Override
	public boolean isRTPressed() {
		return this.rt.isPressed();
	}

	@Override
	public boolean isRTHeld() {
		return this.rt.isHeld();
	}

	@Override
	public boolean isStartPressed() {
		return this.start.isPressed();
	}

	@Override
	public boolean isBackPressed() {
		return this.back.isPressed();
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
	public boolean isAHeld() {
		return this.a.isHeld();
	}

	@Override
	public boolean isBHeld() {
		return this.b.isHeld();
	}

	@Override
	public boolean isXHeld() {
		return this.x.isHeld();
	}

	@Override
	public boolean isYHeld() {
		return this.y.isHeld();
	}

	@Override
	public boolean isLBHeld() {
		return this.lb.isHeld();
	}

	@Override
	public boolean isRBHeld() {
		return this.rb.isHeld();
	}

	@Override
	public boolean isLSHeld() {
		return this.ls.isHeld();
	}

	@Override
	public boolean isRSHeld() {
		return this.rs.isHeld();
	}

	@Override
	public boolean isStartHeld() {
		return this.start.isHeld();
	}

	@Override
	public boolean isBackHeld() {
		return this.back.isHeld();
	}
}
