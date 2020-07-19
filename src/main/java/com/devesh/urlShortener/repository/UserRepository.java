package com.devesh.urlShortener.repository;

import com.devesh.urlShortener.models.User;
import com.devesh.urlShortener.models.UserKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User, UserKey> {
    boolean existsByUserKeyEmail(String email);
    User findByUserKeyEmail(String email);
}
