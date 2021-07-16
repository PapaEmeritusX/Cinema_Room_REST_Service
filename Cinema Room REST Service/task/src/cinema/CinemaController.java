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
    List<Seat> seatList = cinemaRoom.getAvailableSeats();


    @PostMapping("/purchase")
    public ResponseEntity<?> buyTicket(@RequestBody Seat book) {
        int index = book.getRow() * row - (row - book.getColumn()) - 1;
        if (book.getRow() > 9 || book.getRow() < 0 || book.getColumn() > 9 || book.getColumn() < 0) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new Error("The number of a row or a column is out of bounds!"));
        } else if (seatList.get(index).isAvailable()) {
            seatList.get(index).setAvailable(false);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(seatList.get(index));

        } else {
            return new ResponseEntity<>(new Error("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }

    }

//    @PostMapping("/purchase_test")
//    public ResponseEntity<?> buyTicketTest(@RequestParam int row, @RequestParam int column) {
//        int index = row * 9 - (9 - column) - 1;
//        if (row > 9 || row < 0 || column > 9 || column < 0) {
//            return new ResponseEntity<>(new Error("The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
//        } else if (seatList.get(index).getPrice() == 0) {
//
//            seatList.get(index).defPrice();
//            Seat purchased = seatList.get(index);
//            return new ResponseEntity<Seat>(purchased, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(new Error("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
//        }
//    }


    @GetMapping("/seats")
    public Cinema getCinema() {
        this.cinemaRoom.setAvailableSeats(seatList.stream()
                .filter(seat -> seat.isAvailable())
                .collect(Collectors.toCollection(ArrayList::new)));

        return this.cinemaRoom;
    }
}
