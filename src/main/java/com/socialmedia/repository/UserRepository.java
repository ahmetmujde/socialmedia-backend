package com.socialmedia.repository;

import com.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT count(*) > 0 FROM User where username = ?1", nativeQuery = true)
    boolean existByUsername(String username);
}
