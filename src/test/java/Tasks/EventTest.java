package Tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import Exceptions.DelphiException;
import Parser.Parser;

public class EventTest {

    class ParserStub extends Parser {
        @Override
        public String[] parseEvent(String dateTimeString) {
            String[] res = new String[3];
            res[0] = "zoom meeting";
            res[1] = "from: 1st january 2023";
            res[2] = "to: 11th january 2023";
            return res;
        }
    }
    private ParserStub d = new ParserStub();

    @Test
    public void eventDescription_formatting_exceptionThrown() {
        try {
            assertEquals("[E][ ] zoom meeting (from: 1st january 2023 to: 11th january 2023)",
                    new Event("zoom meeting /from 01/01/2024 /to 11/11/2023", d).toString());
        } catch (DelphiException e) {
            fail();
        }
    }
}
