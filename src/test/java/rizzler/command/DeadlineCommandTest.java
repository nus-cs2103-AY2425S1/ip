package rizzler.command;

import org.junit.jupiter.api.Test;
import rizzler.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DeadlineCommandTest {

    @Test
    public void deadlineCommand_correctInput_deadlineObject() {
        assertInstanceOf(Deadline.class, new Deadline("deadlineDescription", "1893-05-25"));

        assertInstanceOf(Deadline.class, new Deadline("deadlineDescription", "3045-01-12"));

        assertInstanceOf(Deadline.class, new Deadline("deadlineDescription", "2024-12-01"));
    }
}
