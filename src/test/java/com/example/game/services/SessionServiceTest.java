package com.example.game.services;

import com.example.game.dto.SessionDTO;
import com.example.game.model.Session;
import com.example.game.model.User;
import com.example.game.repositiories.SessionRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {

    @MockBean
    private SessionRepository sessionRepository;

    @InjectMocks
    SessionServiceImpl sessionService;

    @Test
    public void testGetSessionInfoWithNullParametersOnEmptyDatabase() {
        List<SessionDTO> result = sessionService.getSessionInfo(null, null, null, null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testGetSessionInfoWithNullParameters() {
        Date startDate = new Date();
        when(sessionRepository.findAllByCriteria(null, null, null, null))
                .thenReturn(Lists.newArrayList(Session.builder().id(1L).startDate(startDate).user(User.builder().id(1L).build()).build()));
        List<SessionDTO> result = sessionService.getSessionInfo(null, null, null, null);
        assertEquals(Lists.newArrayList(SessionDTO.builder().userId(1L).sessionId(1L).startDate(startDate).build()), result);
    }
}
