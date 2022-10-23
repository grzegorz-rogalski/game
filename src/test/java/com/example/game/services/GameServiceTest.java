package com.example.game.services;

import com.example.game.model.Session;
import com.example.game.model.User;
import com.example.game.repositiories.SessionRepository;
import com.example.game.repositiories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SessionRepository sessionRepository;

    @InjectMocks
    GameServiceImpl gameService;

    @Test
    public void testStartGameWithNotExistingUser() {
        String result = gameService.startGame(1);
        assertEquals("No such user", result);
    }

    @Test
    public void testStartGameWithAlreadyPlayingUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.isSessionPending(1L)).thenReturn(true);
        String result = gameService.startGame(1);
        assertEquals("User is already playing", result);
    }

    @Test
    public void testStartGameWithExistingUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.isSessionPending(1L)).thenReturn(false);
        String result = gameService.startGame(1);
        assertEquals("Game stared", result);
    }

    @Test
    public void testStopGameWithNotExistingUser() {
        String result = gameService.stopGame(1);
        assertEquals("No such user", result);
    }

    @Test
    public void testStopGameWithNotPlayingUser() {
        when(userRepository.existsById(1L)).thenReturn(true);

        String result = gameService.stopGame(1);
        assertEquals("User is not playing", result);
    }

    @Test
    public void testStopGameWithPlayingUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.getActiveSession(1L)).thenReturn(new Session());
        String result = gameService.stopGame(1);
        assertEquals("Game ended", result);
    }
}
