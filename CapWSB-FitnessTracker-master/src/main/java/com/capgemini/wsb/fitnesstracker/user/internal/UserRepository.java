package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their exact email address.
     *
     * @param email The email address to search for.
     * @return An {@link Optional} containing the found user or {@link Optional#empty()} if no match is found.
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds users by a part of their email address, ignoring case.
     *
     * @param email The fragment of the email address to search for.
     * @return A list of {@link User} entities that match the search criteria.
     */
    List<User> findByEmailContainingIgnoreCase(String email);

    /**
     * Finds users who are older than a certain age.
     *
     * @param cutoffDate The date before which the user must have been born to be included in the results.
     * @return A list of {@link User} entities that match the search criteria.
     */
    @Query("SELECT u FROM User u WHERE u.birthdate <= :cutoffDate")
    List<User> findUsersOlderThan(LocalDate cutoffDate);

}
