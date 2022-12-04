/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import testfp.gamepanel;

/**
 *
 * @author fauza
 */
public class bullet extends Sprite{
    public int width, height;
    
    public bullet(int xpos, int ypos, int w, int h){
        x = xpos;
        y = ypos;
        width = w;
        height = h;
        setImage();
    }
    
    public void setImage(){
        try {
            idle1 = ImageIO.read(getClass().getResourceAsStream("/media/shipidle.png"));
        } catch (IOException ex) {
            Logger.getLogger(bullet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics2D g){
        g.drawImage(idle1, x, y, width, height, null);
    }
    
    
    public void checkCollision(ArrayList<enemy> Enemy, gamepanel gp){
        for(int i = 0; i < Enemy.size(); i++){
            enemy Enemies = Enemy.get(i);
            if( (y < Enemies.y + gp.tileSize && y > 0)&& (x > Enemies.x && x<Enemies.x+gp.tileSize)){
                Enemies.isDead = true;
                this.y = -1;
                Enemy.remove(i);
            }
        }
    }
}
