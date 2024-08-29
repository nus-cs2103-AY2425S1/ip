package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void taskTypeTest() {
        assertEquals("D",
                new DeadlineTask("a", false, "2020-01-01 1800").getTaskType());
    }

    @Test
    public void descriptionTest() {
        assertEquals("D | 0 | return books | Feb 03 2019 6:00 PM",
                new DeadlineTask("return books ", false, "2019-02-03 1800").getDescription());
    }
}
