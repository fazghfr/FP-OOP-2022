/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

/**
 *
 * @author fauza
 */
public enum commons {
    ORI_TILESIZE(16),
    SCR_COLUMN(9),
    SCR_ROW(16),
    PLAYER_SPEED(4),
    BULLET_SPEED(8),
    BULLET_MAXSPEED(19),
    ENEMY_SPEED(1),
    ENEMY_MAXSPEED(4),
    GAME_MAXLEVEL(15),
    INIT_LEVEL(0),
     _FPS(120), 
    _SCALE(3);
    
    
    public final int value;
    commons(int value){
        this.value = value;
    }
}
