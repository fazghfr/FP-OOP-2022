/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import testfp.KeyHandler;
import testfp.gamepanel;

/**
 *
 * @author fauza
 */
public class player extends Sprite{
    gamepanel gp;
    KeyHandler kh;
    
    public player(gamepanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;
        setDefaultVal();
    }
    
    public void setDefaultVal(){
        x = gp.tileSize * (gp.maxScreenColumn/2);//center of x plane
        y = gp.tileSize * (gp.maxScreenRow - 1);//bottom of y plane
        speed = 4;
        this.setPlayerImage();
        direction = "idle";
    }
    
    public void setPlayerImage(){
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream("/media/shipleft.png"));
            idle1 = ImageIO.read(getClass().getResourceAsStream("/media/shipidle.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/media/shipright.png"));
            
            left2 = ImageIO.read(getClass().getResourceAsStream("/media/shipleft2.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("/media/shipidle2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/media/shipright2.png"));
        } catch (IOException ex) {
            Logger.getLogger(player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        System.out.println(direction);
        if(kh.upPressed == true){
            direction = "idle";
            y -= speed;
        }
        else if(kh.downPressed == true){
            direction = "idle";
            y += speed;
        }
        else if(kh.leftPressed == true){
            direction = "left";
            x -= speed;
        }
        else if(kh.rightPressed == true){
            direction = "right";
            x += speed;
        }
        
        
        if(y < 0){
            y = 0;
        }
        
        else if(y > gp.ScreenHeight - gp.tileSize){
            y = gp.ScreenHeight - gp.tileSize;
        }
        
        else if(x < 0){
           x = 0;
        }
        
        else if(x > gp.ScreenWidth - gp.tileSize){
            x = gp.ScreenWidth - gp.tileSize;
        }
        
        spriteCounter++;
        if(spriteCounter > 20){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }else{
                spriteNumber = 1;
            }
            
            spriteCounter = 0;
        }
    }
    
    public void draw(Graphics2D g2){
        BufferedImage img = null;
        switch(direction){
            case "idle":
                if(spriteNumber == 1){
                    img = idle1;
                }else{
                    img = idle2;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    img = left1;
                }else{
                    img = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    img = right1;
                }else{
                    img = right2;
                }               
                break;
        }
        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);
    }
}
