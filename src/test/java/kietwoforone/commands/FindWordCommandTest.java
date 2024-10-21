package kietwoforone.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindWordCommandTest {

    private String keyword = "word";

    @Test
    public void findWordCommandTest() {
        assertEquals("Matching tasks listed", new FindWordCommand(keyword).toString());
    }

}
