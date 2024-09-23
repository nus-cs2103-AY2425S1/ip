package rizzler.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateTimeParserTest {

    @Test
    public void toStr_emptyInput_emptyOutput() {
        assertEquals("", DateTimeParser.toStr(""));
    }

    @Test
    public void toStr_whitespaceInput_whitespaceOutput() {
        assertEquals(" ", DateTimeParser.toStr(" "));

        assertEquals("\t", DateTimeParser.toStr("\t"));

        assertEquals("     ", DateTimeParser.toStr("     "));
    }

    @Test
    public void toStr_recommendedFormat_intendedOutput() {
        assertEquals("23 September, 2024", DateTimeParser.toStr("2024-09-23"));

        assertEquals("28 February, 8019", DateTimeParser.toStr("8019-02-28"));

        assertEquals("31 December, 0001", DateTimeParser.toStr("0001-12-31"));
    }

    @Test
    public void toStr_invalidFormat_identicalOutput() {
        String firstInput = "202-03-12";
        String secondInput = "2024-13-10";
        String thirdInput = "9999-01-50";
        String fourthInput = "01-01-2024";

        assertEquals(firstInput, DateTimeParser.toStr(firstInput));

        assertEquals(secondInput, DateTimeParser.toStr(secondInput));

        assertEquals(thirdInput, DateTimeParser.toStr(thirdInput));

        assertEquals(fourthInput, DateTimeParser.toStr(fourthInput));
    }

    @Test
    public void toStr_singleWord_singleWordOutput() {
        assertEquals("hello", DateTimeParser.toStr("hello"));
    }

    @Test
    public void toStr_inputsWithWhitespace_identicalOutputs() {
        String firstInput = "hello     ";
        String secondInput = "hello      there";
        String thirdInput = "      hello     the     re     ";
        String fourthInput = "     by   tomorrow hopefully";
        String fifthInput = "andanotherthing      by next year";

        assertEquals(firstInput, DateTimeParser.toStr(firstInput));

        assertEquals(secondInput, DateTimeParser.toStr(secondInput));

        assertEquals(thirdInput, DateTimeParser.toStr(thirdInput));

        assertEquals(fourthInput, DateTimeParser.toStr(fourthInput));

        assertEquals(fifthInput, DateTimeParser.toStr(fifthInput));
    }
}
