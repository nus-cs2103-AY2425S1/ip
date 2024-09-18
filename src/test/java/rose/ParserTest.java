package rose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import rose.command.AddCommand;
import rose.command.Command;
import rose.command.ExitCommand;
import rose.task.TaskType;

public class ParserTest {

    @Test
    public void parse_correctInput_success() throws RoseException {
        Command exitTrial = Parser.parse("bye");
        ExitCommand exitExpected = new ExitCommand();
        assertEquals(exitTrial.isExit(), exitExpected.isExit());

        Command addTrial = Parser.parse("todo doing laundry");
        AddCommand addExpected = new AddCommand(TaskType.TODO, "doing laundry");
        assertEquals(addTrial.isExit(), addExpected.isExit());
    }

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        RoseException exception = assertThrows(RoseException.class, () -> {
            Parser.parse("event ");
        });
        assertEquals("You need to provide details for the EVENT task.", exception.getMessage());
    }

    @Test
    public void parse_wrongFormat_exceptionThrown() {
        RoseException exception = assertThrows(RoseException.class, () -> {
            Parser.parse("mark three");
        });
        assertEquals("OOPS!!! You should provide a number of the task index.", exception.getMessage());
    }

    @Test
    public void parse_unknownInput_exceptionThrown() {
        RoseException exception = assertThrows(RoseException.class, () -> {
            Parser.parse("random input");
        });
        assertEquals("OOPS!!! I'm sorry, but I don't know that command :-(", exception.getMessage());
    }
}
