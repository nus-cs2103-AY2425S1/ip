package dateandtime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import errorhandling.ReginaException;

public class ReginaDateAndTimeTest {

    @Test
    public void testValidInput() {
        String actualInvalidOutput = "";
        String actualValidOutput = "";
        String expectedInvalidOutput = "Ehh you need to write BOTH the date and time";
        String expectedValidOutput = "2024-12-12 17:00";
        try {
            ReginaDateAndTime r1 = new ReginaDateAndTime("12/12/2024 1700"); // valid
            actualValidOutput = r1.getDate().toString() + " " + r1.getTime().toString();
            new ReginaDateAndTime("12/12/2024"); // invalid
        } catch (ReginaException e) {
            actualInvalidOutput = e.getMessage();
        }
        assertEquals(expectedInvalidOutput, actualInvalidOutput);
        assertEquals(expectedValidOutput, actualValidOutput);
    }

    @Test
    public void testPushBack() throws ReginaException {
        ReginaDateAndTime r1 = new ReginaDateAndTime("12/12/2024 1700");
        r1.pushBackTime(1);
        r1.pushBackDate(1);
        String expected = "Dec 13 2024, 5.01 pm";
        String actual = r1.toString();
        assertEquals(expected, actual);
    }
}
