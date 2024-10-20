import duke.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals("event", new Duke.Parser(new String("event summer break /from May 1 2024 /to 2024-08-01")).getArgument(' '));
    }

    @Test
    public void anotherDummyTest(){
        Duke.Parser parser = new Duke.Parser(new String("event summer break /from May 1 2024 /to 2024-08-01"));
        parser.getArgument(' ');
        assertEquals("summer break ", parser.getArgument('/', 4));
    }
}