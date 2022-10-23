package com.example.game.controllers;

import com.example.game.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public String startSession(@RequestParam("id") int id) {
        return gameService.startGame(id);
    }

    @PostMapping("/stop")
    public String stopSession(@RequestParam("id") int id) {
        return gameService.stopGame(id);
    }

    @PostMapping("/initData")
    public void initData() {
        gameService.initData();
    }
}
