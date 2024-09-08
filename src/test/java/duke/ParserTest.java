package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class ParserTest {

    @Test
    public void processTest() {
        try {
            Parser p = new Parser("xkcd");
            p.process(new TaskList(new ArrayList<>()), new Ui());
            fail();
        } catch (EmptyCommandException | InvalidInstructionException ignored) {
            System.out.println("Error");
        }
    }

    @Test
    public void processTest2() {
        try {
            Parser p = new Parser("mark 1");
            TaskList tasks = new TaskList(new ArrayList<>());
            Todo todo = new Todo("borrow book");
            tasks.add(todo);
            p.process(tasks, new Ui());
            assertEquals("[T][X] borrow book", todo.toString());
        } catch (EmptyCommandException | InvalidInstructionException ignored) {
            System.out.println("Error");
        }
    }
}
