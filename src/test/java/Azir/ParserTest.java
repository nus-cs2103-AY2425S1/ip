package Azir;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void listCommand() throws AzirException {
        assertArrayEquals(new String[] {"list"}, Parser.parse("list", 3));
    }

    @Test
    public void byeCommand() throws AzirException {
        assertArrayEquals(new String[] {"bye"}, Parser.parse("bye", 3));
    }

    @Test
    public void markCommand_valid() throws AzirException {
        assertArrayEquals(new String[] {"mark", "2"}, Parser.parse("mark 2", 3));
    }

    @Test
    public void markCommand_invalidValue() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("mark 5", 3);
        });
        assertEquals("Invalid value", thrown.getMessage());
    }

    @Test
    public void markCommand_emptyNumber() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("mark", 3);
        });
        assertEquals("Input the task number you would like to mark.", thrown.getMessage());
    }

    @Test
    public void unmarkCommand_valid() throws AzirException {
        assertArrayEquals(new String[] {"unmark", "2"}, Parser.parse("unmark 2", 3));
    }

    @Test
    public void unmarkCommand_invalidValue() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("unmark 5", 3);
        });
        assertEquals("Invalid value", thrown.getMessage());
    }

    @Test
    public void unmarkCommand_emptyNumber() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("unmark", 3);
        });
        assertEquals("Input the task number you would like to unmark.", thrown.getMessage());
    }

    @Test
    public void deleteCommand_valid() throws AzirException {
        assertArrayEquals(new String[] {"delete", "2"}, Parser.parse("delete 2", 3));
    }

    @Test
    public void deleteCommand_invalidValue() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("delete 5", 3);
        });
        assertEquals("Invalid value", thrown.getMessage());
    }

    @Test
    public void deleteCommand_emptyNumber() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("delete", 3);
        });
        assertEquals("Input the task number you would like to delete.", thrown.getMessage());
    }

    @Test
    public void todoCommand_valid() throws AzirException{
        assertArrayEquals(new String[] {"todo", "return book"}, Parser.parse("todo return book", 3));
    }

    @Test
    public void todoCommand_noDescription() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("todo", 3);
        });
        assertEquals("todo cannot have an empty description. " +
                "Format: todo [description]", thrown.getMessage());
    }

    @Test
    public void deadlineCommand_valid() throws AzirException{
        assertArrayEquals(new String[] {"deadline", "return book", "2023-10-15"},
                Parser.parse("deadline return book /by 2023-10-15", 3));
    }

    @Test
    public void deadlineCommand_noDescription() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("deadline /by 2023-10-15", 3);
        });
        assertEquals("deadline needs a description. " +
                "Format: deadline [description] /by [date]", thrown.getMessage());
    }

    @Test
    public void deadlineCommand_noBy() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("deadline return book", 3);
        });
        assertEquals("deadline needs a /by date. " +
                "Format: deadline [description] /by [date]", thrown.getMessage());
    }

    @Test
    public void deadlineCommand_noDate() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("deadline return book /by", 3);
        });
        assertEquals("You need a deadline day. " +
                "Format: deadline [description] /by [date]", thrown.getMessage());
    }

    @Test
    public void eventCommand_valid() throws AzirException{
        assertArrayEquals(new String[] {"event", "project meeting", "Mon 2pm", "4pm"},
                Parser.parse("event project meeting /from Mon 2pm /to 4pm", 3));
    }

    @Test
    public void eventCommand_noDescription() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("event /from Mon 2pm /to 4pm", 3);
        });
        assertEquals("event needs a description. " +
                "Format: event [description] /from [date] /to [date]", thrown.getMessage());
    }

    @Test
    public void eventCommand_noFrom() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("event project meeting Mon 2pm /to 4pm", 3);
        });
        assertEquals("event needs a /from. " +
                "Format: event [description] /from [date] /to [date]", thrown.getMessage());
    }

    @Test
    public void eventCommand_noBy() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("event project meeting /from Mon 2pm 4pm", 3);
        });
        assertEquals("event needs a /to. " +
                "Format: event [description] /from [date] /to [date]", thrown.getMessage());
    }

    @Test
    public void eventCommand_noFromDate() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("event project meeting /from /to 4pm", 3);
        });
        assertEquals("event needs a from date. " +
                "Format: event [description] /from [date] /to [date]", thrown.getMessage());
    }

    @Test
    public void eventCommand_noByDate() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("event project meeting /from Mon 2pm /to", 3);
        });
        assertEquals("You need an ending date. " +
                "Format: event [description] /from [date] /to [date]", thrown.getMessage());
    }

    @Test
    public void randomCommand() throws AzirException {
        AzirException thrown = assertThrows(AzirException.class, () -> {
            Parser.parse("blah", 3);
        });
        assertEquals("Azir does not take in this input", thrown.getMessage());
    }
}

