package com.example.game.services;

import com.example.game.dto.SessionDTO;
import com.example.game.model.SessionState;

import java.util.List;

public interface SessionService {

    List<SessionDTO> getSessionInfo(Long userId, String startDate, String endDate, SessionState name);
}
