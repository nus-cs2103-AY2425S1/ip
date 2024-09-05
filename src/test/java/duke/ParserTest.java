package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void processTest() {
        try {
            Parser p = new Parser("xkcd");
            p.process(new TaskList(new ArrayList<Task>()), new Ui());
            fail();
        } catch (EmptyCommandException | InvalidInstructionException ignored) {
        }
    }

    @Test
    public void processTest2() {
        try {
            Parser p = new Parser("mark 1");
            TaskList tasks = new TaskList(new ArrayList<Task>());
            Todo todo = new Todo("borrow book");
            tasks.add(todo);
            p.process(tasks, new Ui());
            assertEquals("[T][X] borrow book", todo.toString());
        } catch (EmptyCommandException | InvalidInstructionException ignored) {
        }
    }
}
