package talkie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testStringifyTask() {
        TaskList taskList = new TaskList();
        ToDo test1 = new ToDo("CS2103T Assignment");
        ToDo test2 = new ToDo("CS2100 Assignment");
        test2.markAsDone();

        assertEquals("T | 0 | CS2103T Assignment",
                test1.stringifyTask(),
                "Stringify a uncompleted ToDo Task");

        assertEquals("T | 1 | CS2100 Assignment",
                test2.stringifyTask(),
                "Stringify a completed ToDo Task");
    }

    @Test
    public void testToString() {
        ToDo test1 = new ToDo("CS2103T Assignment");
        ToDo test2 = new ToDo("CS2100 Assignment");
        test2.markAsDone();

        assertEquals("[T][ ] CS2103T Assignment",
                test1.toString(),
                "String of an uncompleted ToDo Task");
        assertEquals("[T][X] CS2100 Assignment",
                test2.toString(),
                "String of a completed ToDo Task");
    }
}
