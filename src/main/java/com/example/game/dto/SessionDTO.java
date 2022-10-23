package com.example.game.dto;


import com.example.game.model.SessionState;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class SessionDTO {
    private Long userId;
    private Long sessionId;
    private Date startDate;
    private Date endDate;
    private SessionState state;
}
