package com.pyramidAcademy.pyramidAir.boardingPass;

import com.pyramidAcademy.pyramidAir.customResponse.CustomResponse;
import com.pyramidAcademy.pyramidAir.user.User;
import com.pyramidAcademy.pyramidAir.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ticket")
public class BoardingPassController {
    private final BoardingPassService service;
    @Autowired
    UserService userService;
    private final Random random = new Random();

    @Autowired
    BoardingPassController(BoardingPassService service ){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomResponse<BoardingPass>> create(@RequestBody BoardingPass pass, Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User currentUser = userService.getUserByUsername(userDetails.getUsername());
        pass.setPrice(random.nextInt(100) + 400 );
        pass.setEta(pass.getDepartureTime().minusHours(random.nextInt(3)+ 1));
        pass.setUser(currentUser);
        pass.setNumber(UUID.randomUUID());

        service.save(pass);
        return new ResponseEntity<>(new CustomResponse<>(pass, "ticket created", true), HttpStatus.OK);
    }

//    @GetMapping("/generate")
//    public ResponseEntity<CustomResponse<List<BoardingPass>>> generateTickets(@RequestBody BoardingPass pass){
//        int i = random.nextInt(5) + 1;
//        List<BoardingPass> list = new ArrayList<>();
//
//        while(i > 0){
//            list.add(new BoardingPass());
//            i--;
//        }
//    }





}
