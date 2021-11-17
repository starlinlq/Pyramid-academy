package com.pyramidAcademy.pyramidAir.boardingPass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardingPassService {
    private final BoardingPassRepository repository;

    @Autowired
    BoardingPassService(BoardingPassRepository repository){
        this.repository =repository;
    }

    public BoardingPass save(BoardingPass pass){
        repository.save(pass);
        return pass;
    }

}
