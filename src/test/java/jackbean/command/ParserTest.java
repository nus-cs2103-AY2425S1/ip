package jackbean.command;

import jackbean.task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    TaskList dummyTaskList = new TaskList();
    @Test
    public void parseUserInput_invalidCommand_exceptionThrown() {
        try {
            assertEquals("bxfsd", Parser.parseUserInput("3rafd", dummyTaskList));
            fail(); // should not reach this line
        } catch (Exception e) {
            assertEquals("Invalid jackbean.task.Task Type", e.getMessage());
        }
    }

    @Test
    public void parseUserInput_list_listShown() {
        try {
            assertEquals("Yo homie!, here are the tasks in your list:",
                    Parser.parseUserInput("list", dummyTaskList));
        } catch (Exception e) {
            fail(); // should not reach this line
        }
    }

    @Test
    public void parseUserInput_deadline_deadlineAdded() {
        try {
            assertEquals("Got it homie! I've added your deadline, LESGOOOOO:\n"
                    + "[D][ ] mydeadline (by: May 6 2024)" + "\n" + "Homie, you have 1 task(s) in the list now.",
                    Parser.parseUserInput("deadline mydeadline /by 2024-05-06", dummyTaskList));
        } catch (Exception e) {
            fail(); // should not reach this line
        }
    }

    @Test
    public void parseUserInput_notEnoughArguments_exceptionThrown() {
        try {
            assertEquals("sfd", Parser.parseUserInput("deadline dklsfm", dummyTaskList));
            fail(); // should not reach this line
        } catch (Exception e) {
            assertEquals("not enough arguments", e.getMessage());
        }
    }

    @Test
    public void parseUserInput_tooManyArguments_exceptionThrown() {
        try {
            assertEquals("sfd", Parser.parseUserInput("event myevent /from 2021-09-09 /to 2021-10-03 / extra", dummyTaskList));
            fail(); // should not reach this line
        } catch (Exception e) {
            assertEquals("too many arguments", e.getMessage());
        }
    }
}
