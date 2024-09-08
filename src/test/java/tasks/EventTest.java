package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.DukeException;

public class EventTest {
    @Test
    public void test_creat_event() {
        try {
            Task eventA = new Event("eA", "2pm Sunday", "2pm Monday");
            Task eventB = Task.createTask("event eA /from 2pm Sunday /to 2pm Monday");
            assertEquals(eventA.toString(), eventB.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_unmark_event() {
        try {
            Task eventA = new Event("eA", "2pm Sunday", "2pm Monday");
            eventA.setMark();
            Task eventB = Task.createTask("event eA /from 2pm Sunday /to 2pm Monday");
            eventA.setUnmark();
            assertEquals(eventA.toString(), eventB.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
