package ava.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    void values() {
        Command[] expected = {Command.LIST, Command.TODO, Command.EVENT,
                              Command.DEADLINE, Command.DELETE, Command.MARK,
                              Command.UNMARK, Command.FIND, Command.HELP};
        Command[] actual = Command.values();
        assertArrayEquals(expected, actual);
    }

    @Test
    void valueOf() {
        assertEquals(Command.LIST, Command.valueOf("LIST"));
        assertEquals(Command.TODO, Command.valueOf("TODO"));
        assertEquals(Command.EVENT, Command.valueOf("EVENT"));
        assertEquals(Command.DEADLINE, Command.valueOf("DEADLINE"));
        assertEquals(Command.DELETE, Command.valueOf("DELETE"));
        assertEquals(Command.MARK, Command.valueOf("MARK"));
        assertEquals(Command.UNMARK, Command.valueOf("UNMARK"));
        assertEquals(Command.FIND, Command.valueOf("FIND"));
        assertEquals(Command.HELP, Command.valueOf("HELP"));

    }
}
