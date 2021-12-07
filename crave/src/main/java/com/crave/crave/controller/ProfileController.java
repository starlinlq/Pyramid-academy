package com.crave.crave.controller;

import com.crave.crave.dto.ProfileDTO;
import com.crave.crave.exception.ProfileNotFoundException;
import com.crave.crave.model.Profile;
import com.crave.crave.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable long id) throws ProfileNotFoundException{
        ProfileDTO profile = profileService.findById(id);

       if(profile == null){
           throw new ProfileNotFoundException();
       }

       return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
