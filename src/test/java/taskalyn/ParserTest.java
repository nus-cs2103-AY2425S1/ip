package taskalyn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void parse_validByeCommand_returnByeMessage() {
        String actualResult = parser.parse("bye");
        String expectedResult = "Bye! Hope to see you again soon!\n\n"
                + "Shutting down in 5 seconds...";
        assertEquals(expectedResult, actualResult, "The chatbot returns the bye message without errors.");
    }

    @Test
    public void parse_invalidByeCommand_returnCorrectCommandFormatMessage() {
        String actualResult = parser.parse("bye hahahah");
        String expectedResult = "Aw... bye command must be just one word, e.g. bye";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to only input one word.");
    }

    @Test
    public void parse_validSortCommand_returnSortedTasksMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        String actualResult = parser.parse("sort");
        String expectedResult = "Here are the sorted deadline tasks in your list:\n"
                + "1.[D][ ] hw (by: 11 09 2024, 1:00 PM)\n"
                + "2.[D][ ] math (by: 13 09 2024, 3:00 PM)";
        assertEquals(expectedResult, actualResult, "The chatbot returns the sorted deadline tasks without errors.");
    }

    @Test
    public void parse_invalidSortCommand_returnCorrectCommandFormatMessage() {
        String actualResult = parser.parse("sort lolol");
        String expectedResult = "Aw... sort command must be just one word, e.g. sort";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to only input one word.");
    }

    @Test
    public void parse_validListCommand_returnTasksMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("list");
        String expectedResult = "Here are the tasks in your list:\n"
                + "1.[T][ ] go to gym\n"
                + "2.[D][ ] hw (by: 11 09 2024, 1:00 PM)\n"
                + "3.[D][ ] math (by: 13 09 2024, 3:00 PM)\n"
                + "4.[E][ ] party (from: 11 09 2024, 6:00 PM to: 11 09 2024, 8:00 PM)";
        assertEquals(expectedResult, actualResult, "The chatbot should list all tasks without errors.");
    }

    @Test
    public void parse_invalidListCommand_returnCorrectCommandFormatMessage() {
        String actualResult = parser.parse("list woohoo");
        String expectedResult = "Aw... list command must be just one word, e.g. list";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to only input one word.");
    }

    @Test
    public void parse_invalidCommand_returnInvalidMessage() {
        String actualResult = parser.parse("invalid");
        String expectedResult = "Sorry bro, no clue what you're saying!";
        assertEquals(expectedResult, actualResult, "The chatbot should not recognise invalid commands.");
    }

    @Test
    public void parse_emptyFindCommand_getEmptyDescriptionMessage() {
        String actualResult = parser.parse("find");
        String expectedResult = "Aw... find command is incomplete. The format is: find {keyword}";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to enter the keyword for find command.");
    }

    @Test
    public void parse_validFindCommand_returnMatchingTasksMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("find gym");
        String expectedResult = "Here are the matching tasks in your list:\n"
                + "1.[T][ ] go to gym";
        assertEquals(expectedResult, actualResult,
                "The chatbot should return matching tasks without errors.");
    }

    @Test
    public void parse_emptyTodoCommand_getEmptyTaskDescriptionMessage() {
        String actualResult = parser.parse("todo ");
        String expectedResult = "Aw... todo command is incomplete. The format is: todo {task}";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to enter the task description.");
    }

    @Test
    public void parse_validTodoCommand_returnTaskAddedMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("todo head out");
        String expectedResult = "Got it, I've added this task to your list!\n"
                + "[T][ ] head out\n"
                + "Wah bro... 5 tasks already!";
        assertEquals(expectedResult, actualResult,
                "The chatbot should return task added message without errors.");
    }

    @Test
    public void parse_emptyDeadlineCommand_returnIncompleteCommandMessage() {
        String actualResult = parser.parse("deadline");
        String expectedResult = "Aw... deadline command is incomplete. The format is: deadline {task} /by"
                + " {date in dd-MM-yyyy HHmm}";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to enter the correct deadline command.");
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

    @Test
    public void parse_validDeadlineCommand_returnTaskAddedMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("deadline homework /by 20-09-2024 2359");
        String expectedResult = "Got it, I've added this task to your list!\n"
                + "[D][ ] homework (by: 20 09 2024, 11:59 PM)\n"
                + "Wah bro... 5 tasks already!";
        assertEquals(expectedResult, actualResult,
                "The chatbot should return task added message without errors.");
    }

    @Test
    public void parse_invalidEventCommand_returnDateErrorMessage() {
        String actualResult = parser.parse("event school /from 11-09-2024 1 /to 11-09-2024 1300");
        String expectedResult = "Aw... the date and time should be in this format: dd-MM-yyyy HHmm";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to use the proper date.");
    }

    @Test
    public void parse_invalidEventCommand_returnNoDateMessage() {
        String actualResult = parser.parse("event school /from /to");
        String expectedResult = "Aw... you are missing the date.";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to enter the date.");
    }

    @Test
    public void parse_invalidEventCommand_returnNoFromKeywordMessage() {
        String actualResult = parser.parse("event school 11-09-2024 1300 /to 11-09-2024 1500");
        String expectedResult = "Aw... event command should contain /from.";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to enter /from in event command.");
    }

    @Test
    public void parse_invalidEventCommand_returnNoToKeywordMessage() {
        String actualResult = parser.parse("event school /from 11-09-2024 1300 11-09-2024 1500");
        String expectedResult = "Aw... event command should contain /to.";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to enter /to in event command.");
    }

    @Test
    public void parse_invalidEventCommand_returnNoTaskDescriptionMessage() {
        String actualResult = parser.parse("event /from 11-09-2024 1300 /to 11-09-2024 1500");
        String expectedResult = "Aw... event command must contain a non-empty task description.";
        assertEquals(expectedResult, actualResult, "The chatbot should prompt user to enter task description.");
    }

    @Test
    public void parse_emptyEventCommand_returnIncompleteCommandMessage() {
        String actualResult = parser.parse("event");
        String expectedResult = "Aw... event command is incomplete. The format is: event {task} /from"
                + " {date in dd-MM-yyyy HHmm} /to {date in dd-MM-yyyy HHmm}";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to enter the correct event command.");
    }

    @Test
    public void parse_emptyDeleteCommand_getEmptyTaskDescriptionMessage() {
        String actualResult = parser.parse("delete");
        String expectedResult = "Aw... delete command is incomplete. The format is: delete {task number}";
        assertEquals(expectedResult, actualResult, "The chatbot should tell user to use the correct format.");
    }

    @Test
    public void parse_validDeleteCommand_getDeleteTaskMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("delete 1");
        String expectedResult = "Awesome bro! One task gone :D\n"
                + "[T][ ] go to gym\n"
                + "Wah bro... 3 tasks already!";
        assertEquals(expectedResult, actualResult, "The chatbot should delete the task without errors.");
    }

    @Test
    public void parse_invalidDeleteCommand_getNoSuchTaskMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("delete 64");
        String expectedResult = "Aw... that task doesn't exist. Try again!";
        assertEquals(expectedResult, actualResult, "The chatbot should tell user the task doesn't exist");
    }

    @Test
    public void parse_validMarkCommand_getMarkTaskMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("mark 1");
        String expectedResult = "Nice, I've marked this task as complete:\n"
                + "[T][X] go to gym";
        assertEquals(expectedResult, actualResult, "The chatbot should mark the task as complete without errors.");
    }

    @Test
    public void parse_emptyMarkCommand_getIncompleteCommandMessage() {
        String actualResult = parser.parse("mark");
        String expectedResult = "Aw... mark command is incomplete. The format is: mark {task number}";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to use the correct format for mark");
    }

    @Test
    public void parse_invalidMarkCommand_getNoSuchTaskMessage() {
        String actualResult = parser.parse("mark 64");
        String expectedResult = "Aw... that task doesn't exist. Try again!";
        assertEquals(expectedResult, actualResult, "The chatbot should tell user the task doesn't exist");
    }

    @Test
    public void parse_validMarkCommand_getAlreadyMarkedMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        taskManager.markTaskAsComplete(1);
        String actualResult = parser.parse("mark 1");
        String expectedResult = "This task was already marked as complete:\n"
                + "[T][X] go to gym";
        assertEquals(expectedResult, actualResult, "The chatbot should tell user that the task is already marked.");
    }

    @Test
    public void parse_validUnmarkCommand_getUnmarkTaskMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        taskManager.markTaskAsComplete(1);
        String actualResult = parser.parse("unmark 1");
        String expectedResult = "Ok, I've marked this task as incomplete:\n"
                + "[T][ ] go to gym";
        assertEquals(expectedResult, actualResult, "The chatbot should mark the task as incomplete without errors.");
    }

    @Test
    public void parse_emptyUnmarkCommand_getIncompleteCommandMessage() {
        String actualResult = parser.parse("unmark");
        String expectedResult = "Aw... unmark command is incomplete. The format is: unmark {task number}";
        assertEquals(expectedResult, actualResult,
                "The chatbot should prompt user to use the correct format for unmark");
    }

    @Test
    public void parse_invalidUnmarkCommand_getNoSuchTaskMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("unmark 64");
        String expectedResult = "Aw... that task doesn't exist. Try again!";
        assertEquals(expectedResult, actualResult, "The chatbot should tell user the task doesn't exist");
    }

    @Test
    public void parse_validUnmarkCommand_getAlreadyMarkedMessage() throws IOException {
        while (taskManager.getTaskSize() > 0) {
            taskManager.deleteTask(1);
        }
        mockDatabase.clearDatabase();
        taskManager.addTask(new TodoTask("go to gym", false));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        taskManager.addTask(new DeadlineTask("hw", LocalDateTime.parse("11-09-2024 1300", formatter), false));
        taskManager.addTask(new DeadlineTask("math", LocalDateTime.parse("13-09-2024 1500", formatter), false));
        taskManager.addTask(new EventTask("party", LocalDateTime.parse("11-09-2024 1800", formatter),
                LocalDateTime.parse("11-09-2024 2000", formatter), false));
        String actualResult = parser.parse("unmark 1");
        String expectedResult = "This task was already unmarked and incomplete:\n"
                + "[T][ ] go to gym";
        assertEquals(expectedResult, actualResult, "The chatbot should tell user that the task is already unmarked.");
    }
}
