package donk;

import donk.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_todo_success() throws Exception {
        StorageStub storageStub = new StorageStub("dummyPath");
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Parser.parse("todo new task 1", tasks, storageStub, ui);
        assertEquals(1, tasks.size());
    }

    @Test
    public void parse_invalidInput() throws Exception {
        StorageStub storageStub = new StorageStub("dummyPath");
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        try {
            Parser.parse("new task 1", tasks, storageStub, ui);

        } catch (Exception e) {
            System.out.println("error caught " + e.getMessage());
        } finally {
            assertEquals(0, tasks.size());
        }
    }

    @Test
    public void parse_find_success() throws Exception{
        StorageStub storageStub = new StorageStub("dummyPath");
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Parser.parse("todo new task 1", tasks, storageStub, ui);
        assertEquals(1, tasks.size());
    }
}
