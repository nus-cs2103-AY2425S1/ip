package duck.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


/**
 * Tests the CommandWord class.
 */
public class CommandWordTest {

    /**
     * Tests that the command words are correctly represented.
     */
    @Test
    public void testCommandWords() {
        assertEquals("BYE", CommandWord.BYE.toString(), "The 'bye' command word should match.");
        assertEquals("DEADLINE", CommandWord.DEADLINE.toString(), "The 'deadline' command word should match.");
        assertEquals("DELETE", CommandWord.DELETE.toString(), "The 'delete' command word should match.");
        assertEquals("EVENT", CommandWord.EVENT.toString(), "The 'event' command word should match.");
        assertEquals("FIND", CommandWord.FIND.toString(), "The 'find' command word should match.");
        assertEquals("HELP", CommandWord.HELP.toString(), "The 'help' command word should match.");
        assertEquals("LIST", CommandWord.LIST.toString(), "The 'list' command word should match.");
        assertEquals("MARK", CommandWord.MARK.toString(), "The 'mark' command word should match.");
        assertEquals("ON", CommandWord.ON.toString(), "The 'on' command word should match.");
        assertEquals("SORT", CommandWord.SORT.toString(), "The 'sort' command word should match.");
        assertEquals("TODO", CommandWord.TODO.toString(), "The 'todo' command word should match.");
        assertEquals("UNMARK", CommandWord.UNMARK.toString(), "The 'unmark' command word should match.");
    }

    /**
     * Tests that an exception is thrown when an invalid command word is used.
     */
    @Test
    public void testCommandWords_invalidWord_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            CommandWord commandWord = CommandWord.valueOf("INVALID");
        });
    }
}
