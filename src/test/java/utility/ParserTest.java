package utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    
    @Test
    public void extractCommandTest() {
        String testCommand = "event project meeting /from Mon 2pm /to 4pm";
        assertEquals("event", Parser.extractCommand(testCommand));
    }
    
    @Test
    public void extractDescriptionTest() {
        String testCommand = "project meeting /from Mon 2pm /to 4pm";
        assertEquals("project meeting", Parser.extractDescription(testCommand));
    }
    
    @Test
    public void extractFirstDateTest() {
        String testCommand = "event project meeting /from 2023-09-08 /to 4pm";
        System.out.println(Parser.extractFirstDate(testCommand));
        assertEquals("2023-09-08", Parser.extractFirstDate(testCommand));
    }
    
    @Test
    public void extractSecondDateTest() {
        String testCommand = "event project meeting /from 2023-09-08 /to 2024-05-01";
        assertEquals("2024-05-01", Parser.extractSecondDate(testCommand));
    }
}
