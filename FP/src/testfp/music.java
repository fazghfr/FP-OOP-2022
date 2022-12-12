/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author fauza
 */
public class music {
    public Clip clip;
    
    public music() throws LineUnavailableException{
        clip = AudioSystem.getClip();
    }
    
    public void playMusic(String audioFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error playing music: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void playMusic(String audioFilePath, float volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume); // Set the volume to half its maximum
            
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error playing music: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void playFx(String audioFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFilePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error playing music: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void stopClip(){
        clip.stop();
    }
}
