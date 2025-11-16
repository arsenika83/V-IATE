package com.AVASPP.VIATE.repository;

import com.AVASPP.VIATE.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
