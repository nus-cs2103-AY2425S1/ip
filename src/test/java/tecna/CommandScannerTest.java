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

        assertEquals(tecna.CommandType.INVALID, commandScanner.getRequest());
    }

    @Test
    public void getRequest_todoWithBlank_todoWrongFormat() {
        String input = "todo ";
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);

        CommandScanner commandScanner = new CommandScanner();

        assertEquals(CommandType.TODO_WRONG_FORMAT, commandScanner.getRequest());
    }
}
