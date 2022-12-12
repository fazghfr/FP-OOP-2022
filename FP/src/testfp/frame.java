/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package testfp;

import java.awt.Graphics2D;

/**
 *
 * @author fauza
 */
public interface frame {
    int screenWidth = commons.SCR_COLUMN.value *
                              commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    int screenHeight = commons.SCR_ROW.value *
                              commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    int tileSize = commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    
    void initFrame();
    int getXCenteredStringPos(Graphics2D g, String str);
}
