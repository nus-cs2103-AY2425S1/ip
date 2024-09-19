package pixy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pixy.Pixy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixyTest {

    private Pixy pixy;

    @BeforeEach
    public void setUp() {
        pixy = new Pixy(); // Initialize the Pixy object
    }
    @Test
    public void testGetResponse_basicCommand() {
        // Check initial task count
        int initialTaskCount = pixy.getTaskCount();

        String input = "todo Read book";
        String response = pixy.getResponse(input);

        // Validate the response
        assertEquals("Added new todo: Read book. You now have " + (initialTaskCount + 1) + " task(s).", response);
    }

    @Test
    public void testGetCommandType_afterTodoCommand() {
        pixy.getResponse("todo Read book");
        String commandType = pixy.getCommandType();
        assertEquals("todo", commandType);
    }
}

