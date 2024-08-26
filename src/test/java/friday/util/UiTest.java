package friday.util;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void readCommand_readsCorrectInput() {
        String input = "list\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();

        String command = ui.readCommand();

        assertEquals("list", command);
    }

    @Test
    void printMessage_printsCorrectly() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        Ui ui = new Ui();
        String message = "\tGood Bye. Hope to see you again soon!";

        ui.showGoodbye();

        assertEquals(message + System.lineSeparator(), outputStream.toString());

        System.setOut(originalOut);
    }
}
