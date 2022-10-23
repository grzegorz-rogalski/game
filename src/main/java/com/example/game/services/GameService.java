package com.example.game.services;


public interface GameService {
    String startGame(long userId);
    String stopGame(long userId);
    void initData();
}