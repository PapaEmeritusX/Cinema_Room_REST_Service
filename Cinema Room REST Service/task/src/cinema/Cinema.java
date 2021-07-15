package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    @JsonProperty("total_rows")
    private final int totalRows;
    @JsonProperty("total_columns")
    private final int totalColumns;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    public Cinema() {
        this.totalRows = 9;
        this.totalColumns = 9;
        this.availableSeats = new ArrayList<>();
        initAvailableSeats();

    }
    public void setAvailableSeats() {

    }
    public void initAvailableSeats() {

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i,j));
            }
        }
    }
    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public String toString() {
        return this.totalRows + "\n" + this.totalColumns + " " + this.availableSeats;
    }
}
