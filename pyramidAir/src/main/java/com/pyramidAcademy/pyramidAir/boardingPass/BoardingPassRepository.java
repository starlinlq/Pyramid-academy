package com.pyramidAcademy.pyramidAir.boardingPass;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingPassRepository extends CrudRepository<BoardingPass, Long> {
}
