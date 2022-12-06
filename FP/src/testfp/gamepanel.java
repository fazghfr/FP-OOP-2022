/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
    final int originalTileSize = commons.ORI_TILESIZE.value;
    final int scale = commons._SCALE.value;
    
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = commons.SCR_COLUMN.value;
    public final int maxScreenRow = commons.SCR_ROW.value;
    public final int ScreenWidth = tileSize * maxScreenColumn;
    public final int ScreenHeight = tileSize * maxScreenRow;
    
    //fps
    final int FPS = commons._FPS.value;
    
    //things inside of the game
    boolean isGameOver = false;
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
        
        while(gameThread != null && !isGameOver){
            update();//updating the position
            repaint();//repaint the character with its updated position
            
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
        
        System.out.println("kill count = " +Player.killcount);//p_malem
    }
    
    
    public void update(){
        Player.update();
        if(Player.shot){
            Player.Bullt.checkCollision(enemies, this, Player);
        }
        
        if(enemies.size() == 0){
            if(level != commons.GAME_MAXLEVEL.value){
                levelUp();
            }else{
                isGameOver = true;//p_malem
            }
        }else if(!Player.isDead){
            updateEnemies(enemies);
        }else{
            isGameOver = true;
        }
        
        checkEnemyPos();
    }
    
    public void levelUp(){
        level++;
        addEnemy();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("MV Boli", Font.BOLD, 45));
        if(!isGameOver){
            
            drawEnemy(g2);
            Player.draw(g2);
            
            g.setFont(new Font("MV Boli", Font.BOLD, 12));
            g.drawString("Kill Count = " +Player.killcount, tileSize * (maxScreenColumn/2), tileSize * (maxScreenRow-1));

            g2.dispose();
        }
        else{
            drawEnemy(g2);
            Player.draw(g2);
            
            
            if(!Player.isDead){
                g.drawString("You've Won!", 2*tileSize - 8, ScreenHeight/3);
            }else{
                g.drawString("You've lost", 2*tileSize - 8, ScreenHeight/3);
            }
        }
    } 
    
    public void drawEnemy(Graphics2D g){
        for(int i = 0; i<enemies.size(); i++){
            enemies.get(i).draw(g);
        }
    }
    
    public void addEnemy(){
        int size = 1 + level;
        
        if(size >= maxScreenColumn - 1) size = maxScreenColumn - 1;
        enemiesLeft = size;
        
        int yFirstBatch = -tileSize;
        for(int i = 0; i< enemiesLeft/2; i++){
            int xcol, ypos = yFirstBatch;
            Random random = new Random();
            
            do{
                xcol = random.nextInt(ScreenWidth) / tileSize;
            }while(iscolumnOccupied(xcol));
            enemy enemy = new enemy(xcol*tileSize, ypos, tileSize, tileSize);
            enemies.add(enemy);
        }
        
        for(int i = enemiesLeft/2; i< enemiesLeft; i++){
            int xcol, ypos = yFirstBatch - 3*tileSize;
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
        if(enemies.size()==0){
            return;
        }
        
        if( enemies.get(0).y > ScreenHeight){
            Player.isDead = true;
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

