package com.albumranking.albumrankinguserprofile.repositories;

import com.albumranking.albumrankinguserprofile.models.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    Profile findByUserId(String userId);
}