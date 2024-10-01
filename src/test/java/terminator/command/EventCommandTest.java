package terminator.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventCommandTest {

    @Test
    public void execute_validInput_success() throws TerminatorException {
        EventCommand event = new EventCommand("xyz /from 01/01/1234 1234 /to 02/01/1234 1234");
        String response = event.execute(new ArrayList<>());
        String expectedOutput = """
                Mission parameters updated. Added new objective:
                
                [E][ ] xyz from: Jan 1 1234 12:34 to: Jan 2 1234 12:34""";
        assertEquals(expectedOutput, response);
    }

    @Test
    public void execute_startDateAfterEndDate_errorMessage() throws TerminatorException {
        EventCommand event = new EventCommand("xyz /from 01/02/2020 1200 /to 31/01/2020 1200");
        String response = event.execute(new ArrayList<>());
        String expectedOutput = "Error: end date cannot be before start date.";
        assertEquals(expectedOutput, response);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "-1", "0", "200", "50", "9000", "2360", "11111", "000000"})
    public void execute_invalidTimeFormat_exceptionThrown(String timeFmt) {
        TerminatorException te = assertThrows(TerminatorException.class, () -> {
            EventCommand event = new EventCommand("xyz /from 01/02/2020 " + timeFmt + " /to 31/01/2020 0000");
            event.execute(new ArrayList<>());
        });

        String expectedOutput =  """
                    Error: invalid date time input.\n
                    Months should be between 1-12, and days should be between 1-31.
                    The hour should be between 00 to 23, and the minute should be between 00 and 59.""";
        String response = te.getMessage();
        assertTrue(response.contains(expectedOutput));
    }

    @ParameterizedTest
    @ValueSource(strings = {"00/01/2020", "00/00/2020", "100/2000/300000", "20/20/99999"})
    public void execute_invalidDateFormat_exceptionThrown(String dateFmt) {
        TerminatorException te = assertThrows(TerminatorException.class, () -> {
            EventCommand event = new EventCommand("xyz /from " + dateFmt + " 2359 /to 32/02/2020 2359");
            event.execute(new ArrayList<>());
        });

        String expectedOutput =  """
                    Error: invalid date time input.\n
                    Months should be between 1-12, and days should be between 1-31.
                    The hour should be between 00 to 23, and the minute should be between 00 and 59.""";
        String response = te.getMessage();
        assertTrue(response.contains(expectedOutput));
    }

}
