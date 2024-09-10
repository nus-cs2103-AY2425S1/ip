package kietwoforone.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindDateCommandTest {

    private String date = "2024-06-21";

    @Test
    public void findDateCommandTest() {
        assertEquals("Date found", new FindDateCommand(date).toString());
    }
}
