package screwllum.utils;

import org.junit.jupiter.api.Test;
import screwllum.exception.InvalidCommandException;
import screwllum.exception.InvalidDateFormatException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void parseUserInput_validInputs_success() throws InvalidCommandException, InvalidDateFormatException {
        assertEquals(List.of("bye"), Parser.parseUserInput("bYe _  r a n dom/stuff/that*follows'!"));
        assertEquals(List.of("list"), Parser.parseUserInput("liSt _ r a n dom/stuff/that*follows'!"));
        assertEquals(List.of("delete", "5"), Parser.parseUserInput("dEleTe 5 /r a ndom stuff!"));
        assertEquals(List.of("toggle", "1"), Parser.parseUserInput("toGglE 1/Stuff@3"));
        assertEquals(List.of("todo", "someTask"), Parser.parseUserInput("todo   someTask  /this is ignored"));
        
        String[] validDates = new String[]{"2022-01-05", "2011-06-6", "2000-7-13", "2003-4-3"};
        for (String validDate : validDates) {
            assertEquals(List.of("deadline", "name", validDate),
                    Parser.parseUserInput("deadline  name /by    " + validDate + " /Should be ignored"));
            assertEquals(List.of("event", "name", validDate, validDate),
                    Parser.parseUserInput("event  name /from    " + validDate + " /to " + validDate));
        }
    }
}
