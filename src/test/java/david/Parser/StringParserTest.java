package david.Parser;

import david.Exceptions.DavidInvalidArgumentsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringParserTest {
    @Test
    public void parseEmptyString_EmptyStringReturned() {
        //driver
        String actual = StringParser.parseStringToCommand("");
        assertEquals("", actual,"Empty string returned when empty string passed");
    }

    @Test
    public void parseRandomValue_UpperCaseReturned() {
        //driver
        String actual = StringParser.parseStringToCommand("todo");
        assertEquals("TODO", actual,"Upper case version of input string returned");
    }

    @Test
    public void parseRandomValueWithNumber_UpperCaseReturned() {
        //driver
        String actual = StringParser.parseStringToCommand("todo123");
        assertEquals("TODO123", actual,"Upper case version of input string returned with numbers");
    }



    @Test
    public void parseEvent_EventDetailsReturned() throws DavidInvalidArgumentsException {
        String actual = StringParser.parseStringToArguments("todo eat meal");
        assertEquals("eat meal", actual,"event details returned only");
    }

    @Test
    public void parseEventWtihNoDetails_ExceptionThrown() {
        DavidInvalidArgumentsException exception =
                assertThrows(DavidInvalidArgumentsException.class
                        ,() -> StringParser.parseStringToArguments("todo "));
        assertEquals("Please enter valid arguments.", exception.showErrorMessage(),
                "Exception is thrown when there is no event details supplied");
    }

    @Test
    public void parseEventWtihNoDetailsAndNoWhiteSpace_ExceptionThrown() {
        DavidInvalidArgumentsException exception =
                assertThrows(DavidInvalidArgumentsException.class
                        ,() -> StringParser.parseStringToArguments("todo"));
        assertEquals("Please enter valid arguments.", exception.showErrorMessage(),
                "Exception is thrown when there is no event details supplied");
    }
}
