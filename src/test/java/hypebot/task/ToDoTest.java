package hypebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toFileString_incompleteToDo_success() throws Exception {
        // normal incomplete ToDo exported to save in text file
        assertEquals("T , 0 , borrow book\n", new ToDo("borrow book").toFileString());
    }

    @Test
    public void toFileString_completeToDo_success() throws Exception {
        // normal complete ToDo exported to save in text file
        ToDo temp = new ToDo("touch grass");
        temp.mark();
        assertEquals("T , 1 , touch grass\n", temp.toFileString());
    }

    @Test
    public void toString_incompleteToDo_success() throws Exception {
        // normal incomplete ToDo shown to user
        assertEquals("[T][ ] go to sleep", new ToDo("go to sleep").toString());
    }

    @Test
    public void toString_completeToDo_success() throws Exception {
        ToDo temp = new ToDo("finish iP");
        temp.mark();
        assertEquals("[T][X] finish iP", temp.toString());
    }
}
