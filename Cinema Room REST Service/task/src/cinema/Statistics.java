package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {

    @JsonProperty(value = "current_income")
    private int income;
    @JsonProperty(value = "number_of_available_seats")
    private int capacity;
    @JsonProperty(value = "number_of_purchased_tickets")
    private int sold;

    public Statistics() {

    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}
