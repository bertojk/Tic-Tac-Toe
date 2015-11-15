import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer
{
    private String[] sound = {"click_tune.wav", "draw_tune.wav", "help_tune.wav", "winning_tune.wav"};
    private String file;
    public SoundPlayer() {file = null;}
    public void playSound(int i)
    {
        file = sound[i];
        try {
            java.net.URL url = getClass().getClassLoader().getResource(file);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {

            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }

    }
}
