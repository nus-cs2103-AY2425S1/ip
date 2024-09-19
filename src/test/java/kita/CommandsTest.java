package kita;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandsTest {

    private static final Pattern toDoPattern = Pattern.compile("^todo (.+)$");
    private Commands commandsExecutor;
    private TaskList tasks;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        Storage saveSys;
        try {
            saveSys = new Storage();
            tasks = new TaskList(new ArrayList<>());
        } catch (Exception e) {
            System.out.println("Oh no, Kita failed to create/read from the save file successfully :c");
            System.out.println(e);
            return;
        }

        System.setOut(new PrintStream(outContent));
        commandsExecutor = new Commands(tasks, saveSys);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Should create todo task and mark task as completed")
    public void notFoundCommand() throws IOException {
        Matcher todoMatcher = toDoPattern.matcher("todo hello world");
        todoMatcher.matches(); // need to run the matching once first
        commandsExecutor.createToDo(todoMatcher);
        // Assert that task exists inside TaskList
        assertNotNull(this.tasks.getTask(0));

        // Assert the task is marked
        commandsExecutor.mark("mark 1");
        assertTrue(outContent.toString().contains("Nice! I've marked these tasks as done:"));
        assertTrue(this.tasks.getTask(0).getCompleted());
    }
}
