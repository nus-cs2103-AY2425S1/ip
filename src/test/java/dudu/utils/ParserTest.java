package dudu.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import dudu.command.AddCommand;
import org.junit.jupiter.api.Test;

import dudu.command.Command;
import dudu.command.InvalidCommand;
import dudu.exception.DuduException;
import dudu.exception.InvalidFormatException;
import dudu.exception.MissingDateTimeException;
import dudu.exception.MissingDescriptionException;
import dudu.task.Deadline;




public class ParserTest {
    @Test
    public void parseInvalid_invalidCommand_invalidCommandReturned() {
        try {
            Command outputCommand = Parser.parse("invalid");
            Command expectedCommand = new InvalidCommand();
            assertEquals(expectedCommand, outputCommand);
        } catch (MissingDescriptionException exception) {
            System.out.println(exception);
        } catch (InvalidFormatException exception) {
            System.out.println(exception);
        } catch (DuduException exception) {
            System.out.println(exception);
        } catch (MissingDateTimeException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void parseDeadline_validDate_addCommandReturned() {
        try {
            Command command = Parser.parse("deadline this /by 2024-08-30");
            Deadline task = new Deadline("this", LocalDate.parse("2024-08-30"));
            assertEquals(new AddCommand(task, false), command);
        } catch (MissingDescriptionException exception) {
            System.out.println(exception);
        } catch (InvalidFormatException exception) {
            System.out.println(exception);
        } catch (DuduException exception) {
            System.out.println(exception);
        } catch (MissingDateTimeException exception) {
            System.out.println(exception);
        }
    }

    @Test
    public void parseEvent_invalidDate_addCommandReturned() {
        try {
            Parser.parse("deadline this /by 2024-08-30");
        } catch (MissingDescriptionException exception) {
            System.out.println(exception);
        } catch (InvalidFormatException exception) {
            System.out.println(exception);
        } catch (DuduException exception) {
            assertEquals(exception.getMessage(), "Please use YYYY-MM-DD as the date format");
            System.out.println(exception);
        } catch (MissingDateTimeException exception) {
            System.out.println(exception);
        }
    }
}
