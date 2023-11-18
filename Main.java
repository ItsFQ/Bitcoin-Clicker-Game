import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class Main {
  static String userName;
  static ArrayList<Stats> storeData; 

  // Main method to bring all the methods together and make the project polished
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Enter name: ");
    userName = in.nextLine();

    ArrayList <Stats> sortedScores = sortScores();
    storeData = searchPlayer(sortedScores, userName);
    if(storeData.size() != 0){
    System.out.println("Here are your previous logged scores:");
    for(Stats i : storeData){
      System.out.println("Bitcoin(s): "+ i.getScore());
    }
    } else{
      System.out.println("Welcome! Have fun!");
    }
    new Clicker();
    System.out.println("Type !leaderboard to see the global leaderboard");
    String lInput = in.nextLine();
    if(lInput.equals("!leaderboard")){
      for(Stats i : sortScores()){
      System.out.println("Name: " + i.getUserName() + "\nBitcoin(s): " + i.getScore());
      System.out.println("===================");
    }
    }
  }
  
  // A method to create a new Stats object then, send it to the writefile method to be recorded
  public static void addScore(int x){
    Date d = new Date();

    Stats newScore = new Stats(userName, x, d.getTime());
    writeFile(newScore);
  }
  
  
  
  // Method to write player's stats to file
  public static void writeFile(Stats obj){
    try{
      FileWriter writer = new FileWriter("scores.csv",true);
      writer.write(obj.toString());
      writer.close();
    }
    catch (IOException e){
      System.out.println("File not Found!");
    }
  }


  // Methods to sort Scores and add to an ArrayList
  public static ArrayList<Stats> sortScores(){
    Scanner in = null;
    String [] split;
    Stats nextLine;
    File x = new File("scores.csv");
    ArrayList <Stats> stats = new ArrayList<>();
    try {
      in = new Scanner(x);
      while(in.hasNextLine()){
        split = in.nextLine().split(",");
        nextLine = new Stats(split[0], Integer.parseInt(split[1]), Long.parseLong(split[2]));
        if(stats.size() == 0){
          stats.add(nextLine);
        }
        else{
          int finder = 0;
          while(nextLine.compareTo(stats.get(finder)) < 0){
            finder += 1;
            if(finder == stats.size()){
              break;
            }
          }
          stats.add(finder, nextLine);
        }
      }
      return stats;
    } 
    catch (Exception e) {
      System.out.print("ERROR:\n" + "The file: " + x.getName() + " does not exist.");
    }
    return null;
  }
  
  // The method used to serach of a player's previous individual scores 
  public static ArrayList<Stats> searchPlayer(ArrayList <Stats> stats, String username){
      ArrayList <Stats> matchUsername  =  new ArrayList<>();
      for(Stats i: stats){
      if(i.getUserName().equals(username)){
        matchUsername.add(i);
      }
    }
    return matchUsername;
  }
}


