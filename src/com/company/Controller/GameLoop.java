package com.company.Controller;

import com.company.View.GUI;

public class GameLoop {

    private final Connect4Game game;
    private final GUI ourGUI;

    public GameLoop() {
        game = new Connect4Game("R", "Y", 6, 7);
        ourGUI = new GUI(game.isIs1playing(), game, 6, 7);
    }

}
