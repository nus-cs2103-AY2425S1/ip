package tayoo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toString_anyString_returnsCorrectString() {
        String taskName = "Return Book";
        ToDo testToDo1 = new ToDo(taskName, false);
        String expected = "[T][ ] Return Book";
        String actual = testToDo1.toString();
        assertEquals(expected, actual);

        ToDo testToDo2 = new ToDo(taskName, true);
        expected = "[T][X] Return Book";
        actual = testToDo2.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void toTxt_anyTask_returnsCorrectTxtRepresentation() {
        assertDoesNotThrow(() -> {
            String taskName = "Return Book";
            ToDo testToDo1 = new ToDo(taskName, true);
            String expected = "Todo | true | Return Book";
            String actual = testToDo1.toTxt();
            assertEquals(expected, actual);

            ToDo testToDo2 = new ToDo(taskName, false);
            expected = "Todo | false | Return Book";
            actual = testToDo2.toTxt();
            assertEquals(expected, actual);
        });
    }
}
