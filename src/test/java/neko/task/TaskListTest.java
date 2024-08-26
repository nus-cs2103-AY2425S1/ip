package neko.task;

import neko.NekoException;
import neko.Parser;
import neko.Storage;
import neko.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskListTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;
    private PrintStream printStream;
    @TempDir
    File tempDir;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList(new ArrayList<>(100));
        File tempFile = new File(tempDir, "neko.txt");
        storage = new Storage(tempFile.getAbsolutePath());
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    public void testAddTodoTask() throws IOException, NekoException {
        String simulatedUserInput = "Sample Todo Task\n";
        inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        ui = new Ui(inputStream, printStream);
        tasks.addTask("1", ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.getTask(0) instanceof Todo);

        String expectedOutput = "Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n " +
                tasks.getTask(0) + "\nNow you have 1 tasks in your list meow";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    public void testAddDeadlineTask() throws IOException, NekoException {
        String simulatedUserInput = "Sample Deadline\n2024-01-01T13:00";
        inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        ui = new Ui(inputStream, printStream);
        tasks.addTask("2", ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.getTask(0) instanceof Deadline);

        String expectedOutput = "Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n" +
                " [D][ ] Sample Deadline (by: Mon, 1 Jan 2024 1:00pm)\n" +
                "Now you have 1 tasks in your list meow";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    public void testAddEventTask() throws IOException, NekoException {
        String simulatedUSerInput = "Sample Event\n2024-01-01T13:00\n2024-01-02T13:00";
        inputStream = new ByteArrayInputStream(simulatedUSerInput.getBytes());

        ui = new Ui(inputStream, printStream);
        tasks.addTask("3", ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.getTask(0) instanceof Event);

        String expectedOutput = "Purrfect! I've added this task meow ฅ/ᐠᓀ ﻌ ᓂマ\n" +
                " [E][ ] Sample Event (from: Mon, 1 Jan 2024 1:00pm to: Tue, 2 Jan 2024 1:00pm)\n" +
                "Now you have 1 tasks in your list meow";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    public void testAddTaskInvalidInput() throws IOException {
        inputStream = new ByteArrayInputStream("".getBytes());

        ui = new Ui(inputStream, printStream);
        tasks.addTask("4", ui, storage);

        String expectedOutput = "Oops /ᐠ > ˕ <マ, that's not a valid option meow! Please enter 1, 2, or 3 meow!";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

    @Test
    public void testGetTask() throws NekoException, IOException {
        String simulatedUserInput = "Sample Todo Task\n";
        inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        ui = new Ui(inputStream, printStream);
        tasks.addTask("1", ui, storage);

        assertEquals("[T][ ] Sample Todo Task", tasks.getTask(0).toString());
    }

    @Test
    public void testCheckValidIndexWithInvalidIndex() {
        try {
            tasks.checkValidIndex(-1);
            fail();
        } catch (NekoException e) {
            assertEquals("Meow /ᐠ > ˕ <マ Invalid task number meow!", e.getMessage());
        }
    }

    @Test
    public void testCheckValidIndexWhenListIsEmpty() {
        try {
            tasks.checkValidIndex(1);
            fail();
        } catch (NekoException e) {
            assertEquals("Meow /ᐠ > ˕ <マ You don't have any tasks yet meow!", e.getMessage());
        }
    }





}
