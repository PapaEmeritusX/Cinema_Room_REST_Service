package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    @JsonProperty("total_rows")
    private final int totalRows;
    @JsonProperty("total_columns")
    private final int totalColumns;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new ArrayList<>();
        initAvailableSeats();

    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void initAvailableSeats() {

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new Seat(i,j));
            }
        }
    }
    @Override
    public String toString() {
        return this.totalRows + "\n" + this.totalColumns + " " + this.availableSeats;
    }
}
