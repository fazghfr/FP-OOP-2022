/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author fauza
 */
public class Sprite{
    public int x,y;
    public int speed;
    public BufferedImage left1, idle1, right1, left2, idle2, right2;
    public String direction;

    private boolean isDead = false;
    
    public int spriteCounter = 0;
    public int spriteNumber;

    public boolean deathStatus(){
        return isDead;
    }

    public void setDead(){
        this.isDead = true;
    }
}