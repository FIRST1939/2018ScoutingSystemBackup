package ui.queue;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

import tba.v3.models.Match;
import utils.Images;

public class Queuer extends JFrame {
	private static final long serialVersionUID = -5129636857649639784L;
	
	public static void main(String[] args) {
		Queuer qer = new Queuer();
		qer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qer.setVisible(true);
	}
	
	public Font listFont = new Font("Tahoma", Font.PLAIN, 12);
	
	public JMenuBar menuBar = new JMenuBar();
	public DefaultListModel<Match> queue = new DefaultListModel<Match>();
	public JList<Match> list = new JList<Match>(queue);
	public JScrollPane scrollPane = new JScrollPane();
	public JLabel imageAddToQueue = new JLabel(new ImageIcon(Images.ADD_TO_QUEUE));	
	
	public Queuer() {
		super("Queuer");
		
		imageAddToQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new AddToQueueWindow(Queuer.this);
			}
		});
		
		menuBar.add(imageAddToQueue);
		
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					removeSelectedValues();
					
				} else if (e.getKeyCode() == KeyEvent.VK_INSERT) {
					int index = list.getSelectedIndex();
					AddToQueueWindow temp = new AddToQueueWindow(Queuer.this);
					temp.indexField.setText("" + index);
					temp.setTitle("Insert into Queue");
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					list.clearSelection();
				}
			}
		});
		list.setFont(listFont);
		
		scrollPane.setViewportView(list);
		
		this.add(scrollPane);
		this.setJMenuBar(menuBar);
		this.pack();
		this.setIconImage(Images.SHOW_QUEUE);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public void add(int index, Match match) {
		for (String s : match.getRedAlliance().team_keys) {
			s = s.replace("frc", "");
		}
		for (String s : match.getBlueAlliance().team_keys) {
			s = s.replace("frc", "");
		}
		queue.add(index, match);
		scrollPane.update(list.getGraphics());
	}
	
	public void add(Match match) {
		add(queue.size(), match);
	}
	
	public void addAll(int index, Match... matches) {
		for (Match m : matches) {
			add(index, m);
		}
	}
	
	public void addAll(Match... matches) {
		for (Match m : matches) {
			add(m);
		}
	}
	
	public void remove(int index) {
		queue.remove(index);
		scrollPane.update(list.getGraphics());
	}
	
	public void removeSelectedValues() {
		for (Match m : list.getSelectedValuesList()) {
			queue.removeElement(m);
		}
		scrollPane.update(list.getGraphics());
	}
	
	public Match poll() {
		Match output = queue.getElementAt(0);
		remove(0);
		return output;
	}


}
