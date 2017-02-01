package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class PianoGUI implements ActionListener {

	private static final int NUM_OCTAVES = 5;
	/** Holds the possible notes. */
	private int[] notes = { 0, 2, 4, 5, 7, 9, 11 };
	/** Holds the possible sharps. */
	private int[] sharps = { 1, 3, 6, 8, 10 };

	private JFrame frame;

	MidiOut mid;

	public PianoGUI() {
		this.mid = new MidiOut(); 
		frame = new JFrame("Piano GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		


		
		Container mainPanel = frame.getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setForeground(Color.WHITE);
		mainPanel.setBackground(Color.BLACK);
		
		JCheckBox chordMode = new JCheckBox();
		chordMode.setText("Chord Mode");
		chordMode.setForeground(Color.WHITE);
		chordMode.setBackground(Color.BLACK);
		
		JButton chordGo = new JButton();
		chordGo.setText("Play!");

		chordGo.setForeground(Color.WHITE);
		chordGo.setBackground(Color.BLACK);
		
		// Call the make keys method
		JLayeredPane pianoKeyPanel = makeKeys();
		// Add to main panel
		
		
		mainPanel.add(BorderLayout.NORTH, pianoKeyPanel);
		mainPanel.add(BorderLayout.EAST,chordMode);
		mainPanel.add(BorderLayout.WEST,chordGo);
		
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setSize(1310, 250);
	}

	public JLayeredPane makeKeys() {
		int keyNum;
		int xbase = 0;
		int y = 0;
		int x = xbase;

		JLayeredPane keyBoard = new JLayeredPane();
		keyBoard.setPreferredSize(new Dimension(360, 240));
		keyBoard.add(Box.createRigidArea(new Dimension(x, 0)));

		for (int i = 3; i < NUM_OCTAVES+3; i++) {
			for (int j = 0; j < 7; j++) {
				ImageIcon img = new ImageIcon("images/" + notes[j] + ".png");
				JButton jb = new JButton(img);
				keyNum = i * 12 + notes[j];
				jb.setName(String.valueOf(keyNum));
				jb.setActionCommand(String.valueOf(keyNum));
				jb.addActionListener(this);
				jb.setBounds(x, y, 35, 162);
				keyBoard.add(jb, new Integer(1));
				keyBoard.add(Box.createRigidArea(new Dimension(2, 0)));
				x += 37;
			}
		}

		ImageIcon img = new ImageIcon("images/blackKey.png");

		// Make 5 "keys"

		for (int i = 3; i < NUM_OCTAVES+3; i++) {
			JButton jb0 = new JButton(img);
			keyNum = sharps[0] + i * 12;
			jb0.setName(String.valueOf(keyNum));
			jb0.setActionCommand(String.valueOf(keyNum));
			jb0.addActionListener(this);
			JButton jb1 = new JButton(img);
			keyNum = sharps[1] + i * 12;
			jb1.setName(String.valueOf(keyNum));
			jb1.setActionCommand(String.valueOf(keyNum));
			jb1.addActionListener(this);
			JButton jb2 = new JButton(img);
			keyNum = sharps[2] + i * 12;
			jb2.setName(String.valueOf(keyNum));
			jb2.setActionCommand(String.valueOf(keyNum));
			jb2.addActionListener(this);
			JButton jb3 = new JButton(img);
			keyNum = sharps[3] + i * 12;
			jb3.setName(String.valueOf(keyNum));
			jb3.setActionCommand(String.valueOf(keyNum));
			jb3.addActionListener(this);
			JButton jb4 = new JButton(img);
			keyNum = sharps[4] + i * 12;
			jb4.setName(String.valueOf(keyNum));
			jb4.setActionCommand(String.valueOf(keyNum));
			jb4.addActionListener(this);
			// Place the 5 keys
			x = xbase;
			jb0.setBounds(x+22+(259*(i-3)), y, 25, 95);
			keyBoard.add(jb0, new Integer(2));
			jb1.setBounds(x+60+(259*(i-3)), y, 25, 95);
			keyBoard.add(jb1, new Integer(2));
			jb2.setBounds(x+133+(259*(i-3)), y, 25, 95);
			keyBoard.add(jb2, new Integer(2));
			jb3.setBounds(x+171+(259*(i-3)), y, 25, 95);
			keyBoard.add(jb3, new Integer(2));
			jb4.setBounds(x+209+(259*(i-3)), y, 25, 95);
			keyBoard.add(jb4, new Integer(2));
		}
		// Return the keyboard
		return keyBoard;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		String command = "";
		JButton jb = null;

		Object obj = e.getSource();
		jb = (JButton) obj;
		// Get the action command
		command = jb.getActionCommand();
		System.out.println(command);
		mid.playNote(Integer.parseInt(command),400);
		
	}

}
