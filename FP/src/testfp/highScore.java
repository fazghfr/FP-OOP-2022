/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfp;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author fauza
 */
public class highScore extends JPanel implements frame{
    private int screenWidth = commons.SCR_COLUMN.value *
                              commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    private int screenHeight = commons.SCR_ROW.value *
                              commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    private int tileSize = commons._SCALE.value *
                              commons.ORI_TILESIZE.value;
    public JFrame gp;
    public Score champions;
    
    public highScore(JFrame gp){
        this.gp = gp;
    }
    
    public void draw(Graphics2D g) throws IOException{
        InputStream iS = getClass().getResourceAsStream("/media/ArcadeAlternate.ttf");
        Font arcade = null;
        try {
            arcade = Font.createFont(Font.TRUETYPE_FONT, iS);
        } catch (FontFormatException ex) {
            Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gamepanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g.setColor(Color.YELLOW);
        g.setFont(arcade.deriveFont(Font.PLAIN, 45f));
        String str = "SPACE";
        int y = screenHeight/2 - 5*tileSize;
        int x = getXCenteredStringPos(g, str);
        g.drawString(str,x , y);
        str = "CHAMPIONS";
        g.drawString(str, x-tileSize-4 , y + tileSize);
        
        
        //list of player top scores top 5
        ArrayList<Score> scores = new ArrayList<>();
        scores = readTopFive();
        
        if(scores.size()!=0){
            int size = scores.size();
            if(size > 5) size = 5;
            str = scores.get(0).playerName;
            int v = scores.get(0).score;
            x = getXCenteredStringPos(g, str) - tileSize + 8; y = screenHeight/2 - 2*tileSize;


            //drawing players
            g.setColor(Color.WHITE);
            g.setFont(arcade.deriveFont(Font.BOLD, 30f));
            g.drawString(str +"  " +v, x, y);

            int fontsize = (int) 30f - 1;
            g.setFont(arcade.deriveFont(Font.PLAIN, fontsize));
            for(int i = 1; i< size ;i++){
                str = scores.get(i).playerName;
                v = scores.get(i).score;
                y += tileSize;
                g.setFont(arcade.deriveFont(Font.PLAIN, --fontsize));
                g.drawString(str + "  " +v, x, y);
            }

            str = "click backspace to go back";
            g.setFont(arcade.deriveFont(Font.PLAIN, 18f));
            g.drawString(str, getXCenteredStringPos(g, str) , screenHeight - 3*tileSize);
        }
    }
    
    public static void storeScore(String playerName, int score) throws IOException {
        // Create a new FileWriter object with the file name "scores.txt"
        FileWriter writer = null;
        try{
            writer = new FileWriter("scores.txt", true);
        }catch(IOException e){
            System.out.println("something is wrong.");
        }

        // Write the player name, current time, and score to the file, separated by a comma and a space
        writer.write(playerName  + ", " + score + "\n");

        // Close the FileWriter
        writer.close();
    }
    
    public Score getChamp() throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader("scores.txt")); // create a BufferedReader to read the file
        String line = reader.readLine();
        int counter = 0;
         
        String[] parts = line.split(" "); // split jadi bagian bagian datannya
        String playerName = parts[0]; // nama pl
        int score = Integer.parseInt(parts[1]); // skor pl
        Score temp = new Score(playerName, score);
        
        return temp;
    }
    
    class Generic<T> {

        // variable of T type
        private T data;

        public Generic(T data) {
          this.data = data;
        }

        // method that return T type variable
        public T getData() {
          return this.data;
        }
      }
    
    public Score readTopMost() throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader("scores.txt")); // create a BufferedReader to read the file
        String line, playerName = null, sInt = null;
        int counter = 0;
        while ((line = reader.readLine()) != null && counter!=1) { 
          String[] parts = line.split(" "); // split jadi bagian bagian datannya
          playerName = parts[0]; // nama pl
          sInt = parts[1];
          counter++;
        }
        
        reader.close(); // close the reader to finish reading the file
        Generic<String> stringGen = new Generic<String>(playerName);
        Generic<Integer> intGen = new Generic<Integer>(Integer.parseInt(sInt));
        
        return new Score(stringGen.getData(), intGen.getData());
    }
    
    public ArrayList<Score> readTopFive() throws FileNotFoundException, IOException{
        ArrayList<Score> scores = new ArrayList<>(); // create an ArrayList to store the scores
        BufferedReader reader = new BufferedReader(new FileReader("scores.txt")); // create a BufferedReader to read the file
        String line;
        int counter = 0;
        while ((line = reader.readLine()) != null && counter!=5) { 
          if(counter!=0){
            String[] parts = line.split(" "); // split jadi bagian bagian datannya
            String playerName = parts[0]; // nama pl
            int score = Integer.parseInt(parts[1]); // skor pl
            scores.add(new Score(playerName, score)); // masukkan ke dalam arrList
          }else{
            scores.add(readTopMost());
          }
          counter++;
        }
        reader.close(); // close the reader to finish reading the file
        
        return scores;
    }
    
    public void sortScores() throws IOException {
        ArrayList<Score> scores = new ArrayList<>(); // create an ArrayList to store the scores
        BufferedReader reader = new BufferedReader(new FileReader("scores.txt")); // create a BufferedReader to read the file
        String line;
        while ((line = reader.readLine()) != null) { 
          String[] parts = line.split(" "); // split jadi bagian bagian datannya
          String playerName = parts[0]; // nama pl
          int score = Integer.parseInt(parts[1]); // skor pl
          scores.add(new Score(playerName, score)); // masukkan ke dalam arrList
        }
        reader.close(); // close the reader to finish reading the file

        // sort the scores by descending order of score
        Collections.sort(scores, new Comparator<Score>() {
          public int compare(Score s1, Score s2) {
            return Integer.compare(s2.score, s1.score); // compare the scores in descending order
          }
        });

        PrintWriter writer = new PrintWriter("scores.txt", "UTF-8"); // create a PrintWriter to write to the file
        for (Score score : scores) { // write each score to the file
          writer.println(score.playerName + " " + score.score);
        }
        writer.close(); // close the writer to save the file
      }
   

    @Override
    public void initFrame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getXCenteredStringPos(Graphics2D g, String str){
        int length = (int)g.getFontMetrics().getStringBounds(str, g).getWidth();
        return (screenWidth/2 - length/2);        
    }
    
    public int getYCenteredStringPos(Graphics2D g, String str){
        int height = (int)g.getFontMetrics().getStringBounds(str, g).getHeight();
        return (screenHeight/2 - height/2);        
    }
    
    public class Score {
        public String playerName;
        public int score;

        public Score(String playerName, int score) {
          this.playerName = playerName;
          this.score = score;
        }
      }
}
