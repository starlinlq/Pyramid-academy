package com.crave.crave.service;

import com.crave.crave.dto.ProfileDTO;
import com.crave.crave.model.Profile;
import com.crave.crave.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public void save(Profile profile) {
        profileRepo.save(profile);
    }

    public ProfileDTO findById(long id){
        Optional<Profile> profile = profileRepo.findById(id);
        return profile.map(ProfileMapper::toProfileDTO).orElse(null);
    }


    static class ProfileMapper{
        public static ProfileDTO toProfileDTO(Profile profile){
           return ProfileDTO.builder()
                    .name(profile.getName())
                    .photoUrl(profile.getPhotoUrl())
                    .id(profile.getId())
                    .status(profile.getStatus())
                    .userId(profile.getUser().getId())
                    .build();

        }
    }
}
