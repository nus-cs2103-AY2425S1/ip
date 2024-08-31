package ScoobyDoo;
import exception.InputFormatException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void getEventDescription_normalInput_writtenCorrectly() throws Exception{
        assertEquals("cook lunch",
                Parser.getEventDescription("deadline cook lunch /from 2024-12-31 12:12 /to 2024-12-31 13:13"));
    }

    @Test
    public void getEventDescription_wrongInput_throwsException() throws Exception{
        try {
            assertEquals(0, Parser.getEventDescription("deadline cook lunch /from 2024-12-31 12:12"));
            fail();
        } catch (InputFormatException e){
            assertEquals("Oops! I need a /from and a /to regex to save your event task", e.getMessage());
        }
    }

    @Test
    public void getDeadlineDescription_normalInput_writtenCorrectly() throws Exception {
        assertEquals("cook lunch",
                Parser.getDeadlineDescription("deadline cook lunch /by 2024-12-31 12:12"));
    }
}
