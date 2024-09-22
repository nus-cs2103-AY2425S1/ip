package donk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import donk.task.TaskList;


public class ParserTest {
    @Test
    public void parse_todo_success() throws Exception {
        StorageStub storageStub = new StorageStub("dummyPath");
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Parser.parse("todo new task 1");
        assertEquals(1, tasks.size());
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
        StorageStub storageStub = new StorageStub("dummyPath");
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        String[] commands = {"todo book task 1", "event bookFair /start 1/1/2024 /end 1/2/2024", "todo something else"};
        for (String command : commands) {
            Parser.parse(command);
        }
        assertEquals(3, tasks.size());
        assertEquals(2, tasks.find("book").size());
    }
}
