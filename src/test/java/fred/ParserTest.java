package fred;

import fred.Exceptions.FredException;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseInput_addTodo_success() throws Exception {
        String input = "todo return book";
        Action expected = new Action(Command.ADD_TODO_TASK, TaskType.TODO, "return book");
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);

    }

    @Test
    public void parseInput_addDeadline_success() throws Exception {
        String input = "deadline return book /by 2024-01-01 08:00";
        Action expected = new Action(Command.ADD_DEADLINE_TASK, TaskType.DEADLINE, "return book /by 2024-01-01 08:00");
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_addEvent_success() throws Exception {
        String input = "event orientation week /from 2024-02-02 13:00 /to 2024-02-03 14:20";
        Action expected = new Action(Command.ADD_EVENT_TASK, TaskType.EVENT, "orientation week /from 2024-02-02 13:00 /to 2024-02-03 14:20");
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_findTask_success() throws Exception {
        String input = "find book";
        Action expected = new Action(Command.FIND_TASK, "book");
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_markTask_success() throws Exception {
        String input = "mark 1";
        Action expected = new Action(Command.MARK_TASK, 0);
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_unmarkTask_success() throws Exception {
        String input = "unmark 1";
        Action expected = new Action(Command.UNMARK_TASK, 0);
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_deleteTask_success() throws Exception {
        String input = "delete 1";
        Action expected = new Action(Command.DELETE_TASK, 0);
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_tagTask_success() throws Exception {
        String input = "tag 1 fun";
        Action expected = new Action(Command.TAG_TASK, 0, "fun");
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_listTasks_success() throws Exception {
        String input = "list";
        Action expected = new Action(Command.LIST_TASKS);
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_exit_success() throws Exception {
        String input = "bye";
        Action expected = new Action(Command.EXIT, 0);
        Action actual = new Parser().parseInput(input);
        assertEquals(compareActions(expected, actual), true);
    }

    @Test
    public void parseInput_emptyInput_exceptionThrown() {
        try {
            String input = "";
            new Parser().parseInput(input);
            fail();
        } catch (FredException e) {
            assertEquals(e.getMessage(), "OOPS!!! I think you forgot to type something.");
        }
    }

    @Test
    public void parseInput_unknownCommand_exceptionThrown() {
        try {
            String input = "lol";
            new Parser().parseInput(input);
            fail();
        } catch (FredException e) {
            assertEquals(e.getMessage(), "OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    @Test
    public void parseInput_invalidTaskNumber_exceptionThrown() {
        try {
            String input = "mark one";
            new Parser().parseInput(input);
            fail();
        } catch (FredException e) {
            assertEquals(e.getMessage(), "OOPS!!! That's not a valid task number!");
        }
    }

    @Test
    public void parseInput_emptyTaskDescription_exceptionThrown() {
        try {
            String input = "todo";
            new Parser().parseInput(input);
            fail();
        } catch (FredException e) {
            assertEquals(e.getMessage(), "OOPS!!! The description of a task cannot be empty.");
        }
    }

    @Test
    public void parseInput_emptyKeyword_exceptionThrown() {
        try {
            String input = "find";
            new Parser().parseInput(input);
            fail();
        } catch (FredException e) {
            assertEquals(e.getMessage(), "OOPS!!! Looks like you forgot to specify the keywords to look for");
        }
    }

    @Test
    public void parseInput_invalidTag_exceptionThrown() {
        try {
            String input = "tag";
            new Parser().parseInput(input);
            fail();
        } catch (FredException e) {
            assertEquals(e.getMessage(), "OOPS!!! Please use this format: tag 1 fun");
        }
    }

    private boolean compareActions(Action action1, Action action2) {
        if (action1.getCommand() != action2.getCommand()) {
            return false;
        }
        if (action1.getTaskType() != action2.getTaskType()) {
            return false;
        }
        if (action1.getTaskNumber() != action2.getTaskNumber()) {
            return false;
        }
        if (!Objects.equals(action1.getTag(), action2.getTag())) {
            return false;
        }
        if (!Objects.equals(action1.getTaskDetails(), action2.getTaskDetails())) {
            return false;
        }
        if (!Objects.equals(action1.getKeyword(), action2.getKeyword())) {
            return false;
        }
        return true;
    }
}
