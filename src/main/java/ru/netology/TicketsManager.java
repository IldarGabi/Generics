package ru.netology;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;

@Data
public class TicketsManager {
    private TicketsRepository repository;

    public TicketsManager(TicketsRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket tickets) {
        repository.save(tickets);
    }

    public Ticket[] findAll(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] newSearch = new Ticket[0];
        for (Ticket ticket : repository.getAll()) {
            if (ticket.getFromAirport().equals(from) && ticket.getToAirport().equals(to)) {
                int length = newSearch.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(newSearch, 0, tmp, 0, newSearch.length);
                tmp[tmp.length - 1] = ticket;
                newSearch = tmp;
            }
        }
        Arrays.sort(newSearch, comparator);
        return newSearch;
    }

    public Ticket[] findFromOrTo(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] newSearch = new Ticket[0];
        for (Ticket ticket : repository.getAll()) {
            if (ticket.getFromAirport().equals(from) || ticket.getToAirport().equals(to)) {
                int length = newSearch.length + 1;
                Ticket[] tmp = new Ticket[length];
                System.arraycopy(newSearch, 0, tmp, 0, newSearch.length);
                tmp[tmp.length - 1] = ticket;
                newSearch = tmp;
            }
        }
        Arrays.sort(newSearch, comparator);
        return newSearch;
    }
}

