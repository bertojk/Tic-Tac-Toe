import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Setting extends JPanel implements ActionListener
{
    private Font font;
    private SoundPlayer sp;
    private JButton back;

    private JLabel bgLabel;
    private JLabel bgColor;
    private JButton rightColor;
    private JButton leftColor;
    public Color[] colorList = {Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
            Color.MAGENTA, Color.ORANGE, Color.PINK, Color.WHITE, Color.YELLOW};
    private String[] color = {"CYAN", "DARK GRAY", "GRAY", "GREEN", "LIGHT GRAY",
                            "MAGENTA", "ORANGE", "PINK", "WHITE","YELLOW"};
    public int cNum;

    // CONSTRUCT SETTING PANEL
    public Setting()
    {
        cNum = 0;
        sp = new SoundPlayer();
        font = new Font("Brittanic Bold", Font.HANGING_BASELINE, 20);
        setLayout(null);

        // 1. CONSTRUCT BACKGROUND COLOR SETTING
        // 1. 1. CONSTRUCT BACKGROUND LABEL
        bgLabel = new JLabel("BackGround Color \t\t");
        add(bgLabel);
        bgLabel.setFont(font);
        bgLabel.setSize(400, 50);
        bgLabel.setLocation(50, 170);
        // 1.2. CONSTRUCT LEFT SELECTION BUTTON FOR BACKGROUND COLOR
        leftColor = new JButton("<<");
        add(leftColor);
        leftColor.addActionListener(this);
        leftColor.setLocation(425, 170);
        leftColor.setSize(50, 50);
        // 1.3. CONSTRUCT VALUE LABEL FOR BACKGROUND COLOR
        bgColor = new JLabel();
        add(bgColor);
        bgColor.setFont(font);
        bgColor.setHorizontalAlignment(SwingConstants.CENTER);
        bgColor.setLocation(500, 170);
        bgColor.setSize(150, 50);
        bgColor.setText(color[cNum]);
        // 1.4. CONSTRUCT RIGHT SELECTION BUTTON FOR BACKGROUND COLOR
        rightColor = new JButton(">>");
        add(rightColor);
        rightColor.addActionListener(this);
        rightColor.setLocation(670, 170);
        rightColor.setSize(50, 50);

        // 2. CONSTRUCT BACK BUTTON
        back = new JButton("Back");
        add(back);
        back.setLocation(600, 600);
        back.setSize(100, 50);
        back.addActionListener(this);

        setBackground(colorList[cNum]);
    }

    // SET ACTION BUTTON FOR SETTING PANEL
    public void actionPerformed(ActionEvent e)
    {
        sp.playSound(0);
        if (e.getSource() == rightColor)
        {
            cNum = (cNum+1)%color.length;
            bgColor.setText(color[cNum]);
        }
        else if (e.getSource() == leftColor)
        {
            if(cNum - 1 == -1)cNum = color.length-1;
            else cNum --;
            bgColor.setText(color[cNum]);
        }
        else if(e.getSource() == back) { setVisible(false);}
        setBackground(colorList[cNum]);
        MainFrame.menuPanel.setBackground(colorList[cNum]);
    }
}
