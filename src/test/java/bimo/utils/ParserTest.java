package bimo.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bimo.command.Command;
import bimo.command.UnknownCommand;
import bimo.exception.BimoException;


public class ParserTest {
    @Test
    public void parse_invalidUserCommand_unknownCommand() {
        String userInput = "abcdeg 123";
        Parser parser = new Parser();
        try {
            Command command = parser.parse(userInput);
            boolean isUnknown = command instanceof UnknownCommand;
            assertEquals(isUnknown, true);
        } catch (BimoException e) {
            System.out.println("failure");
        }
    }
}
