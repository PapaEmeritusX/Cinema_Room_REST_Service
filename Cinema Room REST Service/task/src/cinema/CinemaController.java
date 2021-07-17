package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CinemaController {
    int row = 9;
    int column = 9;

    Cinema cinemaRoom = new Cinema(row, column);
    Statistics stats = cinemaRoom.getStats();
    List<Seat> seatList = cinemaRoom.getAvailableSeats();

    {
        stats.setCapacity(seatList.size());
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buyTicket(@RequestBody Seat book) {
        int index = book.getRow() * row - (row - book.getColumn()) - 1;
        if (book.getRow() > this.row || book.getRow() < 0 || book.getColumn() > this.column || book.getColumn() < 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Error("The number of a row or a column is out of bounds!"));
        } else if (seatList.get(index).isAvailable()) {
            seatList.get(index).setAvailable(false);
            Purchase ticket = new Purchase();
            ticket.defToken();
            ticket.setTicket(seatList.get(index));
            cinemaRoom.addSoldTicket(ticket.getToken(), ticket.getTicket());

            int income = this.stats.getIncome() + ticket.getTicket().getPrice();
            int sold = this.stats.getSold() + 1;
            int capacity = this.stats.getCapacity() - 1;

            stats.setIncome(income);
            stats.setSold(sold);
            stats.setCapacity(capacity);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ticket);

        } else {
            return new ResponseEntity<>(new Error("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Purchase token) {
        if (cinemaRoom.getSoldTickets().containsKey(token.getToken())) {

            ReturnedTicket returnedTicket = new ReturnedTicket();
            returnedTicket.setTicket(cinemaRoom.getSoldTickets().get(token.getToken()));

            int row = cinemaRoom.getSoldTickets().get(token.getToken()).getRow();
            int column = cinemaRoom.getSoldTickets().get(token.getToken()).getColumn();

            cinemaRoom.getSeat(row,column).setAvailable(true); // the ticket is available to be purchased again
            cinemaRoom.getSoldTickets().remove(token.getToken()); // the ticket remove from the sold

            int income = this.stats.getIncome() - returnedTicket.getTicket().getPrice();
            int sold = this.stats.getSold() - 1;
            int capacity = this.stats.getCapacity() + 1;

            stats.setIncome(income);
            stats.setSold(sold);
            stats.setCapacity(capacity);

            return ResponseEntity
                    .status(HttpStatus.OK).body(returnedTicket);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(new Error("Wrong token!"));
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> showStats(@RequestParam(value = "password", required = false) String password) {
        if (cinemaRoom.getPassword().equals(password)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(cinemaRoom.getStats());
        } else {
            System.out.println("UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Error("The password is wrong!"));
        }
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        this.cinemaRoom.setAvailableSeats(seatList.stream()
                .filter(seat -> seat.isAvailable())
                .collect(Collectors.toCollection(ArrayList::new)));

        return this.cinemaRoom;
    }
}
