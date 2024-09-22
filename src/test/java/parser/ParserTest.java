package parser;

import java.util.ArrayList;
import java.util.Arrays;

import commands.AddCommand;
import commands.Command;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.ExitCommand;
import exceptions.GrokInvalidUserInputException;
import tasklist.TaskList;
import tasks.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private static Parser parser;
    private static TaskList tasks;

    @BeforeAll
    public static void setup() {
        parser = new Parser();
        tasks = new TaskList(new ArrayList<>());
    }

    @Test
    public void parseUserInput_validExitCommand() {
        try {
            assertEquals(new ExitCommand(), parser.parseUserInput("bye", tasks));
        } catch (GrokInvalidUserInputException e) {
            fail("Exception was thrown for valid commands");
        }
    }
    @Test
    public void parseUserInput_validAddCommands() {
        ArrayList<String> validInputs = new ArrayList<>(Arrays.asList(
                "todo xyz",
                "todo x y z",
                "deadline xyz /by 2020-10-10",
                "deadline x y z /by 2020-10-10",
                "deadline xyz /by 2030-08-31",
                "deadline xyz /by 2028-02-29",
                "event xyz /from 2020-10-10 /to 2020-10-11",
                "event x y z /from 2020-10-10 /to 2020-10-11",
                "event xyz /from 1999-10-20 /to 2000-02-10"
        ));

        // coupling of the commands and parser tests here is quite unavoidable,
        // as the purpose of the parser is to convert user input to commands.
        assertTrue(
            validInputs
                    .stream()
                    .map(s -> {
                        try {
                            return parser.parseUserInput(s, tasks);
                        } catch (GrokInvalidUserInputException e) {
                            fail("Exception was thrown for valid commands");
                        }
                        return null;
                    })
                    .allMatch(c -> c instanceof AddCommand)
        );
    }

    @Test
    public void parseUserInput_invalidCharacter() {
        try {
            parser.parseUserInput("todo hello | world", tasks);
            fail("An invalid user input was not caught.");
        } catch (GrokInvalidUserInputException e) {
            // Empty catch clause here: an exception is the expected pass state for this test.
            return;
        }
    }

    @Test
    public void parseUserInput_invalidCommand() {
        try {
            parser.parseUserInput("deadline something s/o/met/hing / / ww", tasks);
            fail("An invalid user input was not caught.");
        } catch (GrokInvalidUserInputException e) {
            // Empty catch clause here: an exception is the expected pass state for this test.
            return;
        }
    }
}
