package astra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getIndex_validIndex_success() {
        try {
            assertEquals(1, Parser.getIndex("done 1"));
        } catch (AstraException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getIndex_invalidIndex_exceptionThrown() {
        try {
            Parser.getIndex("done a");
        } catch (AstraException e) {
            assertEquals("Invalid index.", e.getMessage());
        }
    }

    @Test
    public void getArgs_multipleArgs_success() {
        assertEquals(2, Parser.getArgs("find /t hello /d world").size());
    }

    @Test
    public void getArgs_noArgs_success() {
        assertEquals(0, Parser.getArgs("list").size());
    }
}
