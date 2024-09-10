package twilight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void add() {
        assertEquals("added: [T][ ] borrow book\n" + "There are 1 tasks in the list",
                new TaskList().add(new Todo("borrow book")));

        assertEquals("added: [D][ ] return book by: Apr 4 2019\n" + "There are 1 tasks in the list",
                new TaskList().add(new Deadline("return book", "2019-04-04")));

        assertEquals("added: [E][ ] project meeting from: Mon 2pm to: 4pm\n" + "There are 1 tasks in the list",
                new TaskList().add(new Event("project meeting", "Mon 2pm", "4pm")));
    }


}
