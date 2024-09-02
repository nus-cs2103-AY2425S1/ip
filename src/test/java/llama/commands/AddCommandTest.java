package llama.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import llama.exceptions.LlamaException;

public class AddCommandTest {

    @Test
    public void addCommand_emptyInputTodo_exceptionThrown() {
        try {
            Command command = new AddCommand("", AddCommand.TaskType.TODO);
            fail();
        } catch (LlamaException e) {
            assertEquals("Empty Todo Task?!? Might as well ask me to not add it in!", e.getMessage());
        }
    }

    @Test
    public void addCommand_emptyInputDeadline_exceptionThrown() {
        try {
            Command command = new AddCommand("", AddCommand.TaskType.DEADLINE);
            fail();
        } catch (LlamaException e) {
            assertEquals("Empty Deadline?!? What's the point of keeping track of nothing?", e.getMessage());
        }
    }

    @Test
    public void addCommand_emptyInputEvent_exceptionThrown() {
        try {
            Command command = new AddCommand("", AddCommand.TaskType.EVENT);
            fail();
        } catch (LlamaException e) {
            assertEquals("Empty Event?!? You're planning to go nowhere with no one?", e.getMessage());
        }
    }
}
