package janet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void unknownInputTest() {
        JanetException e1 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand(new String[]{"blah"}, 3);});   // unknown command

        JanetException e2 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("".split(" "), 3);});   // empty command

        JanetException e3 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("     ".split(" "), 3);});  // empty command

        JanetException e4 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand(new String[]{"find"}, 3);});   // not found command

        assertEquals(
                e1.getMessage(),
                "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
        assertEquals(
                e2.getMessage(),
                "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
        assertEquals(
                e3.getMessage(),
                "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
        assertEquals(
                e4.getMessage(),
                "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
    }

    @Test
    public void invalidViewCommandTest() {
        JanetException e1 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand(new String[]{"view"}, 3);});   // no date provided

        // more than 1 date provided (string)
        JanetException e2 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("view something and something".split(" "), 3);});

        // more than 1 date provided (date)
        JanetException e3 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("view 2024-09-10 2024-09-11".split(" "), 3);});

        // 1 date provided but in wrong format
        JanetException e4 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("view 2024/09/10".split(" "), 3);});

        assertEquals(
                e1.getMessage(), "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
        assertEquals(
                e2.getMessage(), "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
        assertEquals(
                e3.getMessage(), "WHOOPS! I'm only a chatbot, so I don't know what that means..."
        );
        assertEquals(
                e4.getMessage(), "WHOOPS! Please ensure date is in yyyy-MM-dd format!"
        );
    }

    @Test
    public void taskNumberAbsentTest() {
        // no task number
        JanetException e1 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand(new String[]{"mark"}, 3);});

        // task number invalid (cannot be parsed into integer)
        JanetException e2 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("unmark something".split(" "), 3);});

        // more than 1 task number provided and invalid
        JanetException e3 = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("delete 3 and that".split(" "), 3);});

        assertEquals(
                e1.getMessage(), "WHOOPS! Ensure task number is present and valid!"
        );
        assertEquals(
                e2.getMessage(), "WHOOPS! Ensure task number is present and valid!"
        );
        assertEquals(
                e3.getMessage(), "WHOOPS! Ensure task number is present and valid!"
        );
    }

    @Test
    public void taskNumberOutOfBoundsTest() {
        int numOfTasksInList = 5;
        JanetException exception = assertThrows(JanetException.class,
                () -> {Parser.checkInaccurateCommand("mark 6".split(" "), numOfTasksInList);});

        assertEquals(
                exception.getMessage(), "WHOOPS! You don't have a task of this number!"
        );
    }
}
