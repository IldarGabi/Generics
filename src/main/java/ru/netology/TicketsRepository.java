package ru.netology;

public class TicketsRepository {
    private Ticket[] flightTypes = new Ticket[0];

    public void save(Ticket ticket) {
        int length = flightTypes.length + 1;
        Ticket[] tmp = new Ticket[length];
        System.arraycopy(flightTypes, 0, tmp, 0, flightTypes.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = ticket;
        flightTypes = tmp;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(id + " not found");
        }
        int length = flightTypes.length - 1;
        Ticket[] tmp = new Ticket[length];
        int index = 0;
        for (Ticket ticket : flightTypes) {
            if (ticket.getId() != id) {
                tmp[index] = ticket;
                index++;
            }
        }
        flightTypes = tmp;
    }

    public Ticket findById(int id) {
        for (Ticket item : flightTypes) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Ticket[] getAll() {
        return flightTypes;
    }
}
