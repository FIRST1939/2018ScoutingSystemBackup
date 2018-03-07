package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tba.v3.models.Match;
import ui.queue.Queuer;
import utils.Images;

/**
 * The user interface for the scouting system.
 * @author Grayson Spidle
 *
 */
public class UI2 extends JFrame {
	
	public static final String BASE_TITLE = "FIRST PowerUp - Scouting Program";
	
	private static final long serialVersionUID = 2892016551422008688L;
	
	public JMenuBar menuBar = new JMenuBar();
	
	public JMenu menu = new JMenu("Temp");
	
	public JMenuItem itemExport = new JMenuItem("Export as .csv");
	public JMenuItem itemNext = new JMenuItem("Next");
	public JMenuItem itemReset = new JMenuItem("Reset");
	public JMenuItem itemRecoverLastState = new JMenuItem("Recover Last State");
	public JMenuItem itemChangeSavePath = new JMenuItem("Change Save Path");
	
	public JLabel buttonQueuer = new JLabel(new ImageIcon(Images.SHOW_QUEUE));
	public JLabel buttonNext = new JLabel(new ImageIcon(Images.NEXT));
	
	public JTextField idField = new JTextField();

	public List<Robot2> panels = new Vector<Robot2>();
	
	public Queuer qer = new Queuer();
	
	public String matchKey = "";
	public String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop" + System.getProperty("file.separator");
	
	public UI2() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttonQueuer.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				qer.setVisible(true);
				qer.requestFocus();
			}
		});
		
		itemReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});
		
		itemChangeSavePath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String path = JOptionPane.showInputDialog("Change Save Path", filePath);
					path.replace("\\", "/");
//					if (path.charAt(path.length() - 1) != '/')
//						path += "/";
					filePath = path;
					setTitle(BASE_TITLE);
				} catch (NullPointerException e2) {
					System.err.println("Save path change was cancelled.");
				}
				
			}
		});
		
		buttonNext.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					writeData(new File(filePath + matchKey + ".txt"), new File(filePath + matchKey + ".txt"));
					reset();
					resetPanelNumbers();
					Match match = qer.poll();
					matchKey = match.key;
					setTitle(BASE_TITLE);
					StringTokenizer st = new StringTokenizer(match.toString(), ",");
					for (int i = 0; i < 6; i++) {
						panels.get(i).setTeamNumber(Integer.parseInt(st.nextToken()));
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					if (qer.queue.isEmpty()) {
						System.err.println("Queue is empty.");
					} else {
						System.err.println("Not enough panels have been created.");
					}
					return;
				} catch (NoSuchElementException e2) {
					System.err.println("Not enough team numbers in the queue.");
					return;
				} catch (NullPointerException e2) {
					System.err.println("Queue is empty.");
					return;
				}
			}
		});
		
		menu.add(itemChangeSavePath);
		menu.add(itemReset);
		
		menuBar.add(menu);
		menuBar.add(buttonQueuer);
		menuBar.add(buttonNext);
		this.setJMenuBar(menuBar);
		
		// Initializing/Adding all the RobotTabbedPanels
		for (int i = 1; i < 7; i++) {
			if (i <= 3) {
				Robot2 pane = new Robot2(new AutonomousRobotPanel2(0 - i, Color.BLUE),
						new TeleoperatedRobotPanel2(0 - i, Color.BLUE));
				pane.setTabPlacement(JTabbedPane.TOP);
				this.add(pane);
			} else {
				Robot2 pane = new Robot2(new AutonomousRobotPanel2(0 - i, Color.RED),
						new TeleoperatedRobotPanel2(0 - i, Color.RED));
				pane.setTabPlacement(JTabbedPane.BOTTOM);
				this.add(pane);
			}
		}
		
		this.setLayout(new GridLayout(2, 3));
		this.pack();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		this.setVisible(true);
		this.setTitle(BASE_TITLE);
		this.setIconImage(Images.LOGO);
	}
	
	public void resetPanelNumbers() {
		for (int i = 0; i < 6; i++) {
			panels.get(i).setTeamNumber(0 - (i + 1));
		}
	}
	
	/**
	 * Returns an unmodifiable list of the panels
	 * @return
	 */
	public List<Robot2> getPanels() {
		return Collections.unmodifiableList(panels);
	}
	
	public void writeAutonomousData(File autoFile) throws IOException {
		if (!autoFile.exists()) {
			try {
				autoFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Failed to create file " + autoFile.toString());
				return;
			}
		}
		
		StringJoiner header = new StringJoiner("|");
		header.add("RobotNumber");
		StringJoiner body = new StringJoiner(System.lineSeparator());
		boolean isHeaderDone = false;
		
		for (Robot2 rp : panels) {
			StringJoiner data = new StringJoiner("|");
			data.add("" + rp.getTeamNumber());
			for (ScoreField sf : rp.autonomous.scoreFields) {
				if (!isHeaderDone) {
					String label = sf.getLabelText().replace(": ", "");
					header.add(label);
					if (sf.hasAttempts()) {
						header.add(label + "Failed");
					}
				}
				data.add(sf.getSuccessfulText());
				if (sf.hasAttempts()) {
					data.add(sf.getFailedText());
				}
			}
			body.add(data.toString());
			isHeaderDone = true;
		}
		
		Files.write(autoFile.toPath(), (header.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		Files.write(autoFile.toPath(), (body.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		System.out.println(header.toString());
		System.out.println(body.toString());
	}
	
	public void writeTeleoperatedData(File teleFile) throws IOException {
		if (!teleFile.exists()) {
			try {
				teleFile.mkdirs();
				teleFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Failed to create file " + teleFile.toString());
				return;
			}
		}
		
		StringJoiner header = new StringJoiner("|");
		header.add("RobotNumber");
		StringJoiner body = new StringJoiner(System.lineSeparator());
		boolean isHeaderDone = false;
		
		for (Robot2 rp : panels) {
			StringJoiner data = new StringJoiner("|");
			data.add("" + rp.getTeamNumber());
			for (ScoreField sf : rp.teleoperated.scoreFields) {
				if (!isHeaderDone) {
					String label = sf.getLabelText().replace(": ", "");
					header.add(label);
					if (sf.hasAttempts()) {
						header.add(label + "Failed");
					}
				}
				data.add(sf.getSuccessfulText());
				if (sf.hasAttempts()) {
					data.add(sf.getFailedText());
				}
			}
			body.add(data.toString());
			isHeaderDone = true;
		}
		
		Files.write(teleFile.toPath(), (header.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		Files.write(teleFile.toPath(), (body.toString() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
		System.out.println(header.toString());
		System.out.println(body.toString());
		
	}
	
	public void writeData(File autoFile, File teleFile) {
		new Thread() {
			@Override
			public void run() {
				synchronized (autoFile) {
					try {
						writeAutonomousData(autoFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				synchronized (teleFile) {
					try {
						writeTeleoperatedData(teleFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	@Override
	public void setTitle(String title) {
		super.setTitle(title + " | " + filePath + matchKey + ".csv");
	}
	
	public String getMatchKey() {
		return matchKey;
	}
	
	public void reset() {
		for (Robot2 p : panels) {
			for (ScoreField sf : p.autonomous.getFields()) {
				sf.reset();
			}
			for (ScoreField sf : p.teleoperated.getFields()) {
				sf.reset();
			}
		}
	}
}
