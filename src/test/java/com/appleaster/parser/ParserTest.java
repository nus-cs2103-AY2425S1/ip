package com.appleaster.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.appleaster.command.Command;
import com.appleaster.command.CommandType;
import com.appleaster.exception.AppleasterException;
import com.appleaster.task.Todo;
import com.appleaster.task.Deadline;
import com.appleaster.task.Event;

import java.time.LocalDate;

class ParserTest {

    @Test
    void testParseTodo() throws AppleasterException {
        Command command = Parser.parseCommand("todo Buy groceries");
        assertNotNull(command);
        assertEquals(CommandType.TODO, command.getType());
        assertTrue(command.getTask() instanceof Todo);
        assertEquals("Buy groceries", command.getTask().getDescription());
    }

    @Test
    void testParseTodoEmptyDescription() {
        assertThrows(AppleasterException.class, () -> {
            Parser.parseCommand("todo ");
        });
    }

    @Test
    void testParseDeadline() throws AppleasterException {
        Command command = Parser.parseCommand("deadline Submit report /by 2024-09-16 1400");
        assertNotNull(command);
        assertEquals(CommandType.DEADLINE, command.getType());
        assertTrue(command.getTask() instanceof Deadline);
        assertEquals("Submit report", command.getTask().getDescription());
    }

    @Test
    void testParseEvent() throws AppleasterException {
        Command command = Parser.parseCommand("event Team meeting /from 2024-09-16 1000 /to 2024-09-16 1130");
        assertNotNull(command);
        assertEquals(CommandType.EVENT, command.getType());
        assertTrue(command.getTask() instanceof Event);
        assertEquals("Team meeting", command.getTask().getDescription());
    }

    @Test
    void testParseViewSchedule() throws AppleasterException {
        Command command = Parser.parseCommand("view 2024-09-16");
        assertNotNull(command);
        assertEquals(CommandType.VIEW_SCHEDULE, command.getType());
        assertEquals(LocalDate.of(2024, 9, 16), command.getDate());
    }

    @Test
    void testParseViewScheduleInvalidDate() {
        assertThrows(AppleasterException.class, () -> {
            Parser.parseCommand("view 2024-13-45");
        });
    }
}