package chacha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private ChaCha chacha = new ChaCha("./src/main/java/chacha/data/chacha.txt");
    private Parser parser = new Parser(chacha);

    @Test
    public void doAddEventCommand_correctInput_stringOutput() {
        String input = "event meeting /2024-03-04 /from 10am /to 10pm";
        String actual = parser.doAddEventCommand(input).split("\n")[2]
                .substring(7);
        String expected = "[E][ ] meeting (Mar 4 2024 from: 10am to: 10pm)";
        assertEquals(expected, actual);
    }

    @Test
    public void doAddEventCommand_dateWrongFormat_exceptionMessage() {
        String input = "event meeting /08-09-2024 /from 2pm /to 4pm";
        String actual = parser.doAddEventCommand(input). split("\n")[1]
                .substring(5);
        String expected = "Please input the date in the format YYYY-MM-DD. ";
        assertEquals(expected, actual);
    }

    @Test
    public void doAddEventCommand_missingComponent_exceptionMessage() {
        String input = "event meeting /2024-09-08";
        String actual = parser.doAddEventCommand(input). split("\n")[1]
                .substring(5);
        String expected = "You are missing some info needed (task description, date, start time, end time).";
        assertEquals(expected, actual);
    }

    @Test
    public void doDeleteCommand_missingIndex_exceptionMessage() {
        String input = "delete 20";
        String actual = parser.doDeleteCommand(input).split("\n")[1]
                .substring(5);
        String expected = "I can't find such a task number... You don't enough tasks! ";
        assertEquals(expected, actual);
    }

    @Test
    public void doDeleteCommand_indexIsNotInt_exceptionMessage() {
        String input = "delete hello";
        String actual = parser.doDeleteCommand(input).split("\n")[1]
                .substring(5);
        String expected = "The index does not seem to be a number... Please type again. ";
        assertEquals(expected, actual);
    }
}
