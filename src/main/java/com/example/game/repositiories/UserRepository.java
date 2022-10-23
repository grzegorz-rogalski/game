package com.example.game.repositiories;

import com.example.game.model.Session;
import com.example.game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT CASE WHEN (COUNT(session) > 0)  THEN true ELSE false END " +
            "FROM Session session WHERE session.user.id = :userId AND session.endDate is null")
    boolean isSessionPending(long userId);

    @Query("SELECT session FROM Session session WHERE session.user.id = :userId AND session.endDate is null ")
    Session getActiveSession(long userId);
}
