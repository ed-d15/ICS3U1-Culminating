import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;

/**
 * BejeweledListener class 
 * This class implements the MouseListener class
 * It detects the mouse clicks on the game board and calls the play method accordingly.
 */
public class BejeweledListener implements MouseListener
{
   private BejeweledGUI gui;
   private Bejeweled game;
      
   public BejeweledListener (Bejeweled game, BejeweledGUI gui) {
      this.game = game;
      this.gui = gui;
      gui.addListener (this);
   }
   
   public void mouseClicked (MouseEvent event) {
      JLabel label = (JLabel) event.getComponent();		
      int row = gui.getRow(label);
      int column = gui.getColumn (label);
      game.play(row, column); 
   }
   
   public void mousePressed (MouseEvent event) {
   }
   
   public void mouseReleased (MouseEvent event) {
   }
   
   public void mouseEntered (MouseEvent event) {
   }
   
   public void mouseExited (MouseEvent event) {
   }
}

/**
 * ButtonListener class 
 * This class implements the ActionListener class
 * It detects the actions on the buttons (save game, load game and exit) and calls 
 * the corresponding methods.
 */
class ButtonListener implements ActionListener {
   final String SAVEGAMEBUTTON = "Save Game";
   final String LOADGAMEBUTTON = "Load Game";
   final String EXITBUTTON = "End Game";
   
   private BejeweledGUI gui;
   private Bejeweled game;
   
   public ButtonListener (Bejeweled game, BejeweledGUI gui) {
      this.game = game;
      this.gui = gui;
      gui.addListener (this);
   }

   public void actionPerformed(ActionEvent e) {
      // detect which button is clicked
      String button = e.getActionCommand();
      
      if (button.equals(EXITBUTTON)) {
         game.endGame();
      } else {
         // if save or load game, prompt user for the file name
         String fileName = JOptionPane.showInputDialog(null, "File Name: ", "File Name", JOptionPane.QUESTION_MESSAGE);   
         if (fileName != null) {
            if (button.equals(SAVEGAMEBUTTON)) {
               if (!game.saveToFile(fileName)) {
                  JOptionPane.showMessageDialog(null, "Problem Saving Game!", "Error", JOptionPane.PLAIN_MESSAGE, null); 
               }
            } else if (button.equals(LOADGAMEBUTTON)) {
               if (!game.loadFromFile(fileName)) {
                  JOptionPane.showMessageDialog(null, "Problem Loading Game!", "Error", JOptionPane.PLAIN_MESSAGE, null);           
               } else {
               // update the game board from the loaded game
                  game.updateGameBoard();
               }
            }
         }
      }
   }
}
