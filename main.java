import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.PrintWriter;

//main class
public class main {
  public static void main(String[] args) {
    GUI GUI = new GUI();
  }

}// end of main

// gui class
class GUI extends JFrame implements ActionListener {

  ArrayList<Game> games = readInData("game_info.csv");
  JComboBox dropdown = new JComboBox<>();
  JComboBox sort;
  JTextField searchbar = new JTextField("Search");
  JTextArea GameTextArea;
  JLabel imageSpace;
  JButton searchButton;
  // JButtons for add method and delte method
  JButton deletegame = new JButton("Delete a game");
  JButton addgame = new JButton("Add a Game");

  public GUI() {
    GameTextArea = new JTextArea("");
    GameTextArea.setFont(new Font("Courier", Font.BOLD, 20));
    GameTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
        BorderFactory.createRaisedBevelBorder()));
    GameTextArea.setLineWrap(true);
    GameTextArea.setWrapStyleWord(true);
    GameTextArea.setEditable(false);

    // Constructing JFrame
    JFrame frame = new JFrame("Videogame Catalogue");
    // Constructing the main panel which is in border layout
    JPanel main = (JPanel) frame.getContentPane();
    main.setLayout(new BorderLayout());
    // Constructing the top panel that will go in north area
    JPanel top = new JPanel();
    top.setLayout(new FlowLayout());
    // constructing bottom panel that will go in south area
    JPanel bottom = new JPanel();
    bottom.setLayout(new FlowLayout());

    // Two dropdown menus

    dropdown.removeAllItems();

    // adding stuff to dropdown menus
    String[] sortCategories = { "", "Sort by name (A-Z)", "Sort by genre", "Sort by release date", "Sort by publisher",
        "Sort by worldwide sales" };
    sort = new JComboBox<>(sortCategories);
    sort.addActionListener(this);
    dropdown.addActionListener(this);
    searchbar.addActionListener(this);
    addgame.addActionListener(this);
    deletegame.addActionListener(this);

    dropdown.setPreferredSize(new Dimension(300, 30));
    searchbar.setPreferredSize(new Dimension(200, 30));
    // JButton southButton = new JButton(" ");
    JButton eastButton = new JButton(" ");
    imageSpace = new JLabel(" ");
    searchButton = new JButton("Search");
    searchButton.addActionListener(this);

    top.add(sort);
    top.add(dropdown);
    top.add(searchbar);
    top.add(searchButton);

    bottom.add(addgame);
    bottom.add(deletegame);

    main.add(top, BorderLayout.NORTH);
    main.add(bottom, BorderLayout.SOUTH);
    main.add(eastButton, BorderLayout.EAST);
    main.add(imageSpace, BorderLayout.WEST);
    main.add(GameTextArea, BorderLayout.CENTER);

    eastButton.setBackground(new Color(11, 102, 175));
    top.setBackground(Color.black);
    bottom.setBackground(Color.black);
    addgame.setBackground(Color.black);
    addgame.setForeground(Color.white);
    deletegame.setBackground(Color.black);
    deletegame.setForeground(Color.white);
    imageSpace.setOpaque(true);
    imageSpace.setBackground(Color.black);
    GameTextArea.setBackground(new Color(0, 0, 89));
    GameTextArea.setForeground(Color.white);

    // Frame details
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 400);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

  }// end of constructor

  // actionperformed
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == sort && sort.getSelectedItem() != null) {

      String selected = sort.getSelectedItem().toString();

      if (selected.equals("Sort by name (A-Z)"))
        nameSort(games, dropdown);

      if (selected.equals("Sort by genre"))
        genreSort(games, dropdown);

      if (selected.equals("Sort by release date"))
        dateSort(games, dropdown);

      if (selected.equals("Sort by publisher"))
        publisherSort(games, dropdown);

      if (selected.equals("Sort by worldwide sales"))
        sizeSort(games, dropdown);

    }

    if (e.getSource() == dropdown && dropdown.getSelectedItem() != null) {
      String selected2 = dropdown.getSelectedItem().toString();
      String input = selected2.substring(0, selected2.indexOf(','));

      for (int i = 0; i < games.size(); i++)
        if (input.equals(games.get(i).name)) {
          GameTextArea.setText(
              "Name: " + games.get(i).name + "\nGenre: " + games.get(i).genre + "\nReleased:" + games.get(i).release
                  + "\nPublisher: " + games.get(i).publisher + "\nWorldwide Sales:" + games.get(i).size + " Million");
          ImageIcon theIcon = new ImageIcon(games.get(i).image);
          Image theImage = theIcon.getImage();
          Image modifiedImage = theImage.getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
          theIcon = new ImageIcon(modifiedImage);
          imageSpace.setIcon(theIcon);

        }
    }

    if (e.getSource() == addgame) {

      GameTextArea.setText("Please enter name, genre,release date, publisher, and total sales in the dialogue boxes:");
      String name = JOptionPane.showInputDialog(GameTextArea, "Name (first letter should be capitalized)");

      String genre = JOptionPane.showInputDialog(GameTextArea, "Genre (first letter should be capitalized)");
      String rel = JOptionPane.showInputDialog(GameTextArea, "Release Year");
      String rat = JOptionPane.showInputDialog(GameTextArea, "Publisher Name");
      String size = JOptionPane.showInputDialog(GameTextArea, "Worldwide Sales");

      int confirm = JOptionPane.showConfirmDialog(GameTextArea, "Do you want to add an image?", "Confirmation",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

      // add image

      if (confirm == JOptionPane.YES_OPTION) {
        File path;
        String n;
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File("."));
        int res = file.showSaveDialog(GameTextArea);

        if (res == JFileChooser.APPROVE_OPTION) {
          path = new File(file.getSelectedFile().getAbsolutePath());
          n = path.getName();

          String in;
          in = (name + "," + genre + "," + rel + "," + rat + "," + size + "," + n);

          writeToFile(games, in, "game_info.csv");
          games = readInData("game_info.csv");

        }
      }

      else {
        String in;

        in = ("0" + "," + name + "," + "0" + "," + rel + "," + genre + "," + "publisher" + "," + "0" + "," + "0" + ","
            + "0"
            + "," + "0" + "," + size);

        writeToFile(games, in, "game_info.csv");
        games = readInData("game_info.csv");
      }

    }

    if (e.getSource() == searchButton) {
      String search = searchbar.getText().trim();

      int searchResult = binarySearch(games, search);

      if (searchResult == -1) {

        GameTextArea.setText("Sorry " + searchbar.getText().trim() + " is not in the database. Want to add a game?");
        ImageIcon theIcon = new ImageIcon("error.png");
        Image theImage = theIcon.getImage();
        Image modifiedImage = theImage.getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
        theIcon = new ImageIcon(modifiedImage);
        imageSpace.setIcon(theIcon);

      }

      else {
        GameTextArea.setText("Name: " + games.get(searchResult).name + "\nGenre: " + games.get(searchResult).genre
            + "\nReleased: " + games.get(searchResult).release + "\nPublisher: " + games.get(searchResult).publisher
            + "\nDownload Size: " + games.get(searchResult).size + " Million");
        ImageIcon theIcon = new ImageIcon(games.get(searchResult).image);
        Image theImage = theIcon.getImage();
        Image modifiedImage = theImage.getScaledInstance(250, 300, java.awt.Image.SCALE_SMOOTH);
        theIcon = new ImageIcon(modifiedImage);
        imageSpace.setIcon(theIcon);
      }

    }
    if (e.getSource() == deletegame) {
      JOptionPane.showMessageDialog(GameTextArea, "You cannot delete existing game");

      // String name = JOptionPane.showInputDialog(GameTextArea, "Name of game *First
      // letter should be capitalized");
      // int check = binarySearch(games, name);
      // if (check == -1)
      // JOptionPane.showMessageDialog(GameTextArea, "This game was not in the
      // database.");
      // else {
      // int confirm = JOptionPane.showConfirmDialog(GameTextArea,
      // "Are you sure you want to delete " + name + " from the database?",
      // "Confirmation",
      // JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      // if (confirm == JOptionPane.YES_OPTION) {
      // removeRecord(games, binarySearch(games, name), "game_info.csv");
      // games = readInData("game_info.csv");
      // GameTextArea.setText("Please chose a sorting method to see the updated
      // database.");
      // } else if (confirm == JOptionPane.NO_OPTION) {
      // GameTextArea.setText("You selected: No");
      // } else {
      // GameTextArea.setText("Confrimation failed");
      // }
      // }

    }

  }// end of actionPerformed

  // sorts by name
  public static void nameSort(ArrayList<Game> games, JComboBox dropdown) {
    for (int i = 0; i < games.size(); i++) {
      int smallest = i;

      for (int a = i + 1; a < games.size(); a++) {
        if (games.get(a).name.compareTo(games.get(smallest).name) < 0)
          smallest = a;
      }
      swap(games, i, smallest);

    }
    String[] choices = new String[games.size()];
    for (int i = 0; i < games.size(); i++)
      choices[i] = (games.get(i).name + ", " + games.get(i).release); // used to be choices[i] = (games.get(i).name +
                                                                      // ",");
    setChoices(choices, dropdown);

  }

  public static void nameSort2(ArrayList<Game> games) {
    for (int i = 0; i < games.size(); i++) {
      int smallest = i;

      for (int a = i + 1; a < games.size(); a++) {
        if (games.get(a).name.compareTo(games.get(smallest).name) < 0)
          smallest = a;
      }
      swap(games, i, smallest);

    }

  }

  // sorts by genre
  public static void genreSort(ArrayList<Game> games, JComboBox dropdown) {
    for (int i = 0; i < games.size(); i++) {
      int smallest = i;

      for (int a = i + 1; a < games.size(); a++) {
        if (games.get(a).genre.compareTo(games.get(smallest).genre) < 0)
          smallest = a;
      }
      swap(games, i, smallest);

    }
    String[] choices = new String[games.size()];
    for (int i = 0; i < games.size(); i++)
      choices[i] = (games.get(i).name + "," + games.get(i).genre);
    setChoices(choices, dropdown);

  }

  // sorts by release date
  public static void dateSort(ArrayList<Game> games, JComboBox dropdown) {
    for (int i = 0; i < games.size(); i++) {
      int smallest = i;

      for (int a = i + 1; a < games.size(); a++) {
        if (Double.parseDouble(games.get(a).release) < Double.parseDouble(games.get(smallest).release))
          smallest = a;
      }
      swap(games, i, smallest);

    }
    String[] choices = new String[games.size()];
    for (int i = 0; i < games.size(); i++)
      choices[i] = (games.get(i).name + "," + games.get(i).release);
    setChoices(choices, dropdown);

  }

  // sorts by publisher
  public static void publisherSort(ArrayList<Game> games, JComboBox dropdown) {
    for (int i = 0; i < games.size(); i++) {
      int smallest = i;

      for (int a = i + 1; a < games.size(); a++) {
        if (games.get(a).publisher.compareTo(games.get(smallest).publisher) < 0)
          smallest = a;
      }
      swap(games, i, smallest);

    }
    String[] choices = new String[games.size()];
    for (int i = 0; i < games.size(); i++)
      choices[i] = (games.get(i).name + ", " + games.get(i).publisher);
    setChoices(choices, dropdown);

  }

  // sorts by download size
  public static void sizeSort(ArrayList<Game> games, JComboBox dropdown) {
    for (int i = 0; i < games.size(); i++) {
      int smallest = i;

      for (int a = i + 1; a < games.size(); a++) {
        if (Double.parseDouble(games.get(a).size) < Double.parseDouble(games.get(smallest).size))
          smallest = a;
      }
      swap(games, i, smallest);

    }
    String[] choices = new String[games.size()];
    for (int i = 0; i < games.size(); i++)
      choices[i] = (games.get(i).name + ", " + games.get(i).size + " Million");
    setChoices(choices, dropdown);

  }

  //////////////////////////// end of sorting methods/////////////

  public static ArrayList<Game> readInData(String fileName) {
    ArrayList<Game> games = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      int index = 0;

      while ((line = reader.readLine()) != null) {
        if (index != 0) {
          ArrayList<String> fields = parseCSVLine(line);
          if (fields.size() >= 11) { // Ensure data has at least 11 elements
            Game myGame = new Game(fields.get(1), fields.get(4), fields.get(3), fields.get(5), fields.get(10),
                fields.get(0));
            games.add(myGame);
          } else {
            System.err.println("Incomplete data in line: " + line);
          }
        }
        index++;
      }
    } catch (IOException iox) {
      System.err.println("Problem reading " + fileName);
      iox.printStackTrace();
    }
    return games;
  }

  private static ArrayList<String> parseCSVLine(String line) {
    ArrayList<String> fields = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean inQuotes = false;

    for (char c : line.toCharArray()) {
      if (c == '"') {
        inQuotes = !inQuotes;
      } else if (c == ',' && !inQuotes) {
        fields.add(sb.toString());
        sb.setLength(0); // Clear StringBuilder
      } else {
        sb.append(c);
      }
    }
    fields.add(sb.toString()); // Add the last field
    return fields;
  }

  // binary search
  public static int binarySearch(ArrayList<Game> games, String search) {
    nameSort2(games);
    int begining = 0;
    int end = games.size() - 1;

    for (int i = (begining + end) / 2; i < games.size();) {
      if ((games.get(i).name).compareTo(search) == 0) {
        return i;
      } else if (end == begining + 1) {
        if ((games.get(end).name).compareTo(search) == 0)
          return end;
        return -1;
      } else if ((games.get(i).name).compareTo(search) > 0) {
        end = i;
        i = (begining + end) / 2;
      } else if (games.get(i).name.compareTo(search) < 0) {
        if (i == games.size() - 2 && (games.get(i + 1).name).compareTo(search) == 0)
          return i + 1;
        begining = i;
        i = (end + begining) / 2;

      }

    }
    return -1;
  }

  public static void swap(ArrayList<Game> games, int leftIndex, int rightIndex) {

    Game tempStudent = games.get(leftIndex);
    games.set(leftIndex, games.get(rightIndex));
    games.set(rightIndex, tempStudent);
  }

  // method to add games to the dropdown
  public static void setChoices(String[] choices, JComboBox dropdown) {
    dropdown.removeAllItems();
    for (int i = 0; i < choices.length; i++)
      dropdown.addItem(choices[i]);
  }

  // Method to write to the file - to add a game
  public void writeToFile(ArrayList<Game> games, String text, String fileName) {

    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));

      out.write("\n" + text);

      out.close();

    }

    catch (IOException e) {
      System.out.println("Problem writing to " + fileName);
    }

  }

  // Method to erases a game from the database
  public static void removeRecord(ArrayList<Game> games, int index, String filepath) {

    games.remove(index);

    try {

      try {

        FileWriter fw = new FileWriter(filepath, false);

        PrintWriter pw = new PrintWriter(fw, false);

        pw.flush();

        pw.close();

        fw.close();

      } catch (Exception exception) {

        System.out.println("Exception has been caught");

      }
      BufferedWriter out = new BufferedWriter(new FileWriter(filepath, true));

      for (int i = 1; i < games.size() + 1; i++) {
        if (i == 1)
          out.write(games.get(i - 1).name + "," + games.get(i - 1).genre
              + "," + games.get(i - 1).release + "," + games.get(i - 1).publisher
              + "," + games.get(i - 1).size + "," + games.get(i - 1).image);
        else {
          out.write("\n" + games.get(i - 1).name + "," + games.get(i - 1).genre + "," + games.get(i - 1).release + ","
              + games.get(i - 1).publisher + "," + games.get(i - 1).size + "," + games.get(i - 1).image);
        }

      }

      out.close();

    } catch (Exception e) {
      System.out.println(e);
    }

  }

}// end of gui class

class Game // Creating object
{
  String name;
  String genre;
  String release;
  String publisher;
  String size;
  String image;

  public Game(String n, String g, String re, String rt, String sz, String im) {

    name = n;
    genre = g;
    release = re;
    publisher = rt;
    size = sz;
    image = im;
  }

  public String toString() {
    return name + " " + genre + " " + release + " " + publisher + " " + size + " " + image;
  }

} // end of Game class
