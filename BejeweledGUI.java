/**
 * BejeweledGUI.java (Skeleton)
 * Provide the GUI for the Bejeweled game
 */
import javax.swing.*;
import javax.swing.JComponent;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

public class BejeweledGUI {
	// the name of the configuration file
   private final String CONFIGFILE = "config.txt";
   private final Color BACKGROUNDCOLOUR = new Color(100, 100, 100);
	
   private JLabel[][] slots;
   private JFrame mainFrame;
   private ImageIcon[] pieceIcon;
   private JButton saveGameButton;
   private JButton loadGameButton;
   private JButton endGameButton;
   private JTextField score;
   private JTextField numMoveLeft;
	
   private String logoIcon;
   private String[] iconFile;
   
   private Color background = new Color(100, 100, 100);
	      
/**
* Number of different piece styles
*/
   public final int NUMPIECESTYLE = 7;

/**
* Number of rows on the game board
*/
   public final int NUMROW = 8;

/**
* Number of colums on the game board
*/
   public final int NUMCOL = 8;

/**
* Constants defining the demensions of the different components
* on the GUI
*/    
   private final int PIECESIZE = 70;
   private final int PLAYPANEWIDTH = NUMCOL * PIECESIZE;
   private final int PLAYPANEHEIGHT = NUMROW * PIECESIZE;

   private final int INFOPANEWIDTH = 2 * PIECESIZE;
   private final int INFOPANEHEIGHT = PLAYPANEHEIGHT;

   private final int LOGOHEIGHT = 2 * PIECESIZE;
   private final int LOGOWIDTH = PLAYPANEWIDTH + INFOPANEWIDTH;

   private final int FRAMEWIDTH = (int)(LOGOWIDTH * 1.03);
   private final int FRAMEHEIGHT = (int)((LOGOHEIGHT + PLAYPANEHEIGHT) * 1.1);

  /**
  * initializes variables from config files
  * initializes the imageIcon array
  * initializes the slots array
  * create the main frame
  * 
  */
   public BejeweledGUI () {
      initConfig();
      initImageIcon();
      initSlots();
      createMainFrame();
   }

  /**
  * initializes the content of logoIcon (String) and iconFile (array of String)
  * with the information in the file specified in the constant CONFIGFILE 
  */
   private void initConfig() {
   iconFile = new String[NUMPIECESTYLE];
       	try {
         BufferedReader in = new BufferedReader(new FileReader(CONFIGFILE));
         
         //Reads logo in txt file
         logoIcon = in.readLine();
         
         //Read pieces in txt file
         for (int i = 0; i < NUMPIECESTYLE; i++) {
         iconFile[i] = in.readLine();
         }
         
         //Closes BufferedReader & FileReader
         in.close();
         
         }
         
         //   
         catch (IOException iox) {
         System.out.print("Error reading text file");
         }
   }

  /**
  * Initialize pieceIcon arrays with graphic files
  */
   private void initImageIcon() {
      pieceIcon = new ImageIcon[NUMPIECESTYLE];
      for (int i = 0; i < NUMPIECESTYLE; i++) {
         pieceIcon[i] = new ImageIcon(iconFile[i]);
      }
   }

  /**
  * Initialize the array of JLabels
  */
   private void initSlots() {
      slots = new JLabel[NUMROW][NUMCOL];
      for (int i = 0; i < NUMROW; i++) {
         for (int j = 0; j < NUMCOL; j++) {
            slots [i] [j] = new JLabel ();
         // slots[i][j].setFont(new Font("SansSerif", Font.BOLD, 18));
            slots[i][j].setPreferredSize(new Dimension(PIECESIZE, PIECESIZE));
            slots [i] [j].setHorizontalAlignment (SwingConstants.CENTER);      
         }
      }
   }

  /**
  * Create the panel for game board (grid of pieces)
  */
   private JPanel createPlayPanel() {
      JPanel panel = new JPanel(); 
      panel.setPreferredSize(new Dimension(PLAYPANEWIDTH, PLAYPANEHEIGHT));
      panel.setBackground(BACKGROUNDCOLOUR);
      panel.setLayout(new GridLayout(NUMROW, NUMCOL));
      for (int i = 0; i < NUMROW; i++) {
         for (int j = 0; j < NUMCOL; j++) {
            panel.add(slots[i][j]);
         }
      }
      return panel;    
   }

  /**
  * Create the panel for information (score board, navagation buttons)
  */
   private JPanel createInfoPanel() {
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(INFOPANEWIDTH, INFOPANEHEIGHT));
      panel.setBackground (BACKGROUNDCOLOUR);
      panel.setBorder (new LineBorder (Color.white)); 
   
      Font headingFont = new Font ("Serif", Font.PLAIN, 24);
      Font regularFont = new Font ("Serif", Font.BOLD, 16);
   
      // Create a panel for the scoreboard
      JPanel scorePanel = new JPanel();
      scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
      scorePanel.setBackground(BACKGROUNDCOLOUR);
   
      // Create the label to display "Score" heading
      JLabel scoreLabel = new JLabel ("     Score     ", JLabel.CENTER);
      scoreLabel.setFont(headingFont);
      scoreLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
   //nextLabel.setForeground(Color.white);
   
      score = new JTextField();
      score.setFont(regularFont);
      score.setText("0");
      score.setEditable(false);
      score.setHorizontalAlignment (JTextField.CENTER);
      score.setBackground(BACKGROUNDCOLOUR);
      
      scorePanel.add(scoreLabel);
      scorePanel.add(score);
   
      JPanel moveLeftPanel = new JPanel();
      moveLeftPanel.setLayout(new BoxLayout(moveLeftPanel, BoxLayout.Y_AXIS));
      moveLeftPanel.setBackground(BACKGROUNDCOLOUR);
   
      // Create the label to display "Moves Left" heading
      JLabel moveLeftLabel = new JLabel ("Moves Left", JLabel.CENTER);
      moveLeftLabel.setFont(headingFont);
      moveLeftLabel.setAlignmentX (Component.CENTER_ALIGNMENT);
   
      numMoveLeft = new JTextField();
      numMoveLeft.setFont(regularFont);
      numMoveLeft.setText("0");
      numMoveLeft.setEditable(false);
      numMoveLeft.setHorizontalAlignment (JTextField.CENTER);
      numMoveLeft.setBackground(BACKGROUNDCOLOUR);
      
      JLabel emptyLabel1 = new JLabel (" ", JLabel.CENTER);
      emptyLabel1.setFont(headingFont);
      emptyLabel1.setAlignmentX (Component.CENTER_ALIGNMENT); 
          
      JLabel emptyLabel2 = new JLabel (" ", JLabel.CENTER);
      emptyLabel2.setFont(headingFont);
      emptyLabel2.setAlignmentX (Component.CENTER_ALIGNMENT);
   
      moveLeftPanel.add(emptyLabel1);
      moveLeftPanel.add(moveLeftLabel);
      moveLeftPanel.add(numMoveLeft);
      moveLeftPanel.add(emptyLabel2);
      
   	// panel for the buttons
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
      buttonPanel.setBackground(background);
   
      // button for save game
      saveGameButton = new JButton("Save Game");
      saveGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
      saveGameButton.setFont(regularFont);
                           
      // button for load game
      loadGameButton = new JButton("Load Game");
      loadGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
      loadGameButton.setFont(regularFont);
   
      // button for End game
      endGameButton = new JButton("End Game");
      endGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
      endGameButton.setFont(regularFont);
   
      buttonPanel.add(saveGameButton);
      buttonPanel.add(Box.createRigidArea(new Dimension(0, 25)));
      buttonPanel.add(loadGameButton);
      buttonPanel.add(Box.createRigidArea(new Dimension(0, 75)));
      buttonPanel.add(endGameButton);
      buttonPanel.add(Box.createRigidArea(new Dimension(0, 25)));
   
      panel.add(scorePanel);
      panel.add(moveLeftPanel);
      panel.add(buttonPanel);
   	    
      return panel;
   }

  /**
  * Create the main frame to hold the logo, play panel and info panel
  */
   private void createMainFrame() {
   
      // Create the main Frame
      mainFrame = new JFrame ("Bejeweled");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel = (JPanel)mainFrame.getContentPane();
      panel.setLayout (new BoxLayout(panel,BoxLayout.Y_AXIS));
   
      // Create the panel for the logo
      JPanel logoPane = new JPanel();
      logoPane.setPreferredSize(new Dimension (LOGOWIDTH, LOGOHEIGHT));
      logoPane.setBackground (BACKGROUNDCOLOUR);
      
      JLabel logo = new JLabel();
      logo.setIcon(new ImageIcon(logoIcon));
      logoPane.add(logo);
   
      // Create the bottom Panel which contains the play panel and info Panel
      JPanel bottomPane = new JPanel();
      bottomPane.setLayout(new BoxLayout(bottomPane,BoxLayout.X_AXIS));
      bottomPane.setPreferredSize(new Dimension(PLAYPANEWIDTH + INFOPANEWIDTH, PLAYPANEHEIGHT));
      bottomPane.add(createPlayPanel());
      bottomPane.add(createInfoPanel());
   
      // Add the logo and bottom panel to the main frame
      panel.add(logoPane);
      panel.add(bottomPane);
   
      mainFrame.setContentPane(panel);
   //   mainFrame.setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHEIGHT));
      mainFrame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
      mainFrame.setVisible(true);
   }

   /**
   * Returns the column number of where the given JLabel is on
   * 
   * @param  label the label whose column number to be requested
   * @return the column number
   */
   public int getRow(JLabel label) {
      int result = -1;
      for (int i = 0; i < NUMROW && result == -1; i++) {
         for (int j = 0; j < NUMCOL && result == -1; j++) {
            if (slots[i][j] == label) {
               result = i;
            }
         }
      }
      return result;
   }

   /**
   * Returns the column number of where the given JLabel is on
   * 
   * @param  label the label whose column number to be requested
   * @return the column number
   */
   public int getColumn(JLabel label) {
      int result = -1;
      for (int i = 0; i < NUMROW && result == -1; i++) {
         for (int j = 0; j < NUMCOL && result == -1; j++) {
            if (slots[i][j] == label) {
               result = j;
            }
         }
      }
      return result;
   }

   /**
   * Add listener for each slot on the game board
   * 
   */
   public void addListener (BejeweledListener listener) {
      for (int i = 0; i < NUMROW; i++) {
         for (int j = 0; j < NUMCOL; j++) {
            slots [i] [j].addMouseListener (listener);
         }
      }
   }
   

   /**
   * Add listener to all the buttons   
   */
   public void addListener(ButtonListener listener) {
      saveGameButton.addActionListener(listener);
      loadGameButton.addActionListener(listener);
      endGameButton.addActionListener(listener);
   }


   /**
   * Display the specified player icon on the specified slot
   * 
   * @param row row of the slot
   * @param col column of the slot
   * @param piece index of the piece to be displayed
   */
   public void setPiece(int row, int col, int piece) {
      slots[row][col].setIcon(pieceIcon[piece]);
   }

   /**
   * Highlight the specified slot with the specified colour
   * 
   * @param row row of the slot
   * @param col column of the slot
   * @param colour colour used to highlight the slot
   */
   public void highlightSlot(int row, int col, Color colour) {
      slots[row][col].setBorder (new LineBorder (colour));   
   }

   /**
   * Unhighlight the specified slot to the default grid colour
   * 
   * @param row row of the slot
   * @param col column of the slot
   */
   public void unhighlightSlot(int row, int col) {
      slots[row][col].setBorder (new LineBorder (BACKGROUNDCOLOUR));   
   }

   /**
   * Display the score on the corresponding textfield
   * 
   * @param point the score to be displayed
   */
   public void setScore(int point) {
      score.setText(point+"");
   }
	
   /**
   * Display the number of moves left on the corresponding textfield
   * 
   * @param num number of moves left to be displayed
   */
   public void setMoveLeft(int num) {
      numMoveLeft.setText(num+"");
   }	
  
   /**
   * Display a pop up window displaying the message about invalid move
   * 
   */
   public void showInvalidMoveMessage(){
      JOptionPane.showMessageDialog(null, " This move is invalid", "Invalid Move", JOptionPane.PLAIN_MESSAGE, null); 
   }

   /**
   * Display a pop up window specifying the size of the chain(s) that is (are) formed after the swap
   * 
   * @param chainSize the size of the chain(s) that is (are) formed
   */
   public void showChainSizeMessage(int chainSize){
      JOptionPane.showMessageDialog(null, "Chain(s) with total size of " + chainSize + " is (are) formed.", "Chain Formed!", JOptionPane.PLAIN_MESSAGE, null); 
   }

   /**
   * Display a pop up window specifying the game is over with the score and number of moves used
   * 
   * @param point the score earned in the game
   * @param numMove the number of moves used in the game
   */
   public void showGameOverMessage(int point, int numMove){
      JOptionPane.showMessageDialog(null, "Game Over!\nYour score is " + point + " in " + numMove + " moves.", "Game Over!", JOptionPane.PLAIN_MESSAGE, null); 
      System.exit (0);
   }

   public static void main (String[] args) {
      BejeweledGUI gui = new BejeweledGUI ();
      Bejeweled game = new Bejeweled (gui);
      BejeweledListener listener = new BejeweledListener (game, gui);
      ButtonListener butListener = new ButtonListener(game, gui);
   }

}