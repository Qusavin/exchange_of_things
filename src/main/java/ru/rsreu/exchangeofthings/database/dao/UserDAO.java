package ru.rsreu.exchangeofthings.database.dao;

import ru.rsreu.exchangeofthings.persistent.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface for user dao.
 */
public interface UserDAO {
    /**
     * Find all users.
     *
     * @return List of users
     */
    List<User> findAll();

    /**
     * Find user by id.
     *
     * @param id User id
     * @return User
     */
    Optional<User> findById(int id);

    /**
     * Find user by username.
     *
     * @param username Username
     * @return User
     */
    Optional<User> findByUsername(String username);

    /**
     * Save user.
     *
     * @param user User
     * @return Saved user
     */
    Optional<User> save(User user);

    /**
     * Update user.
     *
     * @param user User
     * @return Updated user
     */
    Optional<User> update(User user);

    /**
     * Delete user by id.
     *
     * @param userId User id
     */
    void deleteById(int userId);

    /**
     * Update user is blocked status.
     *
     * @param userId User id
     */
    void updateIsBlocked(int userId);
}
