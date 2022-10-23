package com.example.game.services;

import com.example.game.dto.SessionDTO;
import com.example.game.model.Session;
import com.example.game.model.SessionState;
import com.example.game.repositiories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<SessionDTO> getSessionInfo(Long userId, String startDate, String endDate, SessionState state) {
        startDate = validateDate(startDate);
        endDate = validateDate(endDate);
        List<SessionDTO> sessions = toDTO(sessionRepository.findAllByCriteria(userId, startDate, endDate, state));
        return sessions;
    }

    private String validateDate(String date) {
        // TODO validation of date format
        if(date != null && date.equals("")) {
            date = null;
        }
        return date;
    }

    private List<SessionDTO> toDTO(List<Session> sessions) {
        List<SessionDTO> result = new ArrayList<>(sessions.size());
        sessions.forEach(x -> result.add(SessionDTO.builder().userId(x.getUser().getId()).
                sessionId(x.getId()).startDate(x.getStartDate()).endDate(x.getEndDate() != null ? x.getEndDate() : null).state(x.getState()).build()));
        return result;
    }


}
