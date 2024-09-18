package bonnieGUI;

import Exceptions.DeadlineFormatException;
import Exceptions.EmptyTodoException;
import bonnie.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GUIParserTest {
    @Test
    public void parseTaskAddition_unknownCommandUsed() throws DeadlineFormatException, EmptyTodoException {
        String invalidInput = "not a valid input";
        assertEquals(GUIParser.parseInput(invalidInput), String.format("Hey %s, I do not understand what you mean by %s", MainWindow.username, invalidInput));
    }

    @Test
    void testParseInputWithMarkCommand() throws DeadlineFormatException, EmptyTodoException {
        String result = GUIParser.parseInput("mark 1");
        assertEquals("Task 1 has been successfully marked as done!", result);
    }

    @Test
    void testParseInputWithAddTask() throws DeadlineFormatException, EmptyTodoException {
        String result = GUIParser.parseInput("todo Read a book");
        assertEquals(String.format("Hey %s, I have added \"Read a book\" into your task list!\nYou now have 1 tasks to complete!\n", MainWindow.username, result),
                result);
    }

    @Test
    void testParseInputWithInvalidTodoTask() {
        try {
            GUIParser.parseInput("todo");
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof EmptyTodoException);
        }
    }
}
