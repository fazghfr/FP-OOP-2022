/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author fauza
 */
public class enemy extends Sprite{
    public String typeEnemy;
    public boolean isDead = false;
    public BufferedImage t1, t2, t3;
    int width, height;

    
    public enemy(int x, int y, int h, int w){
        this.x = x;
        this.y = y;
        this.height = h;
        this.width = w;
        setImageType();
    }
    
    private void setImageType(){
        try {
            t1 = (ImageIO.read(getClass().getResourceAsStream("/media/shipidle.png")));
            t2 = (ImageIO.read(getClass().getResourceAsStream("/media/shipidle.png")));;
            t3 = (ImageIO.read(getClass().getResourceAsStream("/media/shipidle.png")));;
        } catch (IOException ex) {
            Logger.getLogger(enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
    }
    
    public void draw(Graphics g){
//        g.setColor(Color.red);
//        g.fillRect(x, y, 16, 16);
          g.drawImage(t1, x, y, width, height, null);
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

    
}
