package Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Exceptions.DelphiException;
import Parser.DateParser;

public class EventTest {

    class DateParserStub extends DateParser {
        @Override
        public String parseAndFormatDateTime(String dateTimeString) {
            if (dateTimeString.equals("01/01/2024")) {
                return "1st january 2023";
            } else {
                return "11th january 2023";
            }
        }
    }
    private DateParserStub d = new DateParserStub();
    @Test
    public void eventDescription_formatting_exceptionThrown() {
        try {
            assertEquals("[E][ ] zoom meeting (from: 1st january 2023 to: 11th january 2023)",
                    new Event("zoom meeting /from 01/01/2024 /to 11/11/2023", d).toString());

            assertEquals("[E][ ] zoom meeting (from: 1st january 2023)",
                    new Event("zoom meeting /from 01/01/2024", d).toString());

            fail(); // should not reach here
        } catch (DelphiException e) {
            assertEquals("the input you have provided me is not formatted correctly. "
                    + "Please give me an input starting with todo, deadline or event", e.getMessage());
        }
    }
}
