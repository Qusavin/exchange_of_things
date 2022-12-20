package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.persistent.entity.Session;

import java.util.List;
import java.util.Optional;

/**
 * Interface for session dao.
 */
public interface SessionDAO {
    /**
     * Find all sessions.
     *
     * @return List of sessions
     */
    List<Session> findAll();

    /**
     * Save session.
     *
     * @param session Session
     */
    void save(Session session);

    /**
     * Delete session by user id.
     *
     * @param userId User id
     */
    void deleteSession(int userId);

    /**
     * Get session by user id.
     *
     * @param userId User id
     * @return Session
     */
    Optional<Session> getSession(int userId);
}
