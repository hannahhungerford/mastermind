/*
 * The MasterMind Game
 * Programmer: Hannah Hungerford 
 * Date Completed: November 30 2017
 * Mastermind is a game where the user
 * trys to guess a secret combination
 * of pegs.
 */

//impoty necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import sun.audio.*; 
import java.io.*;

public class HannahMasterMind extends JFrame implements ActionListener    //MASTERMIND CLASS
{
  static JLabel title = new JLabel ("MasterMind!"); //main title 
  static JButton instructions = new JButton ("Instructions"); // intstructions button
  static JButton reset = new JButton ("Reset"); // the resest button
  static JButton hints[] = new JButton[5]; //hints array
  static JButton holeLg [] = new JButton [50]; // large hole array
  static JButton holeSm [] = new JButton [50]; //small hole array 
  static JButton pegTop [] = new JButton [6]; //peg top array
  static JButton submit = new JButton ("Submit"); //submit button
  
  static int combination [] = new int[5];  //secret combination of colours
  static boolean usedCombo[] = new boolean [5]; //a parallel array to combination used in deciding clues
  static int playerGuess [] = new int[5];  //players guess of the secret combination
  static boolean usedPlayerGuess [] = new boolean[5]; //a parallel array to playerGuess used in deciding clues 
  
  static int currentRow = 10; //currentRow of the players guess
  
  static ImageIcon blackPeg = new ImageIcon ("blackPeg.gif"); //black peg icon
  static ImageIcon whitePeg = new ImageIcon ("whitePeg.gif"); //white peg icon
  static ImageIcon questionMarkIcon = new ImageIcon("blank.gif"); //question mark icon  
  static ImageIcon holeLgIcon = new ImageIcon ("holeLg.gif"); //large hole icon
  static ImageIcon holeSmIcon = new ImageIcon ("holeSm.gif"); //small hole icon
  static ImageIcon bluePeg = new ImageIcon ("bluePeg.gif"); //blue peg icon
  static ImageIcon greenPeg = new ImageIcon ("greenPeg.gif"); //green peg icon
  static ImageIcon orangePeg = new ImageIcon ("orangePeg.gif"); //orange peg icon
  static ImageIcon pinkPeg = new ImageIcon ("pinkPeg.gif"); //pink peg icon
  static ImageIcon redPeg = new ImageIcon ("redPeg.gif"); //red peg icon
  static ImageIcon yellowPeg = new ImageIcon ("yellowPeg.gif"); //yellow peg icon
  static ImageIcon blueTop = new ImageIcon ("blueTop.gif"); //blue Top icon
  static ImageIcon greenTop = new ImageIcon ("greenTop.gif"); //green Top icon
  static ImageIcon orangeTop = new ImageIcon ("orangeTop.gif"); //orange Top icon
  static ImageIcon pinkTop = new ImageIcon ("pinkTop.gif"); //pink Top icon
  static ImageIcon redTop = new ImageIcon ("redTop.gif"); //red Top icon
  static ImageIcon yellowTop = new ImageIcon ("yellowTop.gif"); //yellow Top icon
  static ImageIcon pegSelected = new ImageIcon ("blueTop.gif"); //the peg the player selects
  
  
  static int numPegSelected = 0; //the number of the peg being selected to place
  static JLabel pegSelectedLabel = new JLabel (bluePeg); // the peg the user has selected starting with blue as the default
  
  static JLabel blackPegLabel = new JLabel (blackPeg);  //black peg icon label
 static JLabel whitePegLabel = new JLabel (whitePeg); //white peg icon label
  static JLabel clueInstructions1 = new JLabel (" Correct colour, correct position"); //first clue label
  static JLabel clueInstructions2 = new JLabel (" Correct colour, correct position"); //second clue label

  
  static int colour1, colour2, colour3, colour4, colour5, colour6;   //randomly chosen colours used for making the computers combination
  static int difficulty; //level of difficulty
  
  ///////////////////////////////////// Instructions text
  static String instructionsLine1 = "At the beginning of the game the computer will select its secret combination of 5 colour pegs.\n";
  static String instructionsLine2 = "The secret code can be made up if any combination of the available coloured pegs (repeats are allowed),\n";
  static String instructionsLine3 = "in any order. The secret code is hidden from view beind the '?' at the top of the game board.\n";
  static String instructionsLine4 = "\n";
  static String instructionsLine5 = "Difficulty Level Selected:\n";
  static String instructionsLine6 = "-Easy: the computer will user 1-2 colours in the secret code.\n";
  static String instructionsLine7 = "-Medium: the computer will user 2-4 colours in the secret code.\n";
  static String instructionsLine8 = "-Hard: the computer will user 4-6 colours in the secret code.\n";
  static String instructionsLine9 = "\n";
  static String instructionsLine10 = "Your goal is to guess the correct order & colour combination to match the computer's secret code.\n";
  static String instructionsLine11 = "You will have 10 attempts to guess what the secret combination is.\n";
  static String instructionsLine12 = "\n";
  static String instructionsLine13 = "To make a guess, select the colour of hte peg you want and then click on a position in the current row.\n";
  static String instructionsLine14 = "Once you have filled all 5 holes in the current row, click the 'submit' button to make your guess.\n";
  static String instructionsLine15 = "\n";
  static String instructionsLine16 = "After you submit each guess the computer will provide you with hints using 'reult pegs':\n";
  static String instructionsLine17 = "-Black pegs: indicate the number of correctly coloured pegs you placed in the correct positions.\n";
  static String instructionsLine18 = "-White pegs: indicate the number of correctly coloured pegs you placed in the wrong position.s\n";
  static String instructionsLine19 = "\n";
  static String instructionsLine20 = "Please note that the order of the black & white pegs does not correspond to the order of the coloured\n";
  static String instructionsLine21 = "pegs in your guess. They simply indicate the numbers of matches.\n";
  static String instructionsTxt = instructionsLine1 + instructionsLine2 + instructionsLine3 + instructionsLine4 + instructionsLine5 + instructionsLine6 + instructionsLine7 + instructionsLine8 + instructionsLine9 + instructionsLine10 + instructionsLine11 + instructionsLine12 +  instructionsLine13 + instructionsLine14  + instructionsLine15 + instructionsLine16 + instructionsLine17 + instructionsLine18 + instructionsLine19 + instructionsLine20 + instructionsLine21;
  
  

  public HannahMasterMind () 
  {
    super ("Hannah's MasterMind Game");  //set the super
    
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    Container contentPane = getContentPane ();
       
    JPanel mainPanel = new JPanel ();
    mainPanel.setLayout (new BorderLayout ());
    JPanel options = new JPanel (new GridLayout (1,3)); //second top panel containing the hint, instructions and reset button
    JPanel top = new JPanel (); //title panel
    JPanel topSection = new JPanel (new GridLayout (2,1)); //holds the title and the options panel
    JPanel hint = new JPanel (new GridLayout (1,5)); //hint panel
    JPanel holeLgPanel = new JPanel (new GridLayout (10,5)); //large hole panel
    JPanel holeSmPanel = new JPanel (new GridLayout (10,5)); //small hole panel
    JPanel selection = new JPanel (new GridLayout (4,1)); // clues panel
    JPanel clues = new JPanel (new GridLayout (2,2)); // 
    JPanel pegTopPanel = new JPanel (new GridLayout (3,2));                           
    
    
    
    //initialize hints array and add them to the hint panel
    for (int a = 0; a < 5; a++)
    {
      hints [a] = new JButton (questionMarkIcon); 
      hints[a].setBackground (Color.GRAY);//initialize
      hint.add (hints[a]);                        //add to hint panel
    }
    
    //initialize large and small hole array and add them to the large and small hole panel
    for (int b = 0; b < 50; b++)
    {
      holeLg[b] = new JButton (holeLgIcon);
      holeLg[b].setBackground (Color.GRAY);
      holeLg[b].addActionListener (this);
      holeLgPanel.add (holeLg [b]);
      holeSm[b] = new JButton (holeSmIcon);
      holeSm[b].setBackground (Color.WHITE);
      holeSmPanel.add (holeSm [b]);
    }
    
    //initialize peg top buttons and add action listeners to each button
    pegTop [0] = new JButton (blueTop);
    pegTop [0].addActionListener (this);
    pegTop [1] = new JButton (greenTop);
    pegTop [1].addActionListener (this);
    pegTop [2] = new JButton (orangeTop);
    pegTop [2].addActionListener (this);
    pegTop [3] = new JButton (pinkTop);
    pegTop [3].addActionListener (this);
    pegTop [4] = new JButton (redTop);
    pegTop [4].addActionListener (this);
    pegTop [5] = new JButton (yellowTop);
    pegTop [5].addActionListener (this);
    
    //add peg top buttons to the peg top panel
    for (int c = 0; c < 6; c++)
    {
      pegTopPanel.add (pegTop [c]);
    }
    
    
    top.add (title);
    
    
    options.add (hint);
    options.add (instructions);
    options.add (reset);
    
    instructions.addActionListener (this); 
    submit.addActionListener (this);
    reset.addActionListener (this);
    
    topSection.add (top);
    topSection.add (options);
    top.setAlignmentX(Component.CENTER_ALIGNMENT); //set the title to the center of the page
    
    clues.add (blackPegLabel);
    clues.add(clueInstructions1);
    clues.add (whitePegLabel);
    clues.add(clueInstructions2);
    
    
    selection.add (clues);
    selection.add (pegSelectedLabel);
    selection.add (pegTopPanel);
    selection.add (submit);
    
    mainPanel.add (topSection, BorderLayout.PAGE_START); //add the 'mainPanel' to the contentPane
    mainPanel.add (holeLgPanel, BorderLayout.LINE_START);
    mainPanel.add (holeSmPanel, BorderLayout.CENTER);
    mainPanel.add (selection, BorderLayout.LINE_END);
    
    
    contentPane.add (mainPanel);
    setSize (900,500);  //set size of the window
    setVisible (true);  // window is visible
    
    
    
    //set up the chose difficulty JOptionPane
    Object[] possibleValues = { "Hard", "Medium", "Easy" }; //choices of difficulty
    Object selectedValue = JOptionPane.showInputDialog(null, "Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0] );
    
    if (selectedValue.equals ("Hard")) //if user selected 'hard' set difficulty to 3
    {
      difficulty = 3;
    }
    
    else if (selectedValue.equals ("Medium")) //if user selected 'medium' set difficulty to 2
    {
      difficulty = 2;
    }
    
    else if (selectedValue.equals ("Easy")) //if user selected 'easy' set difficulty to 1
    {
      difficulty = 1;
    }
    
    //initialize random colours 
    colour1 = (int) (Math.random ()*6) + 1;      
    int temp = (int) (Math.random ()*6) + 1;
    
    while (temp == colour1)  //error trap to ensure colour2 does not equal colour1
    {
      temp = (int) (Math.random ()*6) + 1;
    } 
    
    colour2 = temp;
    temp = (int) (Math.random ()*6) + 1;
    
    while (temp == colour1 || temp == colour2)  //error trap to ensure colour3 does not equal colour1 or colour2
    {
      temp = (int) (Math.random ()*6) + 1;
    } 
    
    colour3 = temp;
    temp = (int) (Math.random ()*6) + 1;
    
    while (temp == colour1 || temp == colour2 || temp == colour3)  //error trap to ensure colour4 does not equal colour1 or colour2 or colour3
    {
      temp = (int) (Math.random ()*6) + 1;
    } 
    
    colour4 = temp;
    temp = (int) (Math.random ()*6) + 1;
    
    while (temp == colour1 || temp == colour2 || temp == colour3 || temp == colour4)  //error trap to ensure colour5 does not equal colour1 or colour2 or colour3 or colour4
    {
      temp = (int) (Math.random ()*6) + 1;
    } 
    
    colour5 = temp;
    temp = (int) (Math.random ()*6) + 1;
    
    while (temp == colour1 || temp == colour2 || temp == colour3 || temp == colour4 || temp == colour5)  //error trap to ensure colour6 does not equal colour1 or colour2 or colour3 or colour4 or colour5
    {
      temp = (int) (Math.random ()*6) + 1;
    } 
    
    colour6 = temp;
      
    //create an easy secret combination (1-2 colours)
    if (difficulty == 1)
    {                  
      for (int d = 0; d < 5; d++)
      {
        int num = (int) (Math.random ()*2) + 1;
        
        if (num==1)
        combination[d] = colour1;
          
        else if (num==2)
          combination[d] = colour2;               
      }
    }
    
    //create a medium secret combination (2-4 colours)
    if (difficulty == 2)
    {
      
      for (int d = 0; d < 5; d++)
      {
        int num = (int) (Math.random ()*4) + 1;
        
        if (num==1)
        combination[d] = colour1;
          
        else if (num==2)
          combination[d] = colour2;
        
        else if (num==3)
          combination[d] = colour3;
        
        else if (num==4)
          combination[d] = colour4;          
      }
    }
    
    //create a hard secret combination (4-6 colours)
    if (difficulty == 3)
    {        
      for (int d = 0; d < 5; d++)
      {
        int num = (int) (Math.random ()*4) + 1;
        
        if (num==1)
        combination[d] = colour1;
          
        else if (num==2)
          combination[d] = colour2;
        
        else if (num==3)
          combination[d] = colour3;
        
        else if (num==4)
          combination[d] = colour4; 
        
        else if (num==5)
          combination[d] = colour5;
        
        else if (num==6)
          combination[d] = colour6;
      }
    }
    
    for (int count = 0; count < 5; count++)
    {
      System.out.println (combination [count]);
    }
    
  }
  
  
  public void actionPerformed (ActionEvent event) 
  {
//    try 
//    {
//    }
//    catch (FileNotFoundExcpetion ex)
//    {
//    }
//    catch (IOException ex2)
//    {
//    }
    int cluesPos = 0;
    int colourCheck = 0;
    
      
    
    //set parallel guess and combo arrays to start as false 
    for (int j = 0; j< 5; j++)
    {
      usedPlayerGuess [j] = false;
      usedCombo[j] = false;
    }
    
    if (event.getSource () == instructions) // if the user clicks instructions button
    {
      JOptionPane.showMessageDialog (null, instructionsTxt, "Instructions", JOptionPane.INFORMATION_MESSAGE);
      
    }
    
    
    
    
    //selecting the colour of the peg they would like to chose
    if (event.getSource () == pegTop[0])  // if they click blue
    {
//      playSound (1);
      numPegSelected = 1;
      pegSelected = new ImageIcon ("blueTop.gif");
      pegSelectedLabel.setIcon (bluePeg);
    }
    
    if (event.getSource () == pegTop[1])  //if they click green
    {
      numPegSelected = 2;
      pegSelected = new ImageIcon ("greenTop.gif");
      pegSelectedLabel.setIcon (greenPeg);
    }
    
    if (event.getSource () == pegTop[2])
    {
      numPegSelected = 3;
      pegSelected = new ImageIcon ("orangeTop.gif");
      pegSelectedLabel.setIcon (orangePeg);
    }
    
    if (event.getSource () == pegTop[3])
    {
      numPegSelected = 4;
      pegSelected = new ImageIcon ("pinkTop.gif");
      pegSelectedLabel.setIcon (pinkPeg);
    }
    
    if (event.getSource () == pegTop[4])
    {
      numPegSelected = 5;
      pegSelected = new ImageIcon ("redTop.gif");
      pegSelectedLabel.setIcon (redPeg);
    }
    
    if (event.getSource () == pegTop[5])
    {
      numPegSelected = 6;
      pegSelected = new ImageIcon ("yellowTop.gif");
      pegSelectedLabel.setIcon (yellowPeg);
    }
    
    //allows player to place pegs on the current Row
    for (int e = (currentRow*5) - 5; e < currentRow*5; e++)
    {
      if (event.getSource () == holeLg[e])
      {
        holeLg[e].setIcon (pegSelected);
        playerGuess [Math.abs((currentRow * 5 - e) -5)] = numPegSelected;
        
      }
    }
    
    
    if (event.getSource () == submit)  //if user clicked submit 
    {
      
      if (playerGuess [0] == 0 || playerGuess [1] == 0 || playerGuess [2] == 0 || playerGuess [3] == 0 || playerGuess [4] == 0) //if they left a peg blank 
      {
        JOptionPane.showMessageDialog (null,"Please complete the entire row", "Error", JOptionPane.INFORMATION_MESSAGE);   //output error message       
      }
      
      else 
      {
        
        for (int f = (currentRow*5) - 5; f < currentRow*5; f++)
        {
          
          if (playerGuess [currentRow * 5 - (f + 1)] == combination [currentRow * 5 - (f + 1)]) //if the players guess is the same colour and in the same position as the secret combo
            
          {
            
            usedPlayerGuess [currentRow * 5 - (f + 1)] = true; //set boolean parrallel areas for that position to true 
            usedCombo[currentRow * 5 - (f + 1)] = true;    
            holeSm [currentRow*5 - 5 + cluesPos].setIcon (blackPeg); //places a peg peg in the clues secction
            cluesPos = cluesPos + 1;  //increase the position in the clues row 
          }         
        }
        
        for (int g = (currentRow*5) - 5; g < currentRow*5; g++)
        {
          
          if (usedPlayerGuess [currentRow * 5 - (g + 1)] == false)
          {
            colourCheck = playerGuess [currentRow * 5 - (g + 1)];
          }
          
          
          for (int h = (currentRow*5) - 5; h < currentRow*5; h++)
          {
            if (colourCheck == 0)
            {
            }
            else if (colourCheck == combination [currentRow * 5 - (h + 1)] && usedCombo[currentRow * 5 - (h + 1)] == false)
            {
              //System.out.println ("hello");
              holeSm [currentRow*5 - 5 + cluesPos].setIcon (whitePeg); //places a peg peg in the clues secction
              cluesPos = cluesPos + 1;  //increase the position in the clues row 
              System.out.println ("player"+(currentRow * 5 - (g + 1)) + " computr" + (currentRow * 5 - (h + 1)));
              usedPlayerGuess [currentRow * 5 - (g + 1)] = true; //set boolean parrallel areas for that position to true 
              usedCombo[currentRow * 5 - (h + 1)] = true;
            }
          }
        }
        
        
        for (int i = (currentRow*5) - 5;i  < currentRow*5; i++)
        {
          holeLg[i].removeActionListener(this);
        }
        
        //check win        
        if (playerGuess [0] == combination [0] && playerGuess [1] == combination [1] && playerGuess [2] == combination [2] && playerGuess [3] == combination [3] && playerGuess [4] == combination [4])
        {
          displayCombo();
          JOptionPane.showMessageDialog (null,"YOU WIN", "Condragulations", JOptionPane.INFORMATION_MESSAGE);   //output win message 
          
          
        }
        
        //check loss 
        if (currentRow == 1)
        {
          displayCombo();
          JOptionPane.showMessageDialog (null,"You lose", "Try Again!", JOptionPane.INFORMATION_MESSAGE);   //output loss message 
          
          
        }
        
        
        currentRow = currentRow-1;         
      }
      
    }
    
    if (event.getSource () == reset)  //if user clicked submit 
    {
      int yesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart?", "Reset", JOptionPane.YES_NO_OPTION);
        if (yesOrNo == JOptionPane.YES_OPTION) 
        {
           reset();
        }
    }
      
  }
  
  public static void displayCombo ()
  {
    
    for (int k = 0; k < 5; k++)
    {
      System.out.println (" ");
      System.out.println (combination[k]);
      if (combination[k] == 1)
      {
        hints[k].setIcon (blueTop);
      }
      
      else if (combination[k] == 2)
      {
        hints[k].setIcon (greenTop);
      }
      
      else if (combination[k] == 3)
      {
        hints[k].setIcon (orangeTop);
      }
      
      else if (combination[k] == 4)
      {
        hints[k].setIcon (pinkTop);
      }
      
      else if (combination[k] == 5)
      {
        hints[k].setIcon (redTop);
      }
      
      else if (combination[k] == 6)
      {
        hints[k].setIcon (yellowTop);
      }
    }
  }
  
  public static void playSound (int sound) throws IOException
  {
   InputStream in = new FileInputStream("blip.wav");
   AudioStream blip = new AudioStream(in); 
 
   if (sound == 1)
   {
    AudioPlayer.player.start(blip);            
    AudioPlayer.player.stop(blip); 
   }
  }
  
  public static void reset ()
  {
    
    //reset varibales    
    currentRow = 10;
    
    
    
    //reset button icons 
    for (int b = 0; b < 50; b++)
    {
      holeLg[b].setIcon(holeLgIcon);
      holeSm[b].setIcon(holeSmIcon);      
    }
    
    
       //set up the chose difficulty JOptionPane
    Object[] possibleValues = { "Hard", "Medium", "Easy" }; //choices of difficulty
    Object selectedValue = JOptionPane.showInputDialog(null, "Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0] );
    
    if (selectedValue.equals ("Hard")) //if user selected 'hard' set difficulty to 3
    {
      difficulty = 3;
    }
    
    else if (selectedValue.equals ("Medium")) //if user selected 'medium' set difficulty to 2
    {
      difficulty = 2;
    }
    
    else if (selectedValue.equals ("Easy")) //if user selected 'easy' set difficulty to 1
    {
      difficulty = 1;
    }
  }
}

