package donk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import donk.task.Event;
import donk.task.TaskList;
import donk.task.ToDo;


public class ParserTest {
    @Test
    public void parse_todo_success() throws Exception {
        String command = Parser.parse("todo new task 1");
        assertEquals(command, "todo");
    }

    @Test
    public void parse_invalidInput() throws Exception {
        StorageStub storageStub = new StorageStub("dummyPath");
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        try {
            Parser.parse("new task 1");

        } catch (Exception e) {
            System.out.println("error caught " + e.getMessage());
        } finally {
            assertEquals(0, tasks.size());
        }
    }

    @Test
    public void parse_find_success() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("homework"));
        tasks.add(new Event("midautumn festival", "09/09/2024 0100", "10/09/2024 2359"));

        assertEquals(2, tasks.size());
        assertEquals(1, tasks.find("homework").size());
    }
}
