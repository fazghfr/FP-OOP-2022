/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testfp;

import javax.swing.JFrame;

/**
 *
 * @author fauza
 */
public class Testfp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window =  new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setTitle("Final Project");
        
        gamepanel gamePanel = new gamepanel();
        window.add(gamePanel);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        
        
        gamePanel.startGameThread();
        
    }
    
}
