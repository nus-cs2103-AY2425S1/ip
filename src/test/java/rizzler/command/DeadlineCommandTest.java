package rizzler.command;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import rizzler.RizzlerException;

public class DeadlineCommandTest {

    @Test
    public void deadlineCommandConstructor_correctInput_deadlineObject() {
        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("deadlineDescription", "0001-05-25"));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("deadlineDescription", "3045-01-12"));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("deadlineDescription", "2024-12-01"));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("deadlineDescription", "tomorrow"));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("description", "i'm not sure"));
    }

    @Test
    public void deadlineCommandConstructor_correctInputWithWhitespace_deadlineObject() {
        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("     description", "0001-05-25"));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("description      ", "fakeDeadline"));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("    description    ", "fakeDeadline"));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("  description ", "  fakeDeadline  otherText "));

        assertInstanceOf(DeadlineCommand.class, new DeadlineCommand("description", "    fakeDeadline   "));
    }

    @Test
    public void deadlineCommandConstructor_missingArguments_rizzlerException() {
        assertThrows(RizzlerException.class, () -> new DeadlineCommand("", ""));

        assertThrows(RizzlerException.class, () -> new DeadlineCommand("realDescription", ""));

        assertThrows(RizzlerException.class, () -> new DeadlineCommand("", "realDeadline"));

        assertThrows(RizzlerException.class, () -> new DeadlineCommand("", "2024-12-12"));
    }
}
