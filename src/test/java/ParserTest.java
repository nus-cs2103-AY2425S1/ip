import task.Task;
import utility.Parser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void testTime(){
        Parser parser = new Parser();
        assertEquals("2000-12-12T18:00", parser.parseDateTime("12/12/2000 1800").toString());
    }

    @Test
    public void testInvalidTime(){
        Parser parser = new Parser();
        assertNull(parser.parseDateTime("12/13/2000 1800"));
    }
}