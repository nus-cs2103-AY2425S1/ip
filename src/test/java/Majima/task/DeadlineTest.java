package Majima.task;

import Majima.MajimaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }

    @Test
    public void testDeadline() {
        String description = "Take out the trash";
        String deadline = "31-12-2024 2359";

        Deadline task = null;

        try {
            task = new Deadline(description, deadline);
        } catch (MajimaException e) {
            System.out.println("Something went wrong during the testing of Deadline.java");
            e.printStackTrace();
        }

        assertEquals(description, task.getDescription());
        assertEquals("[D][ ] Take out the trash (by: 31-12-2024 2359)", task.toString());
    }
}
