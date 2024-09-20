package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;



public class ParserTest {

    /*
    IntelliJ IDEA AI recommended removing redundant Exception throws that were initially necessary but became redundant
    after code compilation.
     */
    @Test
    public void processTest() {
        Parser p = new Parser("xkcd");
        assertThrows(InvalidCommandException.class, ()-> p.process(new TaskList(new ArrayList<>()), new Ui()));
        //fail();
    }

    @Test
    public void processTest2() throws EmptyCommandException, TaskListOutOfBoundsException, InvalidCommandException {
        Parser p = new Parser("mark 1");
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("borrow book");
        tasks.add(todo);
        p.setInputString("deadline return book /by 2012-06-06");
        /*
        IntelliJ IDEA AI recommended changing string concatenation to a text block, which improved the code quality upon
        implementation.
         */
        assertEquals("""
                        Got it. I've added this task:
                        [D][ ] return book (by: Jun 6 2012)
                        Now you have 2\
                         tasks in the list.
                        """,
                p.stringProcess(tasks, new Ui()));
    }
}
