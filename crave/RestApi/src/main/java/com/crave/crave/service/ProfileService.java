package com.crave.crave.service;

import com.crave.crave.dto.ProfileDTO;
import com.crave.crave.model.Profile;

import java.util.Optional;

public interface ProfileService {
    void save(Profile profile);
    ProfileDTO findById(long id);

}
