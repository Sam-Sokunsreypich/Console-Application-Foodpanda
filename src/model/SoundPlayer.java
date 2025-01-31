package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundPlayer {

    public void playSound(String fileName) {
        try{
            File soundFile = new File(fileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip =AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
