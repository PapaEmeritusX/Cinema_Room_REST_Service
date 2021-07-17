package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {
    @JsonProperty(value = "returned_ticket")
    private Seat ticket;

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
