package nah.parser;
import nah.command.Command;
import nah.data.Task;
import nah.exceptions.NahException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test ()
    public void dummyTest(){
        try {
            assertEquals("[T] [ ] nothing",
                    Parser.parse("T |  | nothing").toString());

        } catch (NahException e) {
            fail("Unexpected exception");
        }
    }
    @Test
    public void dummyTest2(){
        assertEquals(2, 2);
    }
}

