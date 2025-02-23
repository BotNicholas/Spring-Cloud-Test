package org.botnicholas.projects.authenticationservice.repositories;

import org.botnicholas.projects.authenticationservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
