package soju.commands;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import soju.SojuException;

public class TodoCommandTest {

    @Test
    public void todoConstructor_emptyDescription_sojuExceptionThrown() {
        // Test if an exception is thrown when the description is empty
        assertThrowsExactly(SojuException.class, () -> new TodoCommand("todo"));
    }

    @Test
    public void todoConstructor_blankDescription_sojuExceptionThrown() {
        // Test if an exception is thrown when the description is blank
        assertThrowsExactly(SojuException.class, () -> new TodoCommand("todo "));
    }

    @Test
    public void todoConstructor_normalInput_constructedCorrectly() throws SojuException {
        // Test if the command is constructed correctly for valid input
        TodoCommand todoCommand = new TodoCommand("todo read a book");
        assertNotNull(todoCommand);
    }
}
