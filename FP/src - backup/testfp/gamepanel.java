/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

import testfp.entity.enemy;
import testfp.entity.player;

/**
 *
 * @author fauza
 */
public class gamepanel extends JPanel implements Runnable{
    //setting
    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 9;
    public final int maxScreenRow = 16;
    public final int ScreenWidth = tileSize * maxScreenColumn;
    public final int ScreenHeight = tileSize * maxScreenRow;
    
    //fps
    final int FPS = 120;
    
    //things inside of the game
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    player Player = new player(this, kh);
    
    ArrayList<enemy> enemies = new ArrayList<>();
    private int level = 0;

    public int getLevel(){
        return this.level;
    }

    int enemiesLeft = 0;
    
    
    public gamepanel(){
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.BLACK);
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
    
    boolean globFlag = true;
    
    public void update(){
        Player.update();
        if(Player.shot){
            Player.Bullt.checkCollision(enemies, this);
        }
        
        if(enemies.size() == 0){
            level++;
            addEnemy();
        }else{
            updateEnemies(enemies);
        }
        
        checkEnemyPos();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        drawEnemy(g2);
        Player.draw(g2);
        
        g2.dispose();
    } 
    
    public void drawEnemy(Graphics2D g){
        for(int i = 0; i<enemies.size(); i++){
            enemies.get(i).draw(g);
        }
    }
    
    public void addEnemy(){
        int size = 1 + level;
        if(size >= maxScreenColumn - maxScreenColumn/3) size = maxScreenColumn - maxScreenColumn/3;
        enemiesLeft = size;
        for(int i = 0; i< enemiesLeft; i++){
            int xcol, ypos = 0;
            Random random = new Random();
            
            do{
                xcol = random.nextInt(ScreenWidth) / tileSize;
            }while(iscolumnOccupied(xcol));
            enemy enemy = new enemy(xcol*tileSize, ypos, tileSize, tileSize);
            enemies.add(enemy);
        }    
        
    }

    public void updateEnemies(ArrayList<enemy> e){
        for(int i = 0; i<e.size(); i++){
            enemy temp = e.get(i);
            temp.update(this);
        }
    }
    
    public void checkEnemyPos(){
        if(enemies.get(0).y > ScreenHeight){
            enemies.clear();
        }
    }
    
    
    public boolean iscolumnOccupied(int pos){
        for(int i = 0; i<enemies.size(); i++){
            
            if(pos == enemies.get(i).x/tileSize){
                return true;
            }
        }
        
        return false;
    }
}

