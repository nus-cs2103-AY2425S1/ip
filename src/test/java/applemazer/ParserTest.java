package applemazer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private Parser parser;

    private void testInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Applemazer.sc = new Scanner(System.in);
        parser = new Parser(Applemazer.sc);
    }

    @Test
    public void testParseTodoCommandPass() {
        testInput("todo read book");
        String command = Applemazer.sc.next();
        assertDoesNotThrow(() -> parser.parse(command));
    }

    @Test
    public void testParseTodoCommandFail() {
        testInput("todo "); // Space to simulate rest of the empty line.
        String command = Applemazer.sc.next();
        Exception exception = assertThrows(Exception.class, () -> parser.parse(command));
        assertEquals("""
                     OOPS!!! The description of a todo cannot be empty.
                     Try todo <description>.
                     """, exception.getMessage());
    }

    @Test
    public void testParseDeadlineCommandPass() {
        testInput("deadline read book /by 02/02/2000");
        String command = Applemazer.sc.next();
        assertDoesNotThrow(() -> parser.parse(command));
    }

    @Test
    public void testParseDeadlineCommandFail() {
        testInput("deadline read book /from 02/02/2000");
        String command = Applemazer.sc.next();
        Exception exception = assertThrows(Exception.class, () -> parser.parse(command));
        assertEquals("""
                     OOPS!!! The description of deadline is wrong.
                     Try 'deadline <description> /by <yyyy-mm-dd> <HHmm>'
                         'deadline <description> /by <dd/MM/yyyy> <HHmm>'.
                     It is not necessary to input the time!
                     """, exception.getMessage());
    }

    @Test
    public void testParseEventPassCommandPass() {
        testInput("event read book /from 02/02/2000 /to 02/03/2000 1800");
        String command = Applemazer.sc.next();
        assertDoesNotThrow(() -> parser.parse(command));
    }

    @Test
    public void testParseEventFailCommandFail() {
        testInput("event read book /from 02/02/2000 /from 02/03/2000 1800");
        String command = Applemazer.sc.next();
        Exception exception = assertThrows(Exception.class, () -> parser.parse(command));
        assertEquals("""
                     OOPS!!! The description of event is wrong.
                     Try 'event <description> /from <date1> /to <date2>'.
                     <date> should be <yyyy-mm-dd> <HHmm> or <dd/MM/yyyy> <HHmm>.
                     It is not necessary to input the time!
                     """, exception.getMessage());
    }
}
