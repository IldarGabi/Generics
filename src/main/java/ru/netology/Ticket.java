package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket implements Comparable<Ticket>{
    private int id;
    private int price;
    private String fromAirport;
    private String toAirport;
    private int timeInFly;

    @Override
    public int compareTo(Ticket o) {
        return price - o.getPrice();
    }
}
