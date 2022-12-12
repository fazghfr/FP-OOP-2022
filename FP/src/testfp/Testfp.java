/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testfp;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author fauza
 */
public class Testfp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            gamepanel.initGamePanel();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Testfp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
