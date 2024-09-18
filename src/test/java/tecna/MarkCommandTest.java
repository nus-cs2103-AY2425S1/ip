package tecna;

import tecna.command.MarkCommand;
import tecna.exception.WrongFormatException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    @Test
    public void parseMarkCommand_withoutIndex_exceptionThrown() {
        MarkCommand withoutIndex1 = new MarkCommand("mark");
        MarkCommand withoutIndex2 = new MarkCommand("mark ");
        MarkCommand withoutIndex3 = new MarkCommand("mark      ");

        try {
            withoutIndex1.parseMarkCommand(4);
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("mark", "Mark command should be in the format of \"mark [index of the task from 1 to " + 4 +  "]\"").getMessage(), e.getMessage());
        }

        try {
            withoutIndex2.parseMarkCommand(4);
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("mark", "Mark command should be in the format of \"mark [index of the task from 1 to " + 4 +  "]\"").getMessage(), e.getMessage());
        }

        try {
            withoutIndex3.parseMarkCommand(4);
        } catch (WrongFormatException e) {
            assertEquals(new WrongFormatException("mark", "Mark command should be in the format of \"mark [index of the task from 1 to " + 4 +  "]\"").getMessage(), e.getMessage());
        }
    }
}
