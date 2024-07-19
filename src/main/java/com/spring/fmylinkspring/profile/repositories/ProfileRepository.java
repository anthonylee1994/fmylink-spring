package com.spring.fmylinkspring.profile.repositories;

import com.spring.fmylinkspring.profile.entities.Profile;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author anthonylee
 */
@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    boolean existsByUserId(@NotNull Integer userId);

    Optional<Profile> findByUserId(Integer userId);

    Optional<Profile> findByUsername(String username);
}
