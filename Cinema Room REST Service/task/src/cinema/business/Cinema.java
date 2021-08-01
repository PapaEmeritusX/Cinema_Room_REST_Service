package cinema.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cinema {
    @JsonProperty("total_rows")
    private final int totalRows;
    @JsonProperty("total_columns")
    private final int totalColumns;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    @JsonIgnore
    private Map<String,Seat> soldTickets;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Statistics stats;

    {
        this.stats = new Statistics();
        this.password = "super_secret";
        this.soldTickets = new HashMap<>();
    }

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new ArrayList<>();
        initAvailableSeats();
    }


    public void updateStats() {

        this.stats.setCapacity(availableSeats.size());
        for (Seat soldSeat: this.soldTickets.values()) {
            this.stats.setIncome(this.stats.getIncome() + soldSeat.getPrice());
        }
        this.stats.setSold(this.soldTickets.size());
    }
    public Statistics getStats() {
        return stats;
    }

    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void addSoldTicket(String token, Seat ticket) {
        this.soldTickets.put(token, ticket);
    }

    public Map<String, Seat> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Map<String, Seat> soldTickets) {
        this.soldTickets = soldTickets;
    }

    public Seat getSeat(int row, int column) {
        int index = this.totalRows * row - (this.totalColumns - column);
        return this.availableSeats.get(index);
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
