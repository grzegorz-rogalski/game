package com.example.game.repositiories;

import com.example.game.model.Session;
import com.example.game.model.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    @Query("SELECT s FROM Session s WHERE (:userId is null OR s.user.id = :userId) AND (:startDate is null OR s.startDate = :startDate) " +
            "AND (:endDate is null OR s.endDate = :endDate) AND (:state is null OR s.state = :state) ")
    List<Session> findAllByCriteria(Long userId, String startDate, String endDate, SessionState state);
}
