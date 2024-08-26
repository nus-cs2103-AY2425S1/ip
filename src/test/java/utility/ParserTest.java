package utility;

import exceptions.LukeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Parser parser;
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
            parser.parseDeadLine("something/by 2025-02-21");
            assertEquals("something", parser.description);
            assertEquals("Feb 21 2025", parser.by);
            fail();
        } catch (LukeException e) {
            assertEquals("There needs to be spacing between /by and other words.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_noSpacingAfterSlashBy_exceptionThrown() {
        try {
            parser.parseDeadLine("something /by2025-02-21");
            assertEquals("something", parser.description);
            assertEquals("Feb 21 2025", parser.by);
            fail();
        } catch (LukeException e) {
            assertEquals("There needs to be spacing between /by and other words.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_noSpacingAroundSlashBy_exceptionThrown() {
        try {
            parser.parseDeadLine("something/by2025-02-21");
            assertEquals("something", parser.description);
            assertEquals("Feb 21 2025", parser.by);
            fail();
        } catch (LukeException e) {
            assertEquals("There needs to be spacing between /by and other words.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_missingSlashBy_exceptionThrown() {
        try {
            parser.parseDeadLine("something 2025-02-21");
            assertEquals("something", parser.description);
            assertEquals("Feb 21 2025", parser.by);
            fail();
        } catch (LukeException e) {
            assertEquals("Missing /by to indicate when the deadline of the task.", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_badDateFormat_exceptionThrown() {
        try {
            parser.parseDeadLine("something /by 12-02-2024");
            assertEquals("something", parser.description);
            assertEquals("Feb 12 2024", parser.by);
            fail();
        } catch (LukeException e) {
            assertEquals("Invalid Date format", e.getMessage());
        }
    }

    @Test
    public void parseDeadLine_correctInput_correctParserInternalValue() {
        try {
            parser.parseDeadLine("something /by 2024-02-12");
            assertEquals("something", parser.description);
            assertEquals("Feb 12 2024", parser.by);
        } catch (LukeException e) {
            fail();
        }
    }
}
