/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

/**
 *
 * @author fauza
 */
public class KeyHandler implements KeyListener{
    public boolean upPressed, 
                   downPressed, 
                   leftPressed,
                   rightPressed,
                   shootPressed,
                   exitPressed;
                   
    public gamepanel gp;
    public menu mn;
    public highScore hs;
    
    public KeyHandler(gamepanel gp){
        this.gp = gp;
    }
    public KeyHandler(menu mn){
        this.mn = mn;
    }
    public KeyHandler(highScore hs){
        this.hs = hs;
    }
    public KeyHandler(menu mn, gamepanel gp, highScore hs){
        this.mn = mn;
        this.gp = gp;
        this.hs = hs;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(gp.State == state.GAME){
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
            else if(code == KeyEvent.VK_SPACE){
                shootPressed = true;
            }else if(code == KeyEvent.VK_BACK_SPACE){
                exitPressed = true;
            }
        }
        if(gp.State == state.MENU){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                mn.cmdNum--;
                if(mn.cmdNum < 0) mn.cmdNum = 0;
            }
            else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                mn.cmdNum++;
                if(mn.cmdNum > 2) mn.cmdNum = 2;
            }
            else if(code == KeyEvent.VK_ENTER){
                
                switch(mn.cmdNum){
                    case 0:
                        gp.State = state.GAME;
                        String tempName = null;
                        while(tempName == null){
                            tempName = JOptionPane.showInputDialog(null, "Please enter your name (one word) :", "Player Name", JOptionPane.PLAIN_MESSAGE);
                        }
                        String splitW[] = tempName.split(" ");
                        gp.Player.playerName = splitW[0];
                        
                        break;
                    case 1 :
                        gp.State = state.HIGHSCORE;
                        break;
                    case 2 :
                        System.exit(0);
                        break;
                }
            }
        }
        
        if(gp.State == state.HIGHSCORE){
            if(code == KeyEvent.VK_BACK_SPACE){
                  gp.State = state.MENU;
            }
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
         else if(code == KeyEvent.VK_SPACE){
            shootPressed = false;
        }
    }
    
}
