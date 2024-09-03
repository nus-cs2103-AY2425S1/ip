package sora;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest {
    @Test
    public void parseTest_emptyString() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("");
        assertEquals(expectedResult, Parser.parse(""));
    }

    @Test
    public void parseTest_oneWord() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Sora");
        assertEquals(expectedResult, Parser.parse("Sora"));
    }

    @Test
    public void parseTest_twoWord() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Sora"); expectedResult.add("Sora");
        assertEquals(expectedResult, Parser.parse("Sora Sora"));
    }

    @Test
    public void parseTest_threeWord() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Sora"); expectedResult.add("Sora Sora");
        assertEquals(expectedResult, Parser.parse("Sora Sora Sora"));
    }

    @Test
    public void parseTest_threeWord_withSlash() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Sora"); expectedResult.add("Sora"); expectedResult.add("Sora");
        assertEquals(expectedResult, Parser.parse("Sora Sora /Sora"));
    }

    @Test
    public void parseTest_fourWord_withSlash() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("Sora"); expectedResult.add("Sora"); expectedResult.add("Sora");expectedResult.add("Sora");
        assertEquals(expectedResult, Parser.parse("Sora Sora /Sora /Sora"));
    }

    @Test
    public void parseDate_empty() {
        assertNull(Parser.parseDate(""));
    }

    @Test
    public void parseDate_dayMonthYearHourMinute() {
        assertEquals(LocalDateTime.of(2024, 8, 28, 10, 0), Parser.parseDate("28/8/2024 1000"));
    }

}
