package genji;

import genji.command.AddCommand;
import genji.task.TaskList;
import genji.task.ToDo;
import org.junit.jupiter.api.Test;
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
}
