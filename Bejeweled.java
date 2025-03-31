/**
	File Name: Bejeweled.java
	Name: --REDACTED--
	Class: ICS3U1 - 12
	Date: January 21, 2024
	Description: This program reads the input of the user and does all the coding for
   the the bejeweled game.
* Bejeweled.java (Skeleton)
*
* This class represents a Bejeweled (TM)
* game, which allows player to make moves
* by swapping two pieces. Chains formed after
* valid moves disappears and the pieces on top
* fall to fill in the gap, and new random pieces
* fill in the empty slots.  Game ends after a
* certain number of moves or player chooses to 
* end the game.
*/

import java.awt.Color;
import java.io.*;

public class Bejeweled {

   /* 
	 * Constants
	 */  
   // colours used to mark the selected piece and 
   // the pieces in the chain to be deleted
   final Color COLOUR_DELETE = Color.RED;
   final Color COLOUR_SELECT = Color.YELLOW;
   
   BejeweledGUI gui;	// the object referring to the GUI, use it when calling methods to update the GUI

   
   // declare all constants here
   final int NUM_COLUMN = 8;
   final int NUM_ROW = 8;
   final int NUMPIECESTYLE = 7;
   final int EMPTY = -1;
   final int CHAIN_REQ = 3;
   final String GAMEFILEFOLDER = "gamefiles";
   final int NUMMOVE = 10;
   
   // declare all "global" variables here
   int [][] board;
   int score;
   int numMoveLeft;
   int slot1Row, slot1Col;
   boolean firstSelection;
	      
   public Bejeweled(BejeweledGUI gui) {
      this.gui = gui;      
      start();   
   }
   
//swapPiecesGUI method - swap pieces and gui
   public void swapPiecesGUI (int row1, int column1, int row2, int column2) {
   
   //Finds piece number for each slot
      int piece1 = board[row1][column1];
      int piece2 = board[row2][column2];
      
      //Sets new pieces for each number
      gui.setPiece(row1,column1,piece2);
      gui.setPiece(row2,column2,piece1);
      
   }

/*countLeft method - counts the pieces left which are the same as the selected piece
 not including itself */
   public int countLeft (int row, int column) {
   
   //Sets variables
      boolean state = true;
      int count = 0;
      
      //Loops through the array to check if the row and column are equal
      for (int i = column - 1; i >= 0 && state; i--) {
      
      //If piece is the same as the new piece adds to count
         if (board[row][column] == board[row][i]) {
            count++;
         }
         //If not turns state to false which ends for loop
         else {
            state = false;
         }
      }
      //Returns count
      return count;
   }

/*countRight method - counts the pieces right which are the same as the selected piece
 not including itself */
   public int countRight (int row, int column) {
   //Declares count and state
      boolean state = true;
      int count = 0;
      //Loops through the array to check if the row and column are equal
      for (int i = column + 1; i < NUM_COLUMN && state; i++) {
      //If peices are the same add to count
         if (board[row][column] == board[row][i]) {
            count++;
         }
         //If not ends the for loop state
         else {
            state = false;
         }   
      }
      //Returns count
      return count;
   }
   
/*countUp method - counts the pieces above which are the same as the selected piece
 not including itself */
   public int countUp (int row, int column) {
      int count = 0;
      boolean state = true;
      
      //Loops through the array to check if the row and column are equal
      for (int i = row - 1; i >= 0 && state; i--) {
      //If pieces are the same add to count
         if (board[row][column] == board[i][column]) {
            count++;
         }
         //If not the same ends the for loop state
         else {
            state = false;
         } 
      }
      //Returns count
      return count;
   }
   
/*countDown method - counts the pieces down which are the same as the selected piece
 not including itself */
   public int countDown (int row, int column) {
   //Declares variables
      boolean state = true;
      int count = 0;
      
      //Loops through the array to check if the row and column are equal
      for (int i = row + 1; i < NUM_ROW && state; i++) {
      //Checks if condition if the same piece
         if (board[row][column] == board[i][column]) {
            count++;
         }
         //If not for loop will end
         else {
            state = false;
         } 
      }
      //Returns count
      return count;
   }
   
   //Swap pieces method - swap the two pieces value selected
   public void swapPieces (int row1, int column1, int row2, int column2) {
      int temp = board[row1][column1];
      board[row1][column1] = board[row2][column2];
      board[row2][column2] = temp;
   } 

//initBoard method - randomizes board and graphically sets up board
   public void initBoard() {
   //Creates board array
      board = new int [NUM_ROW][NUM_COLUMN];
      
   //Loops throughout the board array
      for (int r = 0; r < NUM_ROW; r++) {
         for (int c = 0; c < NUM_COLUMN; c++) {      
         //Randomizes number and puts it into an array
            int random = (int)(Math.random()*NUMPIECESTYLE);
            board[r][c] = random;
            
            //Sets piece method
            gui.setPiece(r,c,random);
         }
      }
      //Sets score and moves
      gui.setMoveLeft(numMoveLeft);
      gui.setScore(score);
   }
   
   /*markDeletePieceLeft method - Loops through pieces left which highlights
    the piece red and puts value as -1 */
   public void markDeletePieceLeft (int row, int column, int numPieces) {
      for (int i = 1; i <= numPieces; i++) {
         gui.highlightSlot(row,column-i,COLOUR_DELETE);
         board[row][column-i] = EMPTY;
      }
   }
   
  /*markDeletePieceRight method - Loops through pieces right which highlights
    the piece red and puts value as -1 */
   public void markDeletePieceRight (int row, int column, int numPieces) {
      for (int i = 1; i <= numPieces; i++) {
         gui.highlightSlot(row,column+i,COLOUR_DELETE);
         board[row][column+i] = EMPTY;
      }
   }
   
   /*markDeletePieceUp method- Loops through pieces up which highlights
    the piece red and puts value as -1 */
   public void markDeletePieceUp (int row, int column, int numPieces) {
      for (int i = 1; i <= numPieces; i++) {
         gui.highlightSlot(row-i,column,COLOUR_DELETE);
         board[row-i][column] = EMPTY;
      }
   }
   
   /*markDeletePieceDown method- Loops through pieces down which highlights
    the piece red and puts value as -1 */
   public void markDeletePieceDown (int row, int column, int numPieces) {
      for (int i = 1; i <= numPieces; i++) {
         gui.highlightSlot(row+i,column,COLOUR_DELETE);
         board[row+i][column] = EMPTY;
      }
   }
   
   //markDeletePiece method - highlights the piece red and puts value as -1
   public void markDeletePiece (int row, int column) {
      gui.highlightSlot(row,column,COLOUR_DELETE);
      board[row][column] = EMPTY;
   }
   
   //updateBoard method - Shift the pieces down and randomize the top piece
   public void updateBoard () {
   
   //Goes through board to find empty piece
      for (int r = 0; r < NUM_ROW; r++) {
         for (int c = 0; c < NUM_COLUMN; c++) {
            if (board[r][c] == EMPTY) {
               
               //Shifts the pieces down
               for (int i = r; i > 0; i--) {
                  board[i][c] = board[i-1][c];
               }  
               
               //Randomizes the first piece
               board[0][c] = (int)(Math.random()*NUMPIECESTYLE);
            }
         }
      }
   }
   
//adjacentSlots method - Checks if the two pieces are adjacent
   public boolean adjacentSlots (int rows1,int columns1,int rows2, int columns2) {
   
   //Returns true is peices are adjacents if not return false
      boolean check = false;
      
      //If pieces are beside each other 
      if (((rows1 + 1 == rows2 || rows1 - 1 == rows2) && columns1 == columns2) || ((columns1 + 1 == columns2 || columns1 - 1 == columns2) && rows1 == rows2)) {
         check = true;
      }
      return check;
   }
   
   //Start method - The code when the program runs
   public void start() {
   
   //Initializes variables
      firstSelection = true;
      score = 0;
      numMoveLeft = NUMMOVE;
   
   //Starts innit board
      initBoard();
   }

/*play method - when user clicks on peices the code belows runs which detect chains
highlights pieces, use the GUI methods, board methods and ends game */
   public void play (int row, int column) {
   
   //Declares chain amount for first piece
      int chain_up1;
      int chain_down1;
      int chain_left1;
      int chain_right1;
   
   //Chain amount for second piece
      int chain_up2;
      int chain_down2;
      int chain_left2;
      int chain_right2;  
      
      int chain_total = 0;
      
      //Boolean for adjacent and if a chain is formed
      boolean chain_formed = false;
      boolean adjacent = false;
   
   //Checks if either first sleection or second selection
      if (firstSelection) {
      //Highlights slot
         gui.highlightSlot(row,column,COLOUR_SELECT);
         
         //Puts variable into a temporary and changes firstSelection
         slot1Row = row;
         slot1Col = column;
         firstSelection = false;
      }
      
      //If firstSelection is false
      else {
      //Higlights Slot
         gui.highlightSlot(row,column,COLOUR_SELECT);
      
      //Checks if the move is correct or not
         adjacent = adjacentSlots(row,column,slot1Row,slot1Col);
      
      //If adjacent
         if (adjacent) {
         
            //Swap pieces orginally
            swapPiecesGUI(row,column,slot1Row,slot1Col);
            swapPieces(row,column,slot1Row,slot1Col);
            
            //Finds the chain for the first postion / second peice
            chain_left1 = countLeft(slot1Row,slot1Col);
            chain_right1 = countRight(slot1Row,slot1Col);
            chain_up1 = countUp(slot1Row,slot1Col);
            chain_down1 = countDown(slot1Row,slot1Col);
               
            //Finds chain for second position / first piece                 
            chain_left2 = countLeft(row,column);
            chain_right2 = countRight(row,column);
            chain_up2 = countUp(row,column);
            chain_down2 = countDown(row,column);
         
         //Checks first peice left and right for chain
            if (chain_left1 + chain_right1 + 1 >= CHAIN_REQ) {
            
            //Delete pieces
               markDeletePieceLeft(slot1Row,slot1Col,chain_left1);
               markDeletePieceRight(slot1Row,slot1Col,chain_right1);
               markDeletePiece(slot1Row,slot1Col);
            //Adds to score and puts chain formed as true
               chain_formed = true;
            }
            
            //Checks Vertical chain for first piece
            if (chain_up1 + chain_down1 + 1 >= CHAIN_REQ) {
               markDeletePieceUp(slot1Row,slot1Col,chain_up1);
               markDeletePieceDown(slot1Row,slot1Col,chain_down1);
               markDeletePiece(slot1Row,slot1Col);
               chain_formed = true;
            }
            
            //Checks for horizontal chain for second peice
            if (chain_left2 + chain_right2 + 1 >= CHAIN_REQ) {
               markDeletePieceLeft(row,column,chain_left2);
               markDeletePieceRight(row,column,chain_right2);
               markDeletePiece(row,column);
               chain_formed = true;
            }
            
            //Checks horizontal chain for second piece
            if (chain_up2 + chain_down2 + 1 >= CHAIN_REQ) {
               markDeletePieceUp(row,column,chain_up2);
               markDeletePieceDown(row,column,chain_down2);
               markDeletePiece(row,column);
               chain_formed = true; 
            }
               
               //If any chain is formed
            if (chain_formed) {     
               //Goes through for loop to find empty peices
               for (int r = 0; r < NUM_ROW; r++) {
                  for (int c = 0; c < NUM_COLUMN; c++) {
                     if (board[r][c] == EMPTY) {
                     //Adds to the chain_total
                        chain_total++;
                     }
                  }
               }
               
               //Shows chain message
               gui.showChainSizeMessage(chain_total);
               
               //Adds to score and removes move
               score = score + chain_total;
               numMoveLeft--;
               
               //Updates board and graphically update board
               updateBoard();
               updateGameBoard();
            }
            
            //If no chain is formed
            else {
            //Swap pieces back and shows error message
               swapPiecesGUI(row,column,slot1Row,slot1Col);
               swapPieces(row,column,slot1Row,slot1Col);
               
               //Shows error
               gui.showInvalidMoveMessage();
            }
         }
         
         //If move is not valid 
         else { 
         //Shows error message
            gui.showInvalidMoveMessage();
         }
         
         //Unhilights pieces
         gui.unhighlightSlot(row,column);
         gui.unhighlightSlot(slot1Row,slot1Col);
         
         //Changes first selection to true
         firstSelection = true;   
      }
      
      //If user runs out of moves
      if (numMoveLeft < 1) {
      //End games
         endGame();
      }
   }

//saveToFile method - if sucessful returns true if unsuccessful returns true
   public boolean saveToFile (String fileName) {
      boolean state = true;
      try {
         BufferedWriter out = new BufferedWriter (new FileWriter(GAMEFILEFOLDER + "/" + fileName + ".txt"));
         
         //Prints score and number of moves left
         out.write(score + "");
         out.newLine();
         out.write(numMoveLeft + "");
         out.newLine();
         
         //Goes through array and prints out
         for (int r = 0; r < NUM_ROW; r++) {
            for (int c = 0; c < NUM_COLUMN; c++) {
               out.write(board[r][c] + " ");
            } 
            //New line for every row
            out.newLine();
         }
         //Closes writer
         out.close();
      }
      
      //If error turns state to false
      catch (IOException iox) {
         state = false;
      } 
      return state;
   }

/*loadFromFile method - a game from a text file, setting the score,
 number of moves left and board */
   public boolean loadFromFile(String fileName) {
      String temp_string [];
      boolean state = true;
      try {
      //Creates BufferedReader and FileReader
         BufferedReader in = new BufferedReader (new FileReader(GAMEFILEFOLDER + "/" + fileName + ".txt"));
      
      //Sets score and num move left
         score = Integer.parseInt(in.readLine());
         numMoveLeft = Integer.parseInt(in.readLine());
      
      //Goes through the text file array
         for (int r = 0; r < NUM_ROW; r++) {
         //Splits the line into a temporary array
            temp_string = in.readLine().split(" ");
            for (int c = 0; c < NUM_COLUMN; c++) {
               board[r][c] = Integer.parseInt(temp_string[c]);
            }
         }
         //Closes reader
         in.close();
         updateGameBoard();
      }
      //If reading is unsucessful puts state to false
      catch (IOException iox) {
         state = false;
      }
      //Returns state
      return state;
   }

//updateGameBoard - updates game board graphically depending on the pieces value
   public void updateGameBoard() {
   
   //Goes through the board to set piece depending on number and unhighlights
      for (int r = 0; r < NUM_ROW; r++) {
         for (int c = 0; c < NUM_COLUMN; c++) {
            gui.unhighlightSlot(r,c);
            gui.setPiece(r,c,board[r][c]);
         }
      }
      
      //Updates moves and score
      gui.setMoveLeft(numMoveLeft);
      gui.setScore(score);
      
   }

//endGame method - shows the end of game once user clicks
   public void endGame() {
      gui.showGameOverMessage(score,numMoveLeft);
   }

}