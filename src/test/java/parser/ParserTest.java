package parser;

import exception.TakoException;
import org.junit.jupiter.api.Test;
import tako.Tako;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void isValidDateTest1() {
        assertEquals(false, Parser.isValidDate("2022-22-22"));
    }

    @Test
    public void isValidDateTest2() {
        assertEquals(false, Parser.isValidDate(""));
    }

    @Test
    public void isValidDateTest3() {
        assertEquals(false, Parser.isValidDate(" "));
    }

    @Test
    public void isValidDateTest4() {
        assertEquals(false, Parser.isValidDate("2022-22-a2"));
    }

    @Test
    public void isValidDateTest5() {
        assertEquals(false, Parser.isValidDate("2022 22 22"));
    }

    @Test
    public void isValidDateTest6() {
        assertEquals(false, Parser.isValidDate("2022-1-22"));
    }

    @Test
    public void isValidDateTest7() {
        assertEquals(true, Parser.isValidDate("2022-01-22"));
    }
}
