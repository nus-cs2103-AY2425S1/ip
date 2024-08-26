package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        Todo test1 = new Todo("read book");
        Todo test2 = new Todo("finish assignment");
        test2.markTask();

        assertEquals("[T][ ] read book", test1.toString(), "Task without marking");
        assertEquals("[T][X] finish assignment", test2.toString(),"Task should be marked as done");
    }

    @Test
    public void testMarkAsDone() {
        Todo test = new Todo("read book");
        test.markTask();
        assertEquals("[T][X] read book", test.toString(), "Task should be marked as done");
    }

    @Test
    public void testToFileString() {
        Todo test1 = new Todo("read book");
        test1.isDone = true;
        Todo test2 = new Todo("go sleep");
        assertEquals("T | 1 | read book", test1.toFileString(), "Task complete in text file");
        assertEquals("T | 0 | go sleep", test2.toFileString(), "Task incomplete in text file");
    }
}
