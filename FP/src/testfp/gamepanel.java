/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
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
    private BufferedImage background;
    public music bgMusic;
    
    //fps
    final int FPS = commons._FPS.value;
    
    //things inside of the game
    public menu mn = new menu();
    public highScore hs =new highScore(new JFrame());
    public boolean isGameOver = false, isGameStarted = false;
    public KeyHandler kh = new KeyHandler(mn, this, hs);
    public Thread gameThread;
    public player Player = new player(this, kh);
    public ArrayList<enemy> enemies = new ArrayList<>();
    public int enemiesLeft = 0;
    private JButton tryAgain, toMainMenu;
    private int level = 0;
    public JFrame gameFrame;
    public music ms;//music for game state
    

    public int getLevel(){
        return this.level;
    }
    
    public state State = state.MENU;
    
    public gamepanel(JFrame frame) throws LineUnavailableException{
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        this.gameFrame = frame;
        this.bgMusic = new music();
        this.ms = new music();
        
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/media/bgfixmaybe.png"));
            bgMusic.playMusic("src/media/music.wav");
        } catch (IOException ex) {
            Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startGameThread(){
            
            gameThread = new Thread(this);
            gameThread.start();
            
            try {
                highScore.storeScore(Player.playerName, Player.killcount);
                hs.sortScores();
            } catch (IOException ex) {
                Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
   @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() - drawInterval;
        
      
        while(gameThread != null && !isGameOver){
            if(Player.playerName != null){
                isGameStarted = true;
            }
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

            if(State == state.GAME){
                if(isGameOver){
                      
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLayout(new FlowLayout());
                    frame.setSize(new Dimension(300, 100));

                    JButton back = new JButton("Back to Main Menu");

                    back.addActionListener(e -> {
                            frame.dispose();
                            gameFrame.dispose();
                            ms.stopClip();
                        try {
                            initGamePanel();
                        } catch (LineUnavailableException ex) {
                            Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    // Add the buttons to the frame and make it visible
                    frame.add(back);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.pack();
                    frame.setVisible(true); 
                }
            }
        }
    }
    
    public void update(){
        if(State == state.GAME && isGameStarted){
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
                if(hs.champions ==  null) try {
                    hs.champions = hs.getChamp();
                } catch (IOException ex) {
                    System.out.println("error on getting champions data");
                }
                if(hs.champions.score < Player.killcount*5){
                    updateEnemiesSpeed(enemies);
                }
                updateEnemies(enemies);
            }else{
                isGameOver = true;
            }

            checkEnemyPos();
        }
    }
    
    //oveloading
    public void levelUp(){
        level++;
        addEnemy();
    }
    //overloading
    public void levelUp(int i){
        level += i;
        addEnemy();
    }
    
    boolean glob = false;
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(background, 0, 0, null);
        if(State == state.GAME){
            bgMusic.stopClip();
            if(!glob){
                ms.playMusic("src/media/musicgame.wav", -20.0f);
                glob = true;
            }
            
            
            Graphics2D g2 = (Graphics2D) g;

            InputStream iS = getClass().getResourceAsStream("/media/ArcadeAlternate.ttf");
            Font arcade = null;
            try {
                arcade = Font.createFont(Font.TRUETYPE_FONT, iS);
            } catch (FontFormatException ex) {
                Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            g2.setColor(Color.WHITE);
            g2.setFont(arcade);
            if(!isGameOver){

                drawEnemy(g2);
                Player.draw(g2);

                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
                g2.drawString("Points Earned = " +Player.killcount*5, tileSize/2 ,tileSize - tileSize/4);

                g2.dispose();
            }
            else{
                drawEnemy(g2);
                Player.draw(g2);

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
                
                try {
                    hs.champions = hs.getChamp();
                } catch (IOException ex) {
                    Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(hs.champions.score < Player.killcount*5){
                    g2.setColor(Color.GREEN);
                    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
                    g2.drawString("New HighScore!! = " +Player.killcount*5, getXCenteredStringPos(g2, "New HighScore!! = " +Player.killcount*5 +"You've Won!") + tileSize, ScreenHeight/3);
                }else{
                    g2.setColor(Color.red);
                    g2.drawString("Game Over!", getXCenteredStringPos(g2, "Game Over!"), ScreenHeight/3);
                }
                
                try {
                    hs.storeScore(Player.playerName, Player.killcount*5);
                } catch (IOException ex) {
                    Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
                }                  
            }
            
        }
        else if(State == state.MENU){
            mn.draw((Graphics2D) g);
        }
        
        else if(State == state.HIGHSCORE){
            try {
                hs.draw((Graphics2D) g);
            } catch (IOException ex) {
                Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void updateEnemiesSpeed(ArrayList<enemy> e){
        for(int i = 0; i<e.size(); i++){
            enemy temp = e.get(i);
            temp.setDet();
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
    
    public static void initGamePanel() throws LineUnavailableException{
        JFrame window =  new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setTitle("Space Defender");
        
        gamepanel gamePanel = new gamepanel(window);
        window.setSize(commons.ORI_TILESIZE.value * commons.SCR_COLUMN.value * 
                     commons._SCALE.value, commons.ORI_TILESIZE.value * commons.SCR_ROW.value * 
                     commons._SCALE.value);
        window.setLocationRelativeTo(null);
        window.add(gamePanel);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        
        
        gamePanel.startGameThread();
    }
    
    public int getXCenteredStringPos(Graphics2D g, String str){
        int length = (int)g.getFontMetrics().getStringBounds(str, g).getWidth();
        return (ScreenWidth/2 - length/2);        
    }

}

