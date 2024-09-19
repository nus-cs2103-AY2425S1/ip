package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.DelphiException;
import parser.Parser;

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
    private final ParserStub d = new ParserStub();

    @Test
    public void eventDescription_formatting_exceptionThrown() {
        try {
            assertEquals("[E][ ] zoom meeting (from: 1st january 2023 to: 11th january 2023)",
                    new Event(d.parseEvent("")).toString());
        } catch (DelphiException e) {
            fail();
        }
    }
}
