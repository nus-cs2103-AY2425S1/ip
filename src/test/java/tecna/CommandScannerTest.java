package tecna;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandScannerTest {

    @Test
    public void getRequest_listWithNumber_invalidRequest() {
        String input = "list 2";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(CommandType.INVALID, commandScanner.getRequest());
    }

    @Test
    public void getRequest_validTodo_commandTypeTodo() {
        String input = "todo go to work";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(tecna.CommandType.TODO, commandScanner.getRequest());
    }

    @Test
    public void getRequest_validDeadline_commandTypeDeadline() {
        String input = "deadline go to work /by 2024-03-02 1200";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(tecna.CommandType.DEADLINE, commandScanner.getRequest());
    }

    @Test
    public void getRequest_validEvent_commandTypeEvent() {
        String input = "event go to work /from 2024-03-02 1200 /to 2024-03-02 1700";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(tecna.CommandType.EVENT, commandScanner.getRequest());
    }

    @Test
    public void getRequest_invalidDeadline_commandTypeDeadlineWrongFormat() {
        String input = "deadline go to work /by tonight";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(CommandType.DEADLINE_WRONG_FORMAT, commandScanner.getRequest());
    }

    @Test
    public void getRequest_invalidEvent_commandTypeEventWrongFormat() {
        String input = "event go to work /from tonight";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(CommandType.EVENT_WRONG_FORMAT, commandScanner.getRequest());    }

    @Test
    public void getRequest_todoWithBlank_commandTypeTodoWrongFormat() {
        String input = "todo ";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(CommandType.TODO_WRONG_FORMAT, commandScanner.getRequest());
    }
}
