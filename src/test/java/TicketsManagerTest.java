import ru.netology.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketsManagerTest {
    private TicketsRepository repository = new TicketsRepository();
    private TicketsManager manager = new TicketsManager(repository);

    Ticket ticket1 = new Ticket(1, 10000, "LED", "BRU", 150);
    Ticket ticket2 = new Ticket(2, 20000, "LED", "BRU", 120);
    Ticket ticket3 = new Ticket(9, 5000, "VKO", "IJK", 90);
    Ticket ticket4 = new Ticket(8, 8000, "LED", "IJK", 120);
    Ticket ticket5 = new Ticket(7, 25000, "VKO", "DYU", 240);

    @BeforeEach
    void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    @Test
    void searchByFromAndToAirport() {
        Ticket[] expected = {ticket2, ticket1};
        Ticket[] actual = manager.findAll("LED", "BRU", new TicketComparator());
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void searchByOnlyFromAirport() {
        Ticket[] expected = {ticket3, ticket5};
        Ticket[] actual = manager.findFromOrTo("VKO", "", new TicketComparator());
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void searchByOnlyToAirport() {
        Ticket[] expected = {ticket3, ticket4};
        Ticket[] actual = manager.findFromOrTo("", "IJK", new TicketComparator());
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void searchOneTicket (){
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.findAll("VKO", "IJK", new TicketComparator());
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void notFoundTicket (){
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("ALA", "IJK", new TicketComparator());
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }
    @Test
    void removeByNotId() {
        assertThrows(NotFoundException.class, () -> repository.removeById(4));
    }
}