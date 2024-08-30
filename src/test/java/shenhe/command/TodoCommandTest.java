package shenhe.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;
import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.task.Todo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class TodoCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(Paths.get("test/data/shenhe.txt").toString());
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecute_validInput_addsTodoTask() {
        // Arrange
        String userInput = "todo Read book";
        TodoCommand command = new TodoCommand(userInput);

        // Act
        try {
            command.execute(tasks, ui, storage);
        } catch (EmptyTaskDescriptionException e) {
            fail("Should not throw EmptyTaskDescriptionException");
        }

        // Assert
        assertEquals(1, tasks.getSize());
        assertTrue(tasks.getTask(0) instanceof Todo);
        assertEquals("[T][0] Read book", tasks.getTask(0).toString());
        assertTrue(outputStream.toString().contains("Now you have 1 tasks in the list."));
    }

    @Test
    public void testExecute_emptyDescription_throwsException() {
        // Arrange
        String userInput = "todo ";
        TodoCommand command = new TodoCommand(userInput);

        // Act & Assert
        assertThrows(EmptyTaskDescriptionException.class, () -> command.execute(tasks, ui, storage));
    }

    @Test
    public void testIsExit_returnsFalse() {
        // Arrange
        TodoCommand command = new TodoCommand("todo Read book");

        // Act
        boolean isExit = command.isExit();

        // Assert
        assertFalse(isExit);
    }
}

