package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
public class Controller {
    Rom room = new Rom(9, 9);
    Statistics stats = new Statistics(81);
    Map<UUID, Seat> soldTickets = new ConcurrentHashMap<>();

    @GetMapping("/seats")
    public ResponseEntity<Rom> getSeats(){
        return new ResponseEntity<>(room, HttpStatus.OK);

    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase(@RequestBody Seat seat){
        var ticket = purchaseTicket(seat);

        if(ticket != null){
            UUID token = UUID.randomUUID();
            Purchased purchasedSeat = new Purchased(token, ticket);
            soldTickets.put(token, ticket);

            stats.setCurrentIncome(ticket.getPrice());
            stats.setNumberOfPurchaseTickets(1);
            stats.setNumberOfAvailableSeats(stats.getNumberOfAvailableSeats() - 1);

            return new ResponseEntity<>(purchasedSeat, HttpStatus.OK);

        } else if (seat.getRow() > 9 || seat.getColumn() > 9 || seat.getRow() < 1 || seat.getColumn() < 1){
           return new ResponseEntity<>(new TakenError("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(new TakenError("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);

    }


    @PostMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam(required = false) String password){
        if(Objects.equals(password, "super_secret")){
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } else
            return new ResponseEntity<>(new TakenError("The password is wrong!"), HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody Token token){
        Seat seat = soldTickets.get(token.getToken());
        if(seat != null){
            ReturnTicket ticket = new ReturnTicket(seat);
            soldTickets.remove(token.getToken());
            stats.setNumberOfAvailableSeats(stats.getNumberOfAvailableSeats() + 1);
            stats.setCurrentIncome(-(seat.getPrice()));
            stats.setNumberOfPurchaseTickets(-1);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else
            return new ResponseEntity<>(new TakenError("Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    private Seat purchaseTicket(Seat seat){
       try{
           Seat avl = room.getAvailable_seats().stream().filter(s->{return s.getColumn() == seat.getColumn() && s.getRow() == seat.getRow();}).collect(Collectors.toList()).get(0);
           var filtered =  room.getAvailable_seats().stream().filter(s->{return s.getColumn() != seat.getColumn() && s.getRow() != seat.getRow();}).collect(Collectors.toList());
           room.setAvailable_seats(filtered);
           return avl;
       } catch (IndexOutOfBoundsException ex){
           return null;
       }
    }



}
