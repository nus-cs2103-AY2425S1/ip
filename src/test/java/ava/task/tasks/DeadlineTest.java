package ava.task.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




class DeadlineTest {

    @Test
    void testToString() {
        Deadline deadline = new Deadline("Submit report", "2023-12-31T23:59");
        String expected = "Deadline: ❌ Pending | Submit report [By :Sun, 31 Dec 2023 23:59:00]";
        assertEquals(expected, deadline.toString());
    }

    @Test
    void serialize() {
        Deadline deadline = new Deadline("Submit report", "2023-12-31T23:59");
        String expected = "D,0,Submit report,2023-12-31T23:59";
        assertEquals(expected, deadline.serialize());
    }
}
