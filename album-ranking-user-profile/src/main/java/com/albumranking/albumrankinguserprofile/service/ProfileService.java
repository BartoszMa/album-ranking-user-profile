package com.albumranking.albumrankinguserprofile.service;

import com.albumranking.albumrankinguserprofile.models.Profile;
import com.albumranking.albumrankinguserprofile.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public boolean checkIfProfileExist(String userId) {
        return profileRepository.findByUserId(userId) != null;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.insert(profile);
    }

    public Profile getProfile(String userId) {
        return profileRepository.findByUserId(userId);
    }

}
