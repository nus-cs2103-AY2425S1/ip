package tayoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TayooTest {

    @Test
    public void getResponse_exitCommand_isExitReturnsTrue() {
        Tayoo testTayoo = new Tayoo();
        testTayoo.getResponse("Exit");
        assertTrue(testTayoo.getIsExit());
    }

    @Test
    public void getResponse_nonExitCommand_isExitReturnsFalse() {
        Tayoo testTayoo = new Tayoo();
        testTayoo.getResponse("Todo return book");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("Deadline return book /by friday");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("Event book reading /from today /to tomorrow");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("List");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("Mark 1");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("unmark 1");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("find book");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("Delete 1");
        assertFalse(testTayoo.getIsExit());
        testTayoo.getResponse("remove all");
        assertFalse(testTayoo.getIsExit());
    }

    @Test
    public void getResponse_anyCommand_returnsCorrectCommandType() {
        Tayoo testTayoo = new Tayoo();
        testTayoo.getResponse("Todo return book");
        String actual = testTayoo.getCommandType();
        String expected = "AddTaskCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("Deadline return book /by friday");
        actual = testTayoo.getCommandType();
        expected = "AddTaskCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("Event book reading /from today /to tomorrow");
        actual = testTayoo.getCommandType();
        expected = "AddTaskCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("Mark 1");
        actual = testTayoo.getCommandType();
        expected = "MarkTaskCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("unmark 1");
        actual = testTayoo.getCommandType();
        expected = "MarkTaskCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("find book");
        actual = testTayoo.getCommandType();
        expected = "FindCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("Delete 1");
        actual = testTayoo.getCommandType();
        expected = "DeleteTaskCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("remove all");
        actual = testTayoo.getCommandType();
        expected = "DeleteAllCommand";
        assertEquals(expected, actual);

        testTayoo.getResponse("exit");
        actual = testTayoo.getCommandType();
        expected = "ExitCommand";
        assertEquals(expected, actual);
    }

}
