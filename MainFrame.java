import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements ActionListener
{

	final private int maxSize = 15;
	final private int minSize  = 3;
	final private int minSizeWar = 8;
	private Font font;
	private TicTacToe game;
	private Setting setting;
	private SoundPlayer sp;

	public static JPanel menuPanel;
	private JButton start;
	private JButton set;
	private JButton exit;

	private JLabel modeLabel;
	private JLabel sModeLabel;
	private JButton wLButton;
	private JButton wRButton;
	private boolean warMode;

	private JPanel gameSetup;
	private JLabel sizeLabel;
	private JLabel nSizeLabel;
	private JButton addSize;
	private JButton subSize;
	private int size;

	private JButton ok;

	// CONSTRUCT MAIN FRAME
	public MainFrame()
	{
		super("Tic Tac Toe");
		setSize(800, 700);
		setResizable(false);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new CardLayout());
		sp = new SoundPlayer();
        setting = new Setting();
		font = new Font("Brittanic Bold", Font.HANGING_BASELINE, 20);


		// 1. CREATE MENU PANEL
		menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout());
		menuPanel.setVisible(true);
		menuPanel.setBackground(setting.colorList[setting.cNum]);

		// 1. 1. CREATE START BUTTON
		start = new JButton("Start");
		menuPanel.add(start);
		start.addActionListener(this);

		// 1. 2. CREATE SETTING BUTTON
		set = new JButton("Setting");
		menuPanel.add(set);
		set.addActionListener(this);

		// 1.3. CREATE EXIT BUTTON
		exit = new JButton("Exit");
		menuPanel.add(exit);
		exit.addActionListener(this);

		// 1.4. ADD MENU PANEL TO MAINFRAME
		add(menuPanel);
		add(setting);


		// 2. CREATE GAME SETUP PANEL
		gameSetup = new JPanel();
		gameSetup.setLayout(null);

		// 2.1. INITIALIZE VARIABLE
		warMode = false;
		size = minSize;

		// 2.2. CREATE WAR MODE SETUP
		// 2.2.1. CREATE WAR MODE TITLE LABEL
		modeLabel = new JLabel("War Mode");
		gameSetup.add(modeLabel);
		modeLabel.setFont(font);
		modeLabel.setSize(400, 50);
		modeLabel.setLocation(50, 100);
		// 2.2.2. CREATE LEFT BUTTON FOR WAR MODE
		wLButton = new JButton("<<");
		gameSetup.add(wLButton);
		wLButton.addActionListener(this);
		wLButton.setLocation(425, 100);
		wLButton.setSize(50, 50);
		wLButton.setEnabled(false);
		// 2.2.3. CREATE WAR MODE VALUE LABEL
		sModeLabel = new JLabel("NO");
		gameSetup.add(sModeLabel);
		sModeLabel.setFont(font);
		sModeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sModeLabel.setLocation(550, 100);
		sModeLabel.setSize(50, 50);
		// 2.2.4. CREATE RIGHT BUTTON FOR WAR MODE
		wRButton = new JButton(">>");
		gameSetup.add(wRButton);
		wRButton.addActionListener(this);
		wRButton.setLocation(670, 100);
		wRButton.setSize(50, 50);

		// 2.3. CREATE BOARD SIZE SETUP
		// 2.3.1. CREATE BOARD SIZE TITLE LABLE
		sizeLabel = new JLabel("BoardSize");
		gameSetup.add(sizeLabel);
		sizeLabel.setFont(font);
		sizeLabel.setSize(400, 50);
		sizeLabel.setLocation(50, 160);
		// 2.3.2. CREATE SUBSTRACT BUTTON FOR BOARD SIZE SETUP
		subSize = new JButton("-");
		gameSetup.add(subSize);
		subSize.addActionListener(this);
		subSize.setLocation(425, 160);
		subSize.setSize(50, 50);
		subSize.setEnabled(false);
		// 2.3.3. CREATE VALUE LABEL FOR BOARD SIZE
		nSizeLabel = new JLabel(Integer.toString(minSize));
		gameSetup.add(nSizeLabel);
		nSizeLabel.setFont(font);
		nSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nSizeLabel.setLocation(550, 160);
		nSizeLabel.setSize(50, 50);
		// 2.3.4. CREATE ADD BUTTON FOR BOARD SIZE SETUP
		addSize = new JButton("+");
		gameSetup.add(addSize);
		addSize.addActionListener(this);
		addSize.setLocation(670, 160);
		addSize.setSize(50, 50);

		// 2.4. CREATE OK BUTTON TO CONFIRM GAME SETUP
		ok = new JButton("OK");
		gameSetup.add(ok);
		ok.addActionListener(this);
		ok.setLocation(600, 300);
		ok.setSize(100, 100);
		// 2.5. ADD GAME SETUP PANEL TO MAIN FRAME
		add(gameSetup);
		gameSetup.setVisible(false);

		setVisible(true);
	}

	// SET ACTION BUTTON FOR MAIN FRAME
	public void actionPerformed(ActionEvent e)
    {
        sp.playSound(0);
		if(e.getSource() == start)
		{
			menuPanel.setVisible(false);
			gameSetup.setVisible(true);

		}
		else if(e.getSource() == set)
		{
            menuPanel.setVisible(false);
			setting.setVisible(true);
		}
		else if(e.getSource() == exit)
		{
			dispose();
		}
		else if (e.getSource() == addSize)
		{
			size += 1 ;
			subSize.setEnabled(true);
			if (size < maxSize) addSize.setEnabled(true);
			else addSize.setEnabled(false);
			nSizeLabel.setText(Integer.toString(size));
		}
		else if (e.getSource() == subSize)
		{
			int minNum;
			if(warMode) minNum = minSizeWar;
			else minNum = minSize;
			size -= 1;
			addSize.setEnabled(true);
			if(size > minNum)subSize.setEnabled(true);
			else subSize.setEnabled(false);
			nSizeLabel.setText(Integer.toString(size));
		}
		else if (e.getSource() == wLButton)
		{
			warMode = false;
			size = minSize;
			subSize.setEnabled(false);
			sModeLabel.setText("NO");
			wLButton.setEnabled(false);
			wRButton.setEnabled(true);
			nSizeLabel.setText(Integer.toString(size));
		}
		else if (e.getSource() == wRButton)
		{
			warMode = true;
			size = minSizeWar;
			subSize.setEnabled(false);
			sModeLabel.setText("YES");
			wLButton.setEnabled(true);
			wRButton.setEnabled(false);
			nSizeLabel.setText(Integer.toString(size));
		}
		else if (e.getSource() == ok)
		{
			gameSetup.setVisible(false);
			game = new TicTacToe(size,setting.colorList[setting.cNum],warMode);
			add(game);
			game.setVisible(true);
			size = 3;
			nSizeLabel.setText(Integer.toString(size));
		}
		
	}

	// RUN THE APPS
	public static void main(String args[]){
		new MainFrame();
	}
}
