package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.ExitCommand;
import exceptions.GrokInvalidUserInputException;
import tasklist.TaskList;

public class ParserTest {
    private static Parser parser;
    private static TaskList tasks;

    @BeforeAll
    public static void setup() {
        parser = new Parser();
        tasks = new TaskList(new ArrayList<>());
    }
    @Test
    public void parseUserInput_validCommand() {
        try {
            assertEquals(new ExitCommand(), parser.parseUserInput("bye", tasks));
        } catch (GrokInvalidUserInputException e) {
            fail("Exception was thrown for valid, simple command");
        }
    }

    @Test
    public void parseUserInput_invalidCharacter() {
        try {
            parser.parseUserInput("todo hello | world", tasks);
            fail("An invalid user input was not caught.");
        } catch (GrokInvalidUserInputException e) {
            return;
        }
    }

    @Test
    public void parseUserInput_invalidCommand() {
        try {
            parser.parseUserInput("deadline something s/o/met/hing / / ww", tasks);
            fail("An invalid user input was not caught.");
        } catch (GrokInvalidUserInputException e) {
            return;
        }
    }
}
