package twilight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void add_validTasks_success() throws InvalidInputException {
        assertEquals("added: [T][ ] borrow book\n" + "There are 1 tasks in the list.",
                new TaskList().add(new Todo("borrow book")));


        assertEquals("added: [D][ ] return book by: Apr 4 2019\n" + "There are 1 tasks in the list.",
                new TaskList().add(new Deadline("return book", "2019-04-04")));

        assertEquals("added: [E][ ] project meeting from: Mon 2pm to: 4pm\n" + "There are 1 tasks in the list.",
                new TaskList().add(new Event("project meeting", "Mon 2pm", "4pm")));
    }

    @Test
    public void add_invalidDate_exceptionThrown() {
        try {
            assertEquals("added: [D][ ] return book by: Apr 4 2019\n" + "There are 1 tasks in the list",
                    new TaskList().add(new Deadline("return book", "2019-16-16")));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid Input:\nInvalid date. Date must be in the format:\nYYYY-MM-DD", e.toString());
        }
    }

    @Test
    public void mark_validInput_success() throws InvalidInputException {
        assertEquals("[T][X] borrow book", new TaskList(new Todo("borrow book")).mark(0));
    }

    @Test
    public void mark_indexOutOfRange_exceptionThrown() {
        try {
            new TaskList().mark(0);
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid Input:\nTask 1 does not exist.", e.toString());
        }
    }

    @Test
    public void list_emptyList_success() {
        assertEquals("No tasks stored in list. Feel free to add some.", new TaskList().list());
    }

    @Test
    public void list_singleItemList_success() {
        assertEquals("Here are the current tasks:\n" + "1. [T][ ] borrow book", new TaskList(new Todo("borrow book")).list());
    }

    @Test
    public void query_singleItemList_success() {
        assertEquals("Here are the matches:\n" + "1. [T][ ] borrow book", new TaskList(new Todo("borrow book")).query("book"));
    }

    @Test
    public void query_singleItemList_noMatches() {
        assertEquals("No matches found", new TaskList(new Todo("borrow book")).query("potato"));
    }



}
