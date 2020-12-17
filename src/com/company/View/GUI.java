package com.company.View;

import com.company.Controller.Connect4Game;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/** Test setting Swing's JComponents properties and appearances */
@SuppressWarnings("serial")
public class GUI extends JFrame {
    private final Container cp;

    private final Connect4Game game;

    // TODO: Get it from the board
    int rows;
    int columns;

    // TODO: Calculate them instead
    int windowWidth = 750;
    int windowHeight = 650;

    // Prepare ImageIcons to be used with JComponents
    private ImageIcon iconEmpty = null;
    private ImageIcon iconRed = null;
    private ImageIcon iconYellow = null;

    private final String title = "Connect 4 - ";

    private void worldsBestUpdater(JButton button) {
        int row10plusCol = Integer.parseInt(button.getName());
        int col = row10plusCol % 10;

        boolean player1turn = game.isIs1playing();
        if(player1turn) setTitle(title + "Yellow");
        else setTitle(title + "Red");

        int addedRow = game.round(col);

        if(addedRow != -1) {
            JButton buttonToUpdate = ((JButton)(cp.getComponent(columns * addedRow + col)));

            if(game.isIs1playing()) buttonToUpdate.setIcon(iconYellow);
            else buttonToUpdate.setIcon(iconRed);

            // check for winner
            if(game.checkForWinnerInGUI(col)) {
                JOptionPane.showMessageDialog(null, "You have won!");
                int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", null, JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    System.out.println("Trying to play again...");
                    game.reset(6, 7);
                    resetBoard();
                } else {
                    System.exit(0);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid position.");
        }
    }

    public void resetBoard() {
        for(int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                ((JButton)(cp.getComponent(columns * row + col))).setIcon(iconEmpty);
    }

    /** Constructor to setup the GUI */
    public GUI(boolean player1turn, Connect4Game game, int rows, int columns) {
        this.game = game;
        this.rows = rows;
        this.columns = columns;

        // Prepare Icons
        // Image path relative to the project root (i.e., bin)
        String imgEmptyFilename = "images/empty.png";
        URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
        if (imgURL != null) iconEmpty = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgEmptyFilename);

        String imgRedFilename = "images/red.png";
        imgURL = getClass().getClassLoader().getResource(imgRedFilename);
        if (imgURL != null) iconRed = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgRedFilename);


        String imgYellowFilename = "images/yellow.png";
        imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
        if (imgURL != null) iconYellow = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgYellowFilename);

        cp = getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                JButton button = new JButton(); // use setter to set text and icon
                button.setIcon(iconEmpty);
                button.setPreferredSize(new Dimension(100, 100));
                // row * 10 + col
                button.setName(Integer.toString((row * 10 + col)));

                button.addActionListener(actionEvent -> worldsBestUpdater(((JButton) (actionEvent.getSource()))));
                cp.add(button);
            }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(!player1turn) setTitle(title + "Yellow");
        else setTitle(title + "Red");
        setLocationRelativeTo(null); // center window on the screen
        setSize(windowWidth, windowHeight);
        setVisible(true);
    }
}