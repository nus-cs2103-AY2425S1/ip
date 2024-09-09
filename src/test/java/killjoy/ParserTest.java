package killjoy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import killjoy.main.KillJoy;
import killjoy.main.UserInterface;
import killjoy.processing.Parser;
import killjoy.task.Task;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        UserInterface ui = new UserInterface(new KillJoy());
        parser = new Parser(ui);
    }

    @Test
    public void testParseUserInput_validInput() {
        String input = "todo /by 2024-08-31";
        Task.TaskType result = Parser.parseUserInput(input);
        assertEquals(Task.TaskType.TODO, result);
    }

    @Test
    public void testParseUserInput_invalidInput() {
        String input = "unknownTask /by 2024-08-31";
        Task.TaskType result = Parser.parseUserInput(input);
        assertNull(result);
    }

    @Test
    public void testGetDescription_todoTask() {
        String input = "todo read book /by 2024-08-31";
        String result = Parser.getDescriptionFromInput(input);
        assertEquals("read book ", result);
    }

    @Test
    public void testGetBy_validDate() {
        String input = "deadline submit assignment /by 2024-08-31 15:00";
        String result = Parser.getByTimeString(input);
        assertEquals("2024-08-31 15:00", result);
    }

    @Test
    public void testParseDateTime_validDateWithTime() {
        String dateTime = "2024-08-31 15:00";
        LocalDateTime result = Parser.parseStringToLocalDateTime(dateTime);
        assertNotNull(result);
        assertEquals(LocalDateTime.of(2024, 8, 31, 15, 0), result);
    }

    @Test
    public void testParseDateTime_validDateWithoutTime() {
        String dateTime = "2024-08-31";
        LocalDateTime result = Parser.parseStringToLocalDateTime(dateTime);
        assertNotNull(result);
        assertEquals(LocalDateTime.of(2024, 8, 31, 0, 0), result);
    }

    @Test
    public void testParseDateTime_invalidDate() {
        String dateTime = "invalid-date";
        LocalDateTime result = Parser.parseStringToLocalDateTime(dateTime);
        assertNull(result);
    }
}
