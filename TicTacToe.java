import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class TicTacToe extends JPanel implements ActionListener
{
	private boolean winner;
	private boolean warMode;
	private int size;
	private int count;
	private int player;
	private int source;
	private int score[];

	private Font font;
	private SoundPlayer sp;
	private Setting setting;

	private JPanel board;
	private XOButton buttons[];

	private JButton reset;
	private JButton exit;
	private JButton help;

	private JLabel scoreP1;
	private JLabel scoreP2;
	private JLabel turnDisplay;
	private TitledBorder tb1,tb2;


	public TicTacToe(int s, Color color, boolean warMode)
	{
		winner = false;
		this.warMode = warMode;
		size = s;
		count = 0;
		player = 1;
		score = new int[2];

		font = new Font("Brittanic Bold", Font.PLAIN, 36);
		sp = new SoundPlayer();
		setting = new Setting();

		setLayout(null);

		board = new JPanel(new GridLayout(size, size));
		buttons = new XOButton[size*size];
		for(int i=0;i < size*size ;i++)
		{
			buttons[i]=new XOButton();
			buttons[i].addActionListener(this);
			buttons[i].setButtonSize(600/size);
			board.add(buttons[i]);
		}
		add(board);
		board.setSize(600, 600);
		board.setLocation(10, 10);
		
		help = new JButton (new ImageIcon(this.getClass().getResource("help.png")));
		add(help);
		help.addActionListener(this);
		help.setSize(100, 100);
		help.setLocation(640, 10);
		help.setToolTipText("Help");
		
		reset = new JButton(new ImageIcon(this.getClass().getResource("reset.png")));
		add(reset);
		reset.addActionListener(this);
		reset.setSize(100, 100);
		reset.setLocation(640, 140);
		reset.setToolTipText("Reset");
		
		exit = new JButton(new ImageIcon(this.getClass().getResource("exit.png")));
		add(exit);
		exit.addActionListener(this);
		exit.setSize(100, 100);
		exit.setLocation(640, 270);
		exit.setToolTipText("Exit");

		turnDisplay = new JLabel("Turn P" + Integer.toString(player));
		add(turnDisplay);
		turnDisplay.setFont(font);
		turnDisplay.setSize(300, 50);
		turnDisplay.setLocation(640, 400);

		if(warMode)
		{
			scoreP1 = new JLabel(Integer.toString(score[0]));
			add(scoreP1);
			scoreP1.setFont(font);
			scoreP1.setHorizontalAlignment(SwingConstants.RIGHT);
			scoreP1.setForeground(Color.red);
			scoreP1.setBackground(Color.WHITE);
			scoreP1.setSize(100, 50);
			scoreP1.setLocation(640, 500);
			tb1 = BorderFactory.createTitledBorder("Score P1");
			//tb1.setTitleJustification(TitledBorder.RIGHT);
			scoreP1.setBorder(tb1);

			scoreP2 = new JLabel(Integer.toString(score[1]));
			add(scoreP2);
			scoreP2.setFont(font);
			scoreP2.setHorizontalAlignment(SwingConstants.RIGHT);
			scoreP2.setForeground(Color.blue);
			scoreP2.setSize(100, 50);
			scoreP2.setLocation(640, 560);
			tb2 = BorderFactory.createTitledBorder("Score P2");
			//tb2.setTitleJustification(TitledBorder.RIGHT);
			scoreP2.setBorder(tb2);
		}
		setBackground(color);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		sp.playSound(0);
		if(e.getSource() == help)
		{
			sp.playSound(2);
			showHelp();
		}
		else if(e.getSource() == reset)
		{
			winner = false;
			resetGame();
		}
		else if(e.getSource() == exit) 
		{
			resetGame();
			setVisible(false);
		}
		else
		{
			for (int j = 0; j<size*size; j++)
			{
				if (e.getSource() == buttons[j]) source = j; 
			}
			if(!buttons[source].isTagged())
			{
				buttons[source].changePlayer(player);
				buttons[source].setTagged();
				count++;
				checkScore(player);
				if(!warMode)
				{
					if(count <= size*size)
					{
						if(score[0] == score[1])
						{
							winner = false;
							if (player == 1) player = 2;
							else player = 1;
						}
						else
						{
							winner = true;
							if (score[0] > score[1]) player = 1;
							else player = 2;
							playAgain(winner, player);
						}
						turnDisplay.setText("Turn P" + Integer.toString(player));
					}
					else playAgain(winner, player);

				}
				else
				{
					if (count >= size * size)
					{
						winner = true;
						if (score[0] > score[1]) player = 1;
						else if (score[0] < score[1]) player = 2;
						else winner = false;
						playAgain(winner, player);
					}
					if (player == 1) player = 2;
					else player = 1;
					turnDisplay.setText("Turn P" + Integer.toString(player));
				}

			}
		}
	}

	public void checkScore(int p)
	{
		score[p-1] = 0;
		for(int i = 0;i<size*size;i++)
		{
			if(i%size < size-2)
			{
				if ( buttons[i].getPlayerTag(p) && buttons[i+1].getPlayerTag(p) && buttons[i+2].getPlayerTag(p) )
				{
					if (warMode) score[p - 1]+=1;
					else score[p - 1] = 1;
				}
			}
			if(i < size*(size-2))
			{
				if ( buttons[i].getPlayerTag(p) && buttons[i+size].getPlayerTag(p) && buttons[i+2*size].getPlayerTag(p) )
				{
					if (warMode)score[p - 1] += 1;
					else score[p-1] = 1;
				}
			}
			if(i%size < size-2 && i < size*(size-2))
			{
				if ( buttons[i].getPlayerTag(p) && buttons[i+size+1].getPlayerTag(p) && buttons[i+2*(size+1)].getPlayerTag(p) )
				{
					if (warMode)score[p - 1] += 1;
					else score[p-1] = 1;
				}
			}
			if(i%size > 1 && i < size*(size-2))
			{
				if ( buttons[i].getPlayerTag(p) && buttons[i+size-1].getPlayerTag(p) && buttons[i+2*(size-1)].getPlayerTag(p) )
				{
					if (warMode)score[p - 1] += 1;
					else score[p-1] = 1;
				}
			}
		}
		if(warMode)
		{
			if (p == 1) scoreP1.setText(Integer.toString(score[0]));
			else scoreP2.setText(Integer.toString(score[1]));
		}
	}

	public void resetGame()
	{
		winner = false;
		count = 0;
		player = 1;
		for (int i = 0; i<size*size; i++)
		{
			buttons[i].clear();
		}
		score[0] = 0;
		score[1] = 0;
		if(warMode)
		{
			scoreP1.setText(Integer.toString(score[0]));
			scoreP2.setText(Integer.toString(score[1]));
		}

	}
	private void playAgain(boolean winner,int bName)
	{
		String msg;
		if(winner) 
		{
			sp.playSound(3);
			msg = "Player " + bName +" Won The match!!!";
		}
		else 
		{

			sp.playSound(1);
			msg = "DRAW!!!";
		}
		Object[] options = {"Play Again",
                "No, thanks"
              };
		int n = JOptionPane.showOptionDialog(null,
		    msg
		    ,
		    "GAME OVER!",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    new ImageIcon(this.getClass().getResource("trophy.png")),
		    options,
		    options[1]);
		if(n == 0)
		{
			sp.playSound(0);
			resetGame();
		}
		else
		{
			sp.playSound(0);
			setVisible(false);
		}
	}
	private void showHelp()
	{
		Object[] options = {"OK"};
		String helpMsg = "Maen ajah sendiri! hahahahaa";
		JOptionPane.showOptionDialog(null,
		    helpMsg,
		    "Status",
		    JOptionPane.DEFAULT_OPTION,
		    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource("help.png")), options,null);
	}

}