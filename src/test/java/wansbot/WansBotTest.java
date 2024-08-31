package wansbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static wansbot.WansBot.emptyInput;

import org.junit.jupiter.api.Test;

import wansbot.tasks.InputEmptyException;
import wansbot.tasks.TaskList;

public class WansBotTest {
    @Test
    public void emptyInput_throwsInputEmptyException() {
        TaskList taskList = new TaskList();
        try {
            emptyInput("todos ");
            fail();
        } catch (InputEmptyException e) {
            assertEquals("You need to input something after todos ", e.getMessage());
        }
    }
}
