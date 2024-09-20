package swbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import swbot.command.InputHandler;
import swbot.command.Storage;
import swbot.tasks.Deadline;
import swbot.tasks.Event;
import swbot.tasks.Task;
import swbot.tasks.Todo;

public class SwbotTest {
    @Test
    public void mark_task_success() {
        Task task = new Todo("test task");
        task.setDone(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void deadline_dateTime_exceptionThrown() {
        try {
            Task task = new Deadline("do homework", "2 12 2004");
        } catch (BuzzException e) {
            assertEquals("WRONG!!! The date and time format should be d/M/yyyy HHmm (e.g., 5/10/2024 0500).",
                    e.getMessage());
        }
    }

    @Test
    public void deleteHandleTest_taskNotExist_throwsException() {
        ArrayList<Task> data = new ArrayList<>();
        Storage storage = new Storage("/path/to/storage");
        InputHandler inputHandler = new InputHandler(data, storage);

        String input = "delete 2";

        assertThrows(BuzzException.class, () -> inputHandler.deleteHandle(input));
    }

    @Test
    public void deleteHandleTest_validInput_taskDeleted() throws BuzzException {
        ArrayList<Task> data = new ArrayList<>();
        data.add(new Todo("Test todo"));
        Storage storage = new Storage("/path/to/storage");
        InputHandler inputHandler = new InputHandler(data, storage);

        String input = "delete 1";
        String expectedOutput = "*POOF* Circuits fried! Deleted the mission.\n"
                + "[T][ ] Test todo\n"
                + "You currently have 0 missions available *reeeee*";

        assertEquals(expectedOutput, inputHandler.deleteHandle(input));
    }

    @Test
    public void deleteHandleTest_negativeIndex_throwsException() {
        ArrayList<Task> data = new ArrayList<>();
        data.add(new Todo("Test todo"));
        Storage storage = new Storage("/path/to/storage");
        InputHandler inputHandler = new InputHandler(data, storage);

        String input = "delete -1";

        assertThrows(BuzzException.class, () -> inputHandler.deleteHandle(input));
    }

    @Test
    void testLoadTasks_emptyFile() throws IOException {
        PrintWriter writer = new PrintWriter("emptyFile.txt");
        writer.print("");
        writer.close();

        Storage storage = new Storage("emptyFile.txt");
        ArrayList<Task> tasks = storage.loadTasks();
        assertTrue(tasks.isEmpty());

        // cleanup
        File file = new File("emptyFile.txt");
        file.delete();
    }

    @Test
    public void toFileFormat_notDone_success() throws BuzzException {
        Event e = new Event("Test Task", "5/10/2024 0500", "6/10/2024 1200");
        assertEquals("E | 0 | Test Task | 5/10/2024 0500 | 6/10/2024 1200", e.toFileFormat());
    }

    /**
     * This test checks if the method toFileFormat() correctly formats a string for an event task that is already done.
     */
    @Test
    public void toFileFormat_done_success() throws BuzzException {
        Event e = new Event("Test Task", "5/10/2024 0500", "6/10/2024 1200");
        e.setDone(true);
        assertEquals("E | 1 | Test Task | 5/10/2024 0500 | 6/10/2024 1200", e.toFileFormat());
    }

    @Test
    public void testGetResponseByeInput() {
        R2D2 bot = new R2D2();
        String response = bot.getResponse("bye");
        assertEquals("Bye. Hope to see you again soon! *bzzzt*\n", response);
    }

    @Test
    public void testFindHandle_withNoMatchingTasks_returnsNoResultsErrorMessage() {
        ArrayList<Task> data = new ArrayList<>();
        data.add(new Todo("Test task 1"));
        data.add(new Todo("Test task 2"));
        data.add(new Todo("Test task 3"));

        Storage storage = new Storage("filePath");
        InputHandler handler = new InputHandler(data, storage);

        String input = "find noMatch";
        String expectedOutput = "Sorry boss can't find anything :(";

        try {
            String result = handler.findHandle(input);
            assertEquals(expectedOutput, result);
        } catch (BuzzException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testFindHandle_withMatchingTasks_returnsMatchedTasks() {
        ArrayList<Task> data = new ArrayList<>();
        data.add(new Todo("Test task 1"));
        data.add(new Todo("Test task 2"));
        data.add(new Todo("Matched task"));

        Storage storage = new Storage("filePath");
        InputHandler handler = new InputHandler(data, storage);

        String input = "find Matched";
        String expectedOutput = "I have found a few matches sir! *wooop*\n1.[T][ ] Matched task\n";

        try {
            String result = handler.findHandle(input);
            assertEquals(expectedOutput, result);
        } catch (BuzzException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testFindHandle_withEmptyDescription_throwsBuzzException() {
        ArrayList<Task> data = new ArrayList<>();
        data.add(new Todo("Test task 1"));
        Storage storage = new Storage("filePath");
        InputHandler handler = new InputHandler(data, storage);

        String input = "find ";
        assertThrows(BuzzException.class, () -> handler.findHandle(input),
                "NOOO! Description is empty *crash*");
    }
    @Test
    public void testMarkHandle_taskIndexOutOfRange() {
        ArrayList<Task> data = new ArrayList<>();
        Task task = new Todo("do something");
        data.add(task);

        Storage mockStorage = new Storage("mockfilePath");
        InputHandler inputHandler = new InputHandler(data, mockStorage);

        assertThrows(BuzzException.class, () -> inputHandler.markHandle("mark 2"));
    }
}
