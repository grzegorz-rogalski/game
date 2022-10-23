package com.example.game.controllers;

import com.example.game.dto.SessionDTO;
import com.example.game.model.SessionState;
import com.example.game.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<SessionDTO> getSessionInfo(@RequestParam(name = "id", required = false) Long userId,
                                           @RequestParam(required = false) String startDate,
                                           @RequestParam(required = false) String endDate,
                                           @RequestParam(required = false) SessionState state) {
        return sessionService.getSessionInfo(userId, startDate, endDate, state);
    }
}
