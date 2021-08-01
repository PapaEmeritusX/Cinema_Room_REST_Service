package cinema.business;

import java.util.UUID;

public class Purchase {
    private String token;
    private Seat ticket;

    public Purchase(String token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public Purchase() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void defToken() {
        UUID uuid = UUID.randomUUID();
        this.token = uuid.toString();
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
