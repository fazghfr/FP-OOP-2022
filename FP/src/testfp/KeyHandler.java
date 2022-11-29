/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author fauza
 */
public class KeyHandler implements KeyListener{
    public boolean upPressed, 
                   downPressed, 
                   leftPressed,
                   rightPressed;
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = true;
        }
        else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        else if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
    
}
