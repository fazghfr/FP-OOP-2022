/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import testfp.entity.player;

/**
 *
 * @author fauza
 */
public class gamepanel extends JPanel implements Runnable{
    //setting
    final int originalTileSize = 16;
    final int scale = 4;
    
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 9;
    public final int maxScreenRow = 12;
    public final int ScreenWidth = tileSize * maxScreenColumn;
    public final int ScreenHeight = tileSize * maxScreenRow;
    
    //fps
    final int FPS = 120;
    
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    player Player = new player(this, kh);
    
    
    public gamepanel(){
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
   @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() - drawInterval;
        
        while(gameThread != null){
            System.out.println(Player.x +" " +Player.y);
            update();//updating the position
            
            repaint();//repaint the character with the updated position
            
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void update(){
        Player.update();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        Player.draw(g2);
        g2.dispose();
    } 
}

