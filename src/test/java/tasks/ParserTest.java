package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.Duck;

public class ParserTest {
    @Test
    public void createEvent_invalidDateTime_fail() throws Exception {

        assertEquals(
                "java.time.format.DateTimeParseException: Text '2029-20-08' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 20",
                new Duck().getResponse("event lesson /from 2029-20-08 /to 2030-07-08"));
    }

    @Test
    public void createDoAfter_missingArgs_fail() throws Exception {
        assertEquals(
                "usage: after <description> /after <earliest_date>\n" +
                        "error: the following arguments are required: description, earliest_date\n" +
                        "Missing args: earliest_date",
                new Duck().getResponse("doafter clean up 2024-09-20"));
    }
}
