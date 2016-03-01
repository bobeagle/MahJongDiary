package com.csbob.mahjongdiary.controller;

import com.csbob.mahjongdiary.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * App Controller
 * <p/>
 * Created by yiyang on 01/03/2016.
 */
public class AppController {

    private static AppController INSTANCE;

    private List<Game> games = new ArrayList<>(10);

    private AppController() {
    }

    public static AppController getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AppController();
        return INSTANCE;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public List<Game> getGames() {
        return games;
    }

}
