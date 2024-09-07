package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.task.Todo;
import wolfie.util.Storage;
import wolfie.util.Ui;

class FindCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/testTasks.txt");
    }

    @Test
    void testExecute() throws IOException, WolfieException {
        tasks.add(new Todo("read book", true));
        tasks.add(new Todo("return book", true));
        FindCommand command = new FindCommand("book");
        String result = command.execute(tasks, ui, storage);
        String expectedOutput = """
                Here are the matching tasks in your list:
                 1.[T][X] read book
                 2.[T][X] return book
                """;
        assertEquals(result, expectedOutput);
    }
}
