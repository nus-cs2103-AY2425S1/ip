package infinity.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import infinity.command.Command;
import infinity.infinityexception.InfinityException;

public class ParserTest {

    private void repeatedInvalidDateTests(String input) {
        try {
            assertEquals(Parser.parseDateTime(input), LocalDateTime.of(2024, 12, 1, 0, 59));
            fail();
        } catch (DateTimeException e) {
            assertEquals(e.getMessage(), "Invalid date time format\n");
        }
    }

    private void repeatedInvalidParseAndRunTests(String input, Command.KnownCommands command, String errorOutput) {
        try {
            assertEquals(Parser.parseAndRun((String i) -> "", input, command), "");
            fail();
        } catch (InfinityException e) {
            assertEquals(errorOutput, e.getMessage());
        }
    }

    @Test
    public void prependZero_singleDigit_zeroPrepended() {
        assertEquals(Parser.prependZero(1), "01");
        System.out.println("Test 1: 1 => '01' - passed");
    }

    @Test
    public void prependZero_doubleDigit_zeroNotPrepended() {
        assertEquals(Parser.prependZero(10), "10");
        System.out.println("Test 2: 10 => '10' - passed");
    }

    @Test
    public void parseDateTime_validDateGiven_objectLocalDateTimeReturned() {
        assertEquals(Parser.parseDateTime("01/12/2024 0059"),
                LocalDateTime.of(2024, 12, 1, 0, 59));
        assertEquals(Parser.parseDateTime("01-12-2024 0059"),
                LocalDateTime.of(2024, 12, 1, 0, 59));
        assertEquals(Parser.parseDateTime("1-1-2024 0000"),
                LocalDateTime.of(2024, 1, 1, 0, 0));
        System.out.println("Test 3: Valid Date - passed");
    }

    @Test
    public void parseDateTime_invalidDateGiven_exceptionDateTimeExceptionThrown() {
        repeatedInvalidDateTests("01/12/2024 2459");
        repeatedInvalidDateTests("01/12/2024 2360");
        repeatedInvalidDateTests("01/12/2024 2459");
        repeatedInvalidDateTests("01/12/2024 2360");
        repeatedInvalidDateTests("32/12/2024 2459");
        repeatedInvalidDateTests("00/12/2024 2459");
        repeatedInvalidDateTests("01/13/2024 2459");
        repeatedInvalidDateTests("01/00/2024 2459");
        System.out.println("Test 4: Invalid Dates - passed");
    }

    @Test
    public void parseDateTimeString_validDateObjectGiven_stringOutputReturned() {
        assertEquals(Parser.parseDateTimeString(LocalDateTime.of(2024, 12, 1, 0, 59)), "01-12-2024 0059");
        System.out.println("Test 5: Valid Dates to String - passed");
    }

    @Test
    public void parseDateTimeStringAlt_validDateObjectGiven_stringOutputReturned() {
        assertEquals(Parser.parseDateTimeStringAlt(LocalDateTime.of(2024, 12, 1, 0, 59)), "December 01, 2024, 0059");
        System.out.println("Test 6: Valid Dates to String Alt - passed");
    }

    @Test
    public void parseAndRun_validInputs_validBotOutput() {
        try {
            assertEquals(Parser.parseAndRun((String input) -> "", "find abc", Command.KnownCommands.FIND), "");
            assertEquals(Parser.parseAndRun((String input) -> "", "mark 1", Command.KnownCommands.MARK), "");
            assertEquals(Parser.parseAndRun((String input) -> "", "todo sometask", Command.KnownCommands.TODO), "");
            assertEquals(Parser.parseAndRun((String input) -> "", "delete 1", Command.KnownCommands.DELETE), "");
        } catch (InfinityException e) {
            fail();
        }
        System.out.println("Test 7: Parse and Run Valid - passed");
    }

    @Test
    public void parseAndRun_invalidInputs_infinityExceptionThrown() {
        repeatedInvalidParseAndRunTests("", Command.KnownCommands.FIND, "Insufficient arguments provided\n");
        repeatedInvalidParseAndRunTests("find ", Command.KnownCommands.FIND, "Insufficient arguments provided\n");
        System.out.println("Test 8: Parse and Run Invalid - passed");
    }

    @Test
    public void parseMultiOperations_variousInputs_variousOutputs() {
        assertArrayEquals(Parser.parseMultiOperations("mark 1 && 2 && 3", "mark"), new String[] {"1", "2", "3"});
        assertArrayEquals(Parser.parseMultiOperations("mark 1 && 2 && 3", ""), new String[] {"mark 1", "2", "3"});
        try {
            assertArrayEquals(Parser.parseMultiOperations("", "mark"), new String[] {""});
            fail();
        } catch (StringIndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "begin 4, end 0, length 0");
        }
        assertArrayEquals(Parser.parseMultiOperations("", ""), new String[] {""});
        System.out.println("Test 9: Multi Operation Parsing - passed");
    }
}
