/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author fauza
 */
public class menu extends JPanel implements frame {
    private int screenWidth = commons.SCR_COLUMN.value *
                              commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    private int screenHeight = commons.SCR_ROW.value *
                              commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    private int tileSize = commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    public int cmdNum = 0;
    
    public void draw(Graphics2D g){
        InputStream iS = getClass().getResourceAsStream("/media/ArcadeAlternate.ttf");
        Font arcade = null;
        try {
            arcade = Font.createFont(Font.TRUETYPE_FONT, iS);
        } catch (FontFormatException ex) {
            Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g.setColor(Color.red);
        g.setFont(arcade.deriveFont(Font.PLAIN, 45f));
        String str = "SPACE DEFENDER";
        int y = screenHeight/2 - 3*tileSize;
        g.drawString(str, getXCenteredStringPos(g, str)+4, y+4);

        g.setColor(Color.YELLOW);
        g.setFont(arcade.deriveFont(Font.PLAIN, 45f));
        str = "SPACE DEFENDER";
        g.drawString(str, getXCenteredStringPos(g, str), y);
        
        
        g.setColor(Color.WHITE);
        g.setFont(arcade.deriveFont(Font.PLAIN, 35f));
        str = "PLAY";
        int x = getXCenteredStringPos(g, str);
        y += 3*tileSize;
        g.drawString(str, x, y);
        if(cmdNum == 0){
            g.setColor(new Color(228, 238, 141));
            g.drawString(str, x, y);
            g.drawString("->", x-tileSize, y);
        }
        
        g.setColor(Color.WHITE);
        str = "RANKS";
        x = getXCenteredStringPos(g, str);
        y += tileSize;
        g.drawString(str, x, y);
        if(cmdNum == 1){
            g.setColor(new Color(228, 238, 141));
            g.drawString(str, x, y);
            g.drawString("->", x-tileSize, y);
        }
        
        g.setColor(Color.WHITE);
        str = "QUIT";
        x = getXCenteredStringPos(g, str);
        y += tileSize;
        g.drawString(str, x, y);
        if(cmdNum == 2){
            g.setColor(new Color(228, 238, 141));
            g.drawString(str, x, y);
            g.drawString("->", x-tileSize, y);
        }
    }
    
    @Override
    public int getXCenteredStringPos(Graphics2D g, String str){
        int length = (int)g.getFontMetrics().getStringBounds(str, g).getWidth();
        return (screenWidth/2 - length/2);        
    }

    @Override
    public void initFrame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
