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
import javax.sound.sampled.LineUnavailableException;
import testfp.commons;
import testfp.gamepanel;
import testfp.music;

/**
 *
 * @author fauza
 */
public class bullet extends Sprite{
    public int width, height;
    private int maxSpeed = commons.BULLET_MAXSPEED.value;
    public int getMaxSpeed(){
        return maxSpeed;
    }
    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }
    
    public bullet(int xpos, int ypos, int w, int h){
        x = xpos;
        y = ypos;
        width = w;
        height = h;
        speed = 8;
        setImage();
    }
    
    public void setImage(){
        try {
            idle1 = ImageIO.read(getClass().getResourceAsStream("/media/bullet.png"));
        } catch (IOException ex) {
            Logger.getLogger(bullet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics2D g){
        g.drawImage(idle1, x, y, width, height, null);
    }
    
    
    public void checkCollision(ArrayList<enemy> Enemy, gamepanel gp, player p){
        for(int i = 0; i < Enemy.size(); i++){
            enemy Enemies = Enemy.get(i);
            if( (y < Enemies.y + gp.tileSize && y > 0)&& (x > Enemies.x && x<Enemies.x+gp.tileSize)){
                Enemies.setDead();
                music sfx = null;
                try {
                    sfx = new music();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(bullet.class.getName()).log(Level.SEVERE, null, ex);
                }
                sfx.playFx("src/media/explosion.wav");
                p.killcount++;
                this.y = -gp.tileSize;
                Enemy.remove(i);
            }
        }
    }

    @Override
    public boolean deathStatus() {
        return isDead;
    }

    @Override
    public void setDead() {
        isDead = true;
    }
}
