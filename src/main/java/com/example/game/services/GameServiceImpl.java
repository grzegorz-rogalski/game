package com.example.game.services;

import com.example.game.model.Session;
import com.example.game.model.SessionState;
import com.example.game.model.User;
import com.example.game.repositiories.SessionRepository;
import com.example.game.repositiories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameServiceImpl implements GameService {
    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Autowired
    public GameServiceImpl(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public String startGame(long userId) {
        if (!userRepository.existsById(userId)) {
            return "No such user";
        } else if (userRepository.isSessionPending(userId)) {
            return "User is already playing";
        } else {
            User user = userRepository.getReferenceById(userId);
            Session session = new Session();
            session.setStartDate(new Date());
            session.setUser(user);
            session.setState(SessionState.ACTIVE);
            sessionRepository.saveAndFlush(session);
            return "Game stared";
        }
    }

    public String stopGame(long userId) {
        if (!userRepository.existsById(userId)) {
            return "No such user";
        } else {
            Session session = userRepository.getActiveSession(userId);
            if (session == null) {
                return "User is not playing";
            }
            session.setState(SessionState.CLOSED);
            session.setEndDate(new Date());
            sessionRepository.saveAndFlush(session);
            return "Game ended";
        }
    }


    @Override
    public void initData() {
        userRepository.saveAndFlush(User.builder().id(1L).build());
        sessionRepository.saveAndFlush(Session.builder().user(User.builder().id(1L).build()).id(1L).startDate(new Date()).state(SessionState.ACTIVE).build());
    }
}
