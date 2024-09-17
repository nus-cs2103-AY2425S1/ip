package genji;

import genji.command.AddCommand;
import genji.command.DateCommand;
import genji.command.FindCommand;
import genji.task.Deadline;
import genji.task.Event;
import genji.task.TaskList;
import genji.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class to test parser class
 */
public class ParserTest {

    /**
     * Tests if parse method deals with invalid command properly
     */
    @Test
    public void testInvalidCommand() {
        try {
            assertEquals(true, Parser.parse("test"));
        } catch (GenjiException e) {
            assertEquals("Invalid command, try to start with \"todo\" \"deadline\" " +
                    "\"event\", type \"list\" \"date\", or type \"bye\" to end", e.getMessage());
        }
    }

    /**
     * Tests if parse method deals with valid command properly
     * @throws GenjiException Not expected to be thrown
     */
    @Test
    public void testByeCommand() throws GenjiException{
        assertEquals(true, Parser.parse("bye").isExit());
    }

    /**
     * Tests if parse method deals with invalid date input properly
     * Inputs an invalid command about event task
     */
    @Test
    public void testInvalidDate() {
        try {
            assertEquals(true, Parser.parse("event test /from  *to zero")
                    instanceof AddCommand);
            fail();
        } catch (GenjiException e) {
            assertEquals("Time period not provided or not in the proper form",
                    e.getMessage());
        }
    }

    /**
     * Tests if parse method deals with adding deadline properly
     * Parses and executes a add command and compare whether it is correct
     * @throws GenjiException Not expected to be thrown
     */
    @Test
    public void testAddDeadline() throws GenjiException {
        TaskList t = new TaskList();
        Ui u = new Ui();
        Storage s = new Storage("./data/Genji.txt");
        Parser.parse("deadline test /by 2024-09-03T11:00").execute(t, u, s);
        assertEquals("[D][ ] test (by: Sep 03 2024 11:00)", t.get(0).toString());
    }

    /**
     * Tests if parse method deals with deleting task properly
     * Adds some tasks to list and then parses and execute the delete command
     * Compare if the task is deleted properly
     * @throws GenjiException Not expected to be thrown
     */
    @Test
    public void testDeleteCommand() throws GenjiException {
        TaskList t = new TaskList();
        Ui u = new Ui();
        Storage s = new Storage("./data/Genji.txt");
        t.add(new ToDo("test1"));
        t.add(new ToDo("test2"));
        Parser.parse("delete 1").execute(t, u, s);
        assertEquals("[T][ ] test2", t.get(0).toString());
    }

    /**
     * Tests if parse method deals with checking date properly
     * Adds some tasks to list and then parses and execute the date command
     * Compare if the task is listed properly
     * @throws GenjiException Not expected to be thrown
     */
    @Test
    public void testDateCommand() throws GenjiException {
        TaskList t = new TaskList();
        Ui u = new Ui();
        Storage s = new Storage("./data/Genji.txt");
        t.add(new ToDo("test1"));
        t.add(new Deadline("test2", LocalDateTime.parse("2024-09-20T16:00")));
        t.add(new Event("test3", LocalDateTime.parse("2024-09-22T17:30"),
                LocalDateTime.parse("2024-09-22T19:30")));
        DateCommand d = (DateCommand) Parser.parse("date 2024-09-20");
        d.execute(t, u, s);
        assertEquals("Here are the matching tasks in your list:\n" +
                "[D][ ] test2 (by: Sep 20 2024 16:00)\n", d.getResponse());
    }

    /**
     * Tests if parse method deals with finding task properly
     * Adds some tasks to list and then parses and execute the find command
     * Compare if the task is listed properly
     * @throws GenjiException Not expected to be thrown
     */
    @Test
    public void testFindCommand() throws GenjiException {
        TaskList t = new TaskList();
        Ui u = new Ui();
        Storage s = new Storage("./data/Genji.txt");
        t.add(new ToDo("test1"));
        t.add(new Deadline("Test2", LocalDateTime.parse("2024-09-20T16:00")));
        t.add(new Event("Test3", LocalDateTime.parse("2024-09-22T17:30"),
                LocalDateTime.parse("2024-09-22T19:30")));
        FindCommand d = (FindCommand) Parser.parse("find test");
        d.execute(t, u, s);
        assertEquals("Here are the matching tasks in your list:\n" +
                "[T][ ] test1\n", d.getResponse());
    }
}
