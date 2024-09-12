package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void isValidDateTest1() {
        assertEquals(false, Parser.checkValidDate("2022-22-22"));
    }

    @Test
    public void isValidDateTest2() {
        assertEquals(false, Parser.checkValidDate(""));
    }

    @Test
    public void isValidDateTest3() {
        assertEquals(false, Parser.checkValidDate(" "));
    }

    @Test
    public void isValidDateTest4() {
        assertEquals(false, Parser.checkValidDate("2022-22-a2"));
    }

    @Test
    public void isValidDateTest5() {
        assertEquals(false, Parser.checkValidDate("2022 22 22"));
    }

    @Test
    public void isValidDateTest6() {
        assertEquals(false, Parser.checkValidDate("2022-1-22"));
    }

    @Test
    public void isValidDateTest7() {
        assertEquals(true, Parser.checkValidDate("2022-01-22"));
    }
}
