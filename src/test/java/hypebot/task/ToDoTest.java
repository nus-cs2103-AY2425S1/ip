package hypebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for {@link Event}.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class ToDoTest {
    @Test
    public void toFileStringIncompleteToDoSuccess() throws Exception {
        // normal incomplete ToDo exported to save in text file
        assertEquals("T , 0 , borrow book\n", new ToDo("borrow book").toFileString());
    }

    @Test
    public void toFileStringCompleteToDoSuccess() throws Exception {
        // normal complete ToDo exported to save in text file
        ToDo temp = new ToDo("touch grass");
        temp.mark();
        assertEquals("T , 1 , touch grass\n", temp.toFileString());
    }

    @Test
    public void toStringIncompleteToDoSuccess() throws Exception {
        // normal incomplete ToDo shown to user
        assertEquals("[T][ ] go to sleep", new ToDo("go to sleep").toString());
    }

    @Test
    public void toStringCompleteToDoSuccess() throws Exception {
        // normal complete ToDo shown to user
        ToDo temp = new ToDo("finish iP");
        temp.mark();
        assertEquals("[T][X] finish iP", temp.toString());
    }
}
