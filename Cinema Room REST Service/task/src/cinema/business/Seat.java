package cinema.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;

import java.util.Objects;

public class Seat {
    @JsonProperty("row")
    private int row;
    @JsonProperty("column")
    private int column;
    @JsonProperty("price")
    private int price;

    @JsonIgnore
    private boolean available;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.available = true;
        defPrice();
    }
    public Seat() {

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isAvailable() {
        return available;
    }

    @JsonIgnore
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void defPrice() {

        this.price = this.row <= 4 ? 10 : 8;
    }

}
