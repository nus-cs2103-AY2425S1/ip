package casper.components;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import casper.exceptions.NoArgumentException;

public class ParserTest {
    @Test
    public void parseInputToCommandAndArgument_commandAndArgument_success() {
        String input = "todo finish homework";
        String[] result = Parser.parseInputToCommandAndArgument(input);
        assertArrayEquals(new String[]{"todo", "finish homework"}, result);
    }

    @Test
    public void parseInputToCommandAndArgument_onlyCommand_success() {
        String input = "list";
        String[] result = Parser.parseInputToCommandAndArgument(input);
        assertArrayEquals(new String[]{"list"}, result);
    }

    @Test
    public void parseInputToCommandAndArgument_emptyInput_success() {
        String input = "";
        String[] result = Parser.parseInputToCommandAndArgument(input);
        assertArrayEquals(new String[]{""}, result);
    }

    @Test
    public void parseArgumentByKeyword_argumentWithKeyword_success() {
        String argument = "finish homework /by tomorrow";
        String keyword = "by";
        String[] result = Parser.parseArgumentByKeyword(argument, keyword);
        assertArrayEquals(new String[]{"finish homework", "tomorrow"}, result);
    }

    @Test
    public void parseArgumentByKeyword_argumentWithoutKeyword_noSplit() {
        String argument = "finish homework";
        String keyword = "by";
        String[] result = Parser.parseArgumentByKeyword(argument, keyword);
        assertArrayEquals(new String[]{argument}, result);
    }

    @Test
    public void parseArgumentByKeyword_keywordAtStart_success() {
        String argument = " /by tomorrow finish homework";
        String keyword = "by";
        String[] result = Parser.parseArgumentByKeyword(argument, keyword);
        assertArrayEquals(new String[]{"", "tomorrow finish homework"}, result);
    }

    @Test
    public void argumentCheck_nonNullArgument_success() throws NoArgumentException {
        String argument = "finish homework";
        String command = "todo";
        Parser.argumentCheck(argument, command);
    }

    @Test
    public void argumentCheck_nullArgument_exceptionThrown() {
        try {
            String argument = null;
            String command = "todo";
            Parser.argumentCheck(argument, command);
            fail();
        } catch (NoArgumentException e) {
            assertEquals("ERROR: No arguments for todo.\nPlease provide the necessary arguments.", e.getMessage());
        }
    }
}
