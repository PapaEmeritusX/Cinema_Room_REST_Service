package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CinemaController {
    Cinema cinemaRoom = new Cinema();
    Error error = new Error("Fail!");
    List<Seat> seatList = cinemaRoom.getAvailableSeats();


    @PostMapping("/purchase")
    public Seat buyTicket(@RequestParam int row, @RequestParam int column) {
        int index = row * 9 - (9 - column) - 1;
        if (row > 9 || row < 0 || column > 9 || column < 0) {
            throw new SeatNotFoundException();
        } else if (seatList.get(index).getPrice() == 0) {

            seatList.get(index).defPrice();
            Seat purchased = seatList.get(index);
            return purchased;
        } else {
            throw new AlreadyPurchasedException();
        }
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    class SeatNotFoundException extends RuntimeException {
        private String error ;

        public SeatNotFoundException() {
            this.error = "The number of a row or a column is out of bounds!";
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    class AlreadyPurchasedException extends RuntimeException {
        private String error = "The ticket has been already purchased!";

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }


    @GetMapping("/seats")
    public Cinema getCinema() {
        return this.cinemaRoom;
    }
}
