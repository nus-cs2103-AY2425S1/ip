package taskalyn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Verifies that the Parser properly handles user inputs and catches exceptions.
 */
public class ParserTest {
    private Parser parser;
    private Ui ui;
    private TaskManager taskManager;
    private MockDatabase mockDatabase;

    @BeforeEach
    public void setUp() throws IOException {
        ui = new Ui();
        mockDatabase = new MockDatabase();
        taskManager = new TaskManager(mockDatabase, ui);
        parser = new Parser(ui, taskManager);
        mockDatabase.clearDatabase();
    }

    /**
     * Verifies that the Parser properly handles the bye command (Unused for GUI).
     */
    @Test
    public void parse_byeCommand_returnByeMessage() {
        String actualResult = parser.parse("bye");
        String expectedResult = "Bye! Hope to see you again soon!";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void parse_invalidCommand_returnInvalidMessage() {
        String actualResult = parser.parse("invalid");
        String expectedResult = "Sorry bro, no clue what you're saying!";
        assertEquals(expectedResult, actualResult, "The chatbot should not recognise invalid commands.");
    }

    @Test
    public void parse_emptyTodoCommand_getEmptyTaskDescriptionMessage() {
        String actualResult = parser.parse("todo ");
        String expectedResult = "Aw... todo command is incomplete. The format is: todo {task}";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to enter the task description.");
    }

    @Test
    public void parse_invalidDeadlineCommand_getDateWrongFormatMessage() {
        String actualResult = parser.parse("deadline finish hw /by 11 09 2024 1300");
        String expectedResult = "Aw... the date and time should be in this format: dd-MM-yyyy HHmm";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to enter date and time in correct format.");
    }

    @Test
    public void parse_invalidDeadlineCommand_getShouldContainKeywordMessage() {
        String actualResult = parser.parse("deadline finish hw 11-09-2024 1300");
        String expectedResult = "Aw... deadline command should contain /by.";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to include /by in deadline command.");
    }

    @Test
    public void parse_emptyDeadlineCommand_getEmptyTaskDescriptionMessage() {
        String actualResult = parser.parse("deadline /by 11-09-2024 1300");
        String expectedResult = "Aw... deadline command must contain a non-empty task description.";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to enter the task description.");
    }
}
