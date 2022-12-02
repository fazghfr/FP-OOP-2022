/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
        BufferedImage img = null;
        try {
            idle1 = ImageIO.read(getClass().getResourceAsStream("/media/shipidle.png"));
        } catch (IOException ex) {
            Logger.getLogger(bullet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
