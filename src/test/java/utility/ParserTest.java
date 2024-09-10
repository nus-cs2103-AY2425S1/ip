package utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.LukeException;

class ParserTest {
    private Parser parser;
    @BeforeEach
    public void startUp() {
        parser = new Parser();
    }

    @AfterEach
    public void tearDown() {
        parser = null;
    }

    @Test
    public void parseDeadLine_noSpacingBeforeSlashBy_exceptionThrown() {
        try {
            parser.parse("deadline something/by 2025-02-21");
            assertEquals("something", parser.getDescription());
            assertEquals("Feb 21 2025", parser.getBy());
            fail();
        } catch (LukeException e) {
            assertEquals("There needs to be spacing between /by and other words.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_noSpacingAfterSlashBy_exceptionThrown() {
        try {
            parser.parse("deadline something /by2025-02-21");
            assertEquals("something", parser.getDescription());
            assertEquals("Feb 21 2025", parser.getBy());
            fail();
        } catch (LukeException e) {
            assertEquals("There needs to be spacing between /by and other words.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_noSpacingAroundSlashBy_exceptionThrown() {
        try {
            parser.parse("deadline something/by2025-02-21");
            assertEquals("something", parser.getDescription());
            assertEquals("Feb 21 2025", parser.getBy());
            fail();
        } catch (LukeException e) {
            assertEquals("There needs to be spacing between /by and other words.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_missingSlashBy_exceptionThrown() {
        try {
            parser.parse("deadline something 2025-02-21");
            assertEquals("something", parser.getDescription());
            assertEquals("Feb 21 2025", parser.getBy());
            fail();
        } catch (LukeException e) {
            assertEquals("Yo! A necessary /by is missing from your input.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_badDateFormat_exceptionThrown() {
        try {
            parser.parse("deadline something /by 12-02-2024");
            assertEquals("something", parser.getDescription());
            assertEquals("Feb 12 2024", parser.getBy());
            fail();
        } catch (LukeException e) {
            assertEquals("Invalid Date format", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_correctInput_correctParserInternalValue() {
        try {
            parser.parse("deadline something /by 2024-02-12");
            assertEquals("something", parser.getDescription());
            assertEquals("Feb 12 2024", parser.getBy());
        } catch (LukeException e) {
            fail();
        }
    }
}
