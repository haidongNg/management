package com.nhd.management.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nhd.management.models.AuthorizationToken;

@Repository
public interface IAuthorizationTokenRepository extends JpaRepository<AuthorizationToken, Long> {

  Optional<AuthorizationToken> findByToken(String token);

}
