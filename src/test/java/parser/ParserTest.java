package parser;
import exception.IncompleteDescException;
import exception.UnknownWordException;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the functionality of the Parser class.
 * The class includes unit tests to test out the functionalities of the Parser class.
 * The testing of the `parseConversation` method ensures that the method correctly parses any user input
 * commands and returns the expected results. It also tests out the scenario when an unknown command is given
 * as an input by the user.
 */
public class ParserTest {

    /**
     * Tests ParseConversation with a positive scenario
     * This test initializes the TaskList, parses a valid todoTask command to add a task,
     * and verifies that the returned result matches the expected output.
     * @throws UnknownWordException
     * @throws IncompleteDescException
     */
    @Test
    public void testParseConversation() throws UnknownWordException, IncompleteDescException {
        Parser parser = new Parser();

        // Initializing the TaskList
        if (TaskList.getList() == null) {
            TaskList.setList(new ArrayList<>());
        }

        // Act
        String result = parser.parseConversation("todo Buy milk");

        // Expected result after parsing the input
        String expected = "Got it. I've added this task:\n" +
                "  [T][ ] Buy milk\n" +
                "Now you have 1 tasks in the list\n" +
                "How else would you like me to edit your TODO list today?";

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests ParseConversation with a negative scenario
     * This test initializes the TaskList, parses an unknown command,
     * and throws the unknown word exception, and hence prints out an error message.
     * @throws UnknownWordException
     * @throws IncompleteDescException
     */
    @Test
    public void testParseConversationUnknownCommand() {
        Parser parser = new Parser();

        // Ensure TaskList is initialized
        if (TaskList.getList() == null) {
            ArrayList<Task> list = new ArrayList<>();
            TaskList taskList = new TaskList(list);
        }

        // The command that should trigger the UnknownWordException
        String invalidCommand = "pick cat";

        // Assert that the UnknownWordException is thrown
        UnknownWordException thrownException = assertThrows(UnknownWordException.class, () -> {
            parser.parseConversation(invalidCommand);
        });

        // Verify the exception message
        String expectedMessage = "Unknown command detected: 'pick cat'.  Sorry, I do not know what that means :(\n" +
                "Please try again with a proper command.\n" +
                "Make sure you are not adding any unecessary spaces or characters.";
        assertEquals(expectedMessage, thrownException.getMessage());
    }
}
