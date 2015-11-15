import java.awt.Image;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class XOButton extends JButton
{
	private ImageIcon X,O;
	private int playerTag = 0;
	private boolean hasTagged = false;
	private int size = 100;

	public XOButton()
	{
			X = new ImageIcon(getClass().getResource("x_animated.gif"));
			O = new ImageIcon(getClass().getResource("o_animated.gif"));
	}
	public boolean isTagged() {return hasTagged;}
	public void setTagged(){hasTagged = true;}
	public void setButtonSize(int size) {if(size < 100) this.size = size;}
	public boolean getPlayerTag(int p) {if (playerTag == p) return true; else return false;}
	public void clear(){ setIcon(null); hasTagged = false; playerTag = 0;}
	public void changePlayer(int p)
	{
		if(p == 1){
			setIcon(new ImageIcon(X.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		}
		else {
			setIcon(new ImageIcon(O.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		}
		playerTag = p;
	}
}