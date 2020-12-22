import ru.netology.NotFoundException;
import ru.netology.Ticket;
import ru.netology.TicketsManager;
import ru.netology.TicketsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketsManagerTest {
    private TicketsRepository repository = new TicketsRepository();
    private TicketsManager manager = new TicketsManager(repository);

    Ticket ticket1 = new Ticket(1, 10000, "LED", "BRU", 120);
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
        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = manager.findAll("LED", "BRU");
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void searchByOnlyFromAirport() {
        Ticket[] expected = {ticket3, ticket5};
        Ticket[] actual = manager.findFromOrTo("VKO", "");
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void searchByOnlyToAirport() {
        Ticket[] expected = {ticket3, ticket4};
        Ticket[] actual = manager.findFromOrTo("", "IJK");
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void searchOneTicket (){
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.findAll("VKO", "IJK");
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }

    @Test
    void notFoundTicket (){
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("ALA", "IJK");
        assertArrayEquals(actual, expected);
        System.out.println(Arrays.toString(actual));
    }
    @Test
    void removeByNotId() {
        assertThrows(NotFoundException.class, () -> repository.removeById(4));
    }
}