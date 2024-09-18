package dudu.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import dudu.command.Command;
import dudu.command.InvalidCommand;
import dudu.exception.InvalidFormatException;
import dudu.exception.MissingDateTimeException;
import dudu.exception.MissingDescriptionException;

public class ParserTest {
    @Test
    public void parse_invalidCommand_invalidCommandReturned() {
        try {
            Command outputCommand = Parser.parse("invalid");
            Command expectedCommand = new InvalidCommand();
            assertEquals(expectedCommand, outputCommand);
        } catch (MissingDescriptionException exception) {
            System.out.println(exception);
        } catch (InvalidFormatException exception) {
            System.out.println(exception);
        } catch (DateTimeParseException exception) {
            System.out.println(exception);
        } catch (MissingDateTimeException exception) {
            System.out.println(exception);
        }
    }
//    @Test
//    public void testDeadlineCommand() {
//        try {
//            Command command = Parser.parse("deadline this /by 2024-08-30");
//            Deadline task = new Deadline("this", LocalDate.parse("2024-08-30"));
//            assertEquals(new CommandDeadline(task, false), command);
//        } catch (MissingDescriptionException exception) {
//            System.out.println(exception);
//        } catch (InvalidFormatException exception) {
//            System.out.println(exception);
//        } catch (DateTimeParseException exception) {
//            System.out.println(exception);
//        } catch (MissingDateTimeException exception) {
//            System.out.println(exception);
//        }
//    }

//    @Test
//    public void testListCommand() {
//        try {
//            Command command = Parser.parse("list");
//            assertEquals(new CommandList(), command);
//        } catch (MissingDescriptionException exception) {
//            System.out.println(exception);
//        } catch (InvalidFormatException exception) {
//            System.out.println(exception);
//        } catch (DateTimeParseException exception) {
//            System.out.println(exception);
//        } catch (MissingDateTimeException exception) {
//            System.out.println(exception);
//        }
//    }
}
