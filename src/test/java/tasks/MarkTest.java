package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.Duck;

public class MarkTest {
    @Test
    public void markTask_unmarked_success() throws Exception {
        Duck duck = new Duck();
        duck.getResponse("deadline test description /by 2024-08-30");
        assertEquals("Nice! I've marked this task as done:\n" +
                "  [D][X] test description (by: 30 AUGUST 2024)", duck.getResponse("mark 1"));
    }

    @Test
    public void markTask_marked_success() throws Exception {
        Duck duck = new Duck();
        duck.getResponse("deadline test description /by 2024-08-30");
        duck.getResponse("mark 1");
        assertEquals("Nice! I've marked this task as done:\n" +
                "  [D][X] test description (by: 30 AUGUST 2024)", duck.getResponse("mark 1"));
    }

    @Test
    public void markTask_outOfBounds_fail() throws Exception {
        Duck duck = new Duck();
        duck.getResponse("deadline test description /by 2024-08-30");
        assertEquals("usage: mark <task_number>\n" +
                "error: the following arguments are required: task_number\n" +
                "tip: Enter \"list\" to see the list of tasks and their numbers.\n" +
                "Index 2 is out of bounds. Index should be an integer from 1 to 1 (inclusive).",
                duck.getResponse("mark 2"));
    }

    @Test
    public void markTask_invalidIndex_fail() throws Exception {
        Duck duck = new Duck();
        duck.getResponse("deadline test description /by 2024-08-30");
        assertEquals("usage: mark <task_number>\n" +
                "error: the following arguments are required: task_number\n" +
                "tip: Enter \"list\" to see the list of tasks and their numbers.\n" +
                "<task_number> should be an integer.",
                duck.getResponse("mark test description"));
    }

    @Test
    public void markTask_missingIndex_fail() throws Exception {
        Duck duck = new Duck();
        duck.getResponse("deadline test description /by 2024-08-30");
        assertEquals("usage: mark <task_number>\n" +
                "error: the following arguments are required: task_number\n" +
                "tip: Enter \"list\" to see the list of tasks and their numbers.\n" +
                "<task_number> should be an integer.",
                duck.getResponse("mark"));
    }
}
