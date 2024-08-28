package com.appleaster.parser;

import com.appleaster.command.Command;
import com.appleaster.command.CommandType;
import com.appleaster.exception.AppleasterException;
import com.appleaster.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseCommand_validTodoCommand_returnsTodoCommand() throws AppleasterException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("todo Buy groceries");
        
        assertEquals(CommandType.TODO, command.getType());
        assertTrue(command.getTask() instanceof Todo);
        assertEquals("Buy groceries", command.getTask().getDescription());
    }

    @Test
    void parseCommand_invalidCommand_throwsException() {
        Parser parser = new Parser();
        
        Exception exception = assertThrows(AppleasterException.class, () -> {
            parser.parseCommand("invalid command");
        });
        
        String expectedMessage = "I don't recognize that command.";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.contains(expectedMessage));
    }
}