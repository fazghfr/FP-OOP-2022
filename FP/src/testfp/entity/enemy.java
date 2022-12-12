/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import testfp.gamepanel;

/**
 *
 * @author fauza
 */
public class enemy extends Sprite implements hostile{
    public String typeEnemy;
    public BufferedImage t1, t2, t3;
    int width, height, num, det = 0;//det untuk keperluan highscore

    
    public enemy(int x, int y, int h, int w){
        this.x = x;
        this.y = y;
        this.height = h;
        this.width = w;
        this.speed = 1;
        setImageType();
    }
    
    private void setImageType(){
        try {
            t1 = (ImageIO.read(getClass().getResourceAsStream("/media/enemy.png")));
            t2 = (ImageIO.read(getClass().getResourceAsStream("/media/enemy2.png")));;
            t3 = (ImageIO.read(getClass().getResourceAsStream("/media/shipidle.png")));;
        } catch (IOException ex) {
            Logger.getLogger(enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void draw(Graphics g){
          BufferedImage img = null;
          
          int det = num;
          img = switch (det%2) {
            case 0 -> t1;
            default -> t2;
        };
          g.drawImage(img, x, y, width, height, null);
    }
    
    public BufferedImage rotateImage(BufferedImage img){
        
        Graphics2D g = img.createGraphics();
        g.rotate(180);
        
        return img;
    }
    
    public void setType(){
        int number;
        number = new Random().nextInt(3) + 1;
        
        switch(number){
            case 1:
                idle1 = t1;
                break;
            case 2:
                idle1 = t2;
                break;
            case 3:
                idle1 = t3;
                break;
        }
    }
    
    public void setDet(){
        this.det = 1;
    }

    @Override
    public boolean deathStatus() {
        return isDead;
    }

    @Override
    public void setDead() {
        this.isDead = true;
    }

    @Override
    public void update(gamepanel gp) {
        if(det==0){
            y += speed;
        }else{
            y += speed+det;
        }
        num = gp.getLevel();
    }

    
}
