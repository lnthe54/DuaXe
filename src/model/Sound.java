package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    public static void play(String name){
        try{
            File file = new File("src/sounds/" + name);
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip.open(stream);
            clip.start();
        }
        catch (Exception e){

        }
    }
}
