package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.EchoException;

public class EventTest {
    @Test
    public void test_creat_event() {
        try {
            Task eventA = new Event("eA", "2023/03/02 08:00", "2023/03/02 20:00");
            Task eventB = Task.createTask("event eA /from 2023/03/02 08:00 /to 2023/03/02 20:00");
            assertEquals(eventA.toString(), eventB.toString());
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_unmark_event() {
        try {
            Task eventA = new Event("eA", "2023/03/02 08:00", "2023/03/02 20:00");
            eventA.setMark();
            Task eventB = Task.createTask("event eA /from 2023/03/02 08:00 /to 2023/03/02 20:00");
            eventA.setUnmark();
            assertEquals(eventA.toString(), eventB.toString());
        } catch (EchoException e) {
            System.out.println(e.getMessage());
        }
    }
}
