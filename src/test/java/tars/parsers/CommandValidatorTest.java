package tars.parsers;

import tars.exceptions.TarsException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class CommandValidatorTest {
    @Test
    public void deadlineCommandValidation_missingByCommand_exceptionThrown() {

        String[] missingByCommand = new String[]{"29-12-24"};
        String[] wrongDeadlineDateCommand = new String[]{"wrong", "29-12-24"};

        try {
            CommandValidator.validate(missingByCommand, CommandValidator.CommandType.BY);
            fail();
        } catch (TarsException e) {
            assertEquals("Add the /by command", e.getMessage());
        }

        try {
            CommandValidator.validate(wrongDeadlineDateCommand, CommandValidator.CommandType.BY);
            fail();
        } catch (TarsException e) {
            assertEquals("Add the /by command", e.getMessage());
        }
    }

    @Test
    public void deadlineCommandValidation_missingDeadlineDate_exceptionThrown() {

        String[] missingDeadlineDate = new String[]{"by"};

        String[] emptyDeadlineDate = new String[]{"by", " "};

        try {
            CommandValidator.validate(missingDeadlineDate, CommandValidator.CommandType.BY);
            fail();
        } catch (TarsException e) {
            assertEquals("Finish the command by adding a deadline date", e.getMessage());
        }

        try {
            CommandValidator.validate(emptyDeadlineDate, CommandValidator.CommandType.BY);
            fail();
        } catch (TarsException e) {
            assertEquals("Finish the command by adding a deadline date", e.getMessage());
        }

    }

    @Test
    public void eventCommandValidation_missingFromCommand_exceptionThrown() {

        String[] missingFromCommand = new String[]{"29-12-24"};
        String[] wrongStartDateCommand = new String[]{"wrong", "29-12-24"};

        try {
            CommandValidator.validate(missingFromCommand, CommandValidator.CommandType.FROM);
            fail();
        } catch (TarsException e) {
            assertEquals("Add the /from command", e.getMessage());
        }

        try {
            CommandValidator.validate(wrongStartDateCommand, CommandValidator.CommandType.FROM);
        } catch (TarsException e) {
            assertEquals("Wrong command being used. Use the /from command instead", e.getMessage());
        }
    }

    @Test
    public void eventCommandValidation_missingStartDate_exceptionThrown() {

        String[] missingStartDate = new String[]{"from"};

        String[] emptyStartDate = new String[]{"from", " "};

        try {
            CommandValidator.validate(missingStartDate, CommandValidator.CommandType.FROM);
            fail();
        } catch (TarsException e) {
            assertEquals("Add an event start date", e.getMessage());
        }

        try {
            CommandValidator.validate(emptyStartDate, CommandValidator.CommandType.FROM);
            fail();
        } catch (TarsException e) {
            assertEquals("Finish the command by adding an event start date", e.getMessage());
        }
    }

    @Test
    public void eventCommandValidation_missingToCommand_exceptionThrown() {

        String[] missingToCommand = new String[]{"29-12-24"};
        String[] wrongEndDateCommand = new String[]{"wrong", "29-12-24"};

        try {
            CommandValidator.validate(missingToCommand, CommandValidator.CommandType.TO);
            fail();
        } catch (TarsException e) {
            assertEquals("Add the /to command", e.getMessage());
        }

        try {
            CommandValidator.validate(wrongEndDateCommand, CommandValidator.CommandType.TO);
            fail();
        } catch (TarsException e) {
            assertEquals("Wrong command being used. Use the /to command instead", e.getMessage());
        }
    }

    @Test
    public void eventCommandValidation_missingEndDate_exceptionThrown() {

        String[] missingEndDate = new String[]{"to"};

        String[] emptyEndDate = new String[]{"to", " "};

        try {
            CommandValidator.validate(missingEndDate, CommandValidator.CommandType.TO);
            fail();
        } catch (TarsException e) {
            assertEquals("Add an event end date", e.getMessage());
        }

        try {
            CommandValidator.validate(emptyEndDate, CommandValidator.CommandType.TO);
            fail();
        } catch (TarsException e) {
            assertEquals("Finish the command by adding an event end date", e.getMessage());
        }
    }
}
