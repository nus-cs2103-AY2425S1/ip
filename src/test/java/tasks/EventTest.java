package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.EchoException;

public class EventTest {
    @Test
    public void test_creat_event() {
        try {
            Task expectedEvent = new Event("read book", "2023/03/02 08:00", "2023/03/02 20:00");
            Task actualEvent = Task.createTask("event read book /from 2023/03/02 08:00 /to 2023/03/02 20:00");
            assertEquals(expectedEvent.toString(), actualEvent.toString());
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_unmark_event() {
        try {
            Task actualEvent = Task.createTask("event read book /from 2023/03/02 08:00 /to 2023/03/02 20:00");
            Task expectedEvent = new Event("read book", "2023/03/02 08:00", "2023/03/02 20:00");
            actualEvent.setMark();
            actualEvent.setUnmark();
            assertEquals(actualEvent.toString(), expectedEvent.toString());
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }
}
