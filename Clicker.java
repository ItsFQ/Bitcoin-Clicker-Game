import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Clicker{

  //The variables/refrences needed
  JLabel counterLabel;
  JButton button1, button2;
  int moneyCounter;
  int upg;
  Font f1, f2;
  CoinHandler handler = new CoinHandler();
  GeneratorHandler genHandler = new GeneratorHandler();
  LogHandler lHandler = new LogHandler();
  Timer timer;

  // The constructor for the Clicker class
  public Clicker(){
    moneyCounter = 0;
    upg = 30;
    createFont();
    createGUI();
  }

  // A method to have the fonts for in the program
  public void createFont(){
    f1 = new Font("Marker Felt", Font.PLAIN,32);
    f2 = new Font("Comic Sans", Font.PLAIN,15);
  }

  // The method that creates the GUI
  public void createGUI(){
    
    // To build the frame of the program
    JFrame window = new JFrame();
    window.setSize(500,500);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setBackground(Color.darkGray);
    window.setLayout(null);

    // To create a panel for the bitcoin button
    JPanel coinPanel = new JPanel();
    coinPanel.setBounds(140, 90, 200, 200);
    coinPanel.setBackground(Color.darkGray);
    window.add(coinPanel);

    // To get the bitcoin image
    ImageIcon coin = new ImageIcon("bitcoin.png");

    // To create the button that will be placed the coin panel
    JButton coinButton = new JButton();
    coinButton.setBackground(Color.darkGray);
    coinButton.setFocusPainted(false);
    coinButton.setIcon(coin);
    coinButton.setBorder(null);
    coinButton.addActionListener(handler);
    coinButton.setActionCommand("coin");
    coinPanel.add(coinButton);

    // To create a pannel for the bitcoin counter
    JPanel counterPanel = new JPanel();
    counterPanel.setBounds(115, 10, 250, 300);
    counterPanel.setBackground(Color.darkGray);
    window.add(counterPanel);

    // To create a bitcoin label for the text and to style it too
    counterLabel = new JLabel("Bitcoin(s): " + moneyCounter);
    counterLabel.setForeground(Color.white);
    counterLabel.setFont(f1);
    counterPanel.add(counterLabel);


    //To create a panel for the upgrade and the log bitcoins buttons
    JPanel upgradePanel = new JPanel();
    upgradePanel.setBounds(90, 310, 300, 150);
    upgradePanel.setBackground(Color.darkGray);
    upgradePanel.setLayout(new GridLayout(3,1));
    window.add(upgradePanel);

    // To create a button for the genrator upgrade
    button1 = new JButton("Gen Upgrade (" + upg + " bitcoins)");
    button1.setFont(f2);
    button1.setFocusPainted(false);
    button1.addActionListener(genHandler);
    upgradePanel.add(button1);
    
    // To create a button for the log bitcoins option
    button2 = new JButton("Log Bitcoin(s)");
    button2.setFont(f2);
    button2.setFocusPainted(false);
    button2.addActionListener(lHandler);
    upgradePanel.add(button2);

    window.setVisible(true);
  }

  // An actionListner to tell the program what to do when the bitcoin button is clicked
  class CoinHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
    moneyCounter++;
    counterLabel.setText("Bitcoin(s): "+ moneyCounter);

  }
}

// A timer method to automate the cliciking process (for the "gen upgrade")
public void setTimer(){
    timer = new Timer(1000, new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
      moneyCounter++;
      counterLabel.setText("Bitcoin(s): " + moneyCounter);
    }});
    
    
}

// To give function to the "Gen Upgrade" button
class GeneratorHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){

      if(moneyCounter >= upg){
      moneyCounter = (moneyCounter - upg)-1;
      upg = upg * 2;
      button1.setText("Gen Upgrade (" + upg + " bitcoins)");
      setTimer();
      timer.start();      
      }
    
  }
}

// To give function to the "Log Bitcoins" button
class LogHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      Main.addScore(moneyCounter);
    
  }
}
  
}

