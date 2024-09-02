package axel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParseToDoCommand() throws AxelException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("read book", addCommand.task.getTaskName());
    }

    @Test
    public void testParseDeadlineCommand() throws AxelException {
        Command command = Parser.parse("deadline return book /by 2023-09-01 1800");
        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        assertTrue(addCommand.task instanceof DeadlineTask);
        DeadlineTask deadlineTask = (DeadlineTask) addCommand.task;
        assertEquals("return book", deadlineTask.getTaskName());
    }
}

