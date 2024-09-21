package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chatterboxerrors.ChatterBoxDeadlineError;
import chatterboxerrors.ChatterBoxDeleteError;
import chatterboxerrors.ChatterBoxError;
import chatterboxerrors.ChatterBoxEventError;
import chatterboxerrors.ChatterBoxMarkError;
import chatterboxerrors.ChatterBoxToDoError;


public class ParserTest {
    @Test
    public void processInputCaseOneTest() throws ChatterBoxError {
        assertEquals("bye", Parser.processInput("bye")[0]);
        assertNull(Parser.processInput("bye")[1]);
        assertNull(Parser.processInput("bye")[2]);
        assertNull(Parser.processInput("bye")[3]);

        assertEquals(Parser.processInput("list")[0], "list");
        assertNull(Parser.processInput("list")[1]);
        assertNull(Parser.processInput("list")[2]);
        assertNull(Parser.processInput("list")[3]);
    }

    @Test
    public void processInputCaseOneWrongInputTest() {
        assertThrows(ChatterBoxError.class, () -> Parser.processInput("bye bye"));
        assertThrows(ChatterBoxError.class, () -> Parser.processInput("list 1"));
        assertThrows(ChatterBoxError.class, () -> Parser.processInput("list as"));
    }

    @Test
    public void processInputCaseTwoTest() throws ChatterBoxError {
        assertEquals("mark", Parser.processInput("mark 1")[0]);
        assertEquals("0", Parser.processInput("mark 1")[1]);
        assertNull(Parser.processInput("mark 1")[2]);
        assertNull(Parser.processInput("mark 1")[3]);

        assertEquals("unmark", Parser.processInput("unmark 1")[0]);
        assertEquals("0", Parser.processInput("unmark 1")[1]);
        assertNull(Parser.processInput("unmark 1")[2]);
        assertNull(Parser.processInput("unmark 1")[3]);

        assertEquals("delete", Parser.processInput("delete 1")[0]);
        assertEquals("0", Parser.processInput("delete 1")[1]);
        assertNull(Parser.processInput("delete 1")[2]);
        assertNull(Parser.processInput("delete 1")[3]);
    }

    @Test
    public void processInputCaseTwoWrongInputTest() {
        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("mark s");
        });
        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("mark as");
        });
        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("mark as 1");
        });
        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("mark 1 as");
        });

        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("unmark s");
        });
        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("unmark as");
        });
        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("unmark as 1");
        });
        assertThrows(ChatterBoxMarkError.class, () -> {
            Parser.processInput("unmark 1 as");
        });

        assertThrows(ChatterBoxDeleteError.class, () -> {
            Parser.processInput("delete s");
        });
        assertThrows(ChatterBoxDeleteError.class, () -> {
            Parser.processInput("delete as");
        });
        assertThrows(ChatterBoxDeleteError.class, () -> {
            Parser.processInput("delete as 1");
        });
        assertThrows(ChatterBoxDeleteError.class, () -> {
            Parser.processInput("delete 1 as");
        });
    }

    @Test
    public void processInputCaseThreeTest() throws ChatterBoxError {
        assertEquals("todo", Parser.processInput("todo assignment")[0]);
        assertEquals("assignment", Parser.processInput("todo assignment")[1]);
        assertNull(Parser.processInput("todo assignment")[2]);
        assertNull(Parser.processInput("todo assignment")[3]);

        assertEquals("todo", Parser.processInput("todo assignment 1")[0]);
        assertEquals("assignment 1", Parser.processInput("todo assignment 1")[1]);
        assertNull(Parser.processInput("todo assignment 1")[2]);
        assertNull(Parser.processInput("todo assignment 1")[3]);
    }
    @Test
    public void processInputCaseThreeWrongInputTest() {
        assertThrows(ChatterBoxToDoError.class, () -> {
            Parser.processInput("todo"); });
        assertThrows(ChatterBoxToDoError.class, () -> {
            Parser.processInput("todo "); });
    }

    @Test
    public void processInputCaseFourTest() throws ChatterBoxError {
        assertEquals("deadline", Parser.processInput("deadline assignment /by 01/12/2024")[0]);
        assertEquals("assignment", Parser.processInput("deadline assignment /by 01/12/2024")[1]);
        assertEquals("01/12/2024", Parser.processInput("deadline assignment /by 01/12/2024")[2]);
        assertNull(Parser.processInput("deadline assignment /by 01/12/2024")[3]);

        assertEquals("deadline", Parser.processInput("deadline assignment /by 01/12/2024 1600")[0]);
        assertEquals("assignment", Parser.processInput("deadline assignment /by 01/12/2024 1600")[1]);
        assertEquals("01/12/2024 1600", Parser.processInput("deadline assignment /by 01/12/2024 1600")[2]);
        assertNull(Parser.processInput("deadline assignment /by 01/12/2024 1600")[3]);

        assertEquals("deadline", Parser.processInput("deadline assignment 1 /by 01/12/2024 1600")[0]);
        assertEquals("assignment 1", Parser.processInput("deadline assignment 1 /by 01/12/2024 1600")[1]);
        assertEquals("01/12/2024 1600", Parser.processInput("deadline assignment 1 /by 01/12/2024 1600")[2]);
        assertNull(Parser.processInput("deadline assignment 1 /by 01/12/2024 1600")[3]);
    }

    @Test
    public void processInputCaseFourWrongInputTest() {
        assertThrows(ChatterBoxDeadlineError.class, () -> {
            Parser.processInput("deadline assignment/from 01/12/2024"); });
        assertThrows(ChatterBoxDeadlineError.class, () -> {
            Parser.processInput("deadline assignment 01/12/2024 1600"); });
        assertThrows(ChatterBoxDeadlineError.class, () -> {
            Parser.processInput("deadline assignment from 01/12/2024 1600"); });
    }

    @Test
    public void processInputCaseFiveTest() throws ChatterBoxError {
        assertEquals("event", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024")[0]);
        assertEquals("meeting", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024")[1]);
        assertEquals("01/12/2024", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024")[2]);
        assertEquals("02/12/2024", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024")[3]);

        assertEquals("event", Parser.processInput("event team meeting /from 01/12/2024 /to 02/12/2024")[0]);
        assertEquals("team meeting", Parser.processInput("event team meeting /from 01/12/2024 /to 02/12/2024")[1]);
        assertEquals("01/12/2024", Parser.processInput("event team meeting /from 01/12/2024 /to 02/12/2024")[2]);
        assertEquals("02/12/2024", Parser.processInput("event team meeting /from 01/12/2024 /to 02/12/2024")[3]);

        assertEquals("event", Parser.processInput("event meeting /from 01/12/2024 1600 /to 02/12/2024")[0]);
        assertEquals("meeting", Parser.processInput("event meeting /from 01/12/2024 1600 /to 02/12/2024")[1]);
        assertEquals("01/12/2024 1600", Parser.processInput("event meeting /from 01/12/2024 1600 /to 02/12/2024")[2]);
        assertEquals("02/12/2024", Parser.processInput("event meeting /from 01/12/2024 1600 /to 02/12/2024")[3]);

        assertEquals("event", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024 1600")[0]);
        assertEquals("meeting", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024 1600")[1]);
        assertEquals("01/12/2024", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024 1600")[2]);
        assertEquals("02/12/2024 1600", Parser.processInput("event meeting /from 01/12/2024 /to 02/12/2024 1600")[3]);
    }

    @Test
    public void processInputCaseFiveWrongInputTest() {
        assertThrows(ChatterBoxEventError.class, () -> {
            Parser.processInput("event assignment /from 01/12/2024");
        });
        assertThrows(ChatterBoxEventError.class, () -> {
            Parser.processInput("event assignment/from 01/12/2024");
        });
        assertThrows(ChatterBoxEventError.class, () -> {
            Parser.processInput("event assignment /from 01/12/2024/to 02/12/2024");
        });
        assertThrows(ChatterBoxEventError.class, () -> {
            Parser.processInput("event assignment /from 01/12/2024 02/12/2024");
        });
    }

    @Test
    public void oneTimeExtractorTest() throws ChatterBoxDeadlineError {
        assertEquals("assignment", Parser.oneTimeExtractor("assignment /by 01/12/2024")[0]);
        assertEquals("01/12/2024", Parser.oneTimeExtractor("assignment /by 01/12/2024")[1]);

        assertEquals("assignment 1", Parser.oneTimeExtractor("assignment 1 /by 01/12/2024")[0]);
        assertEquals("01/12/2024", Parser.oneTimeExtractor("assignment 1 /by 01/12/2024")[1]);
    }

    @Test
    public void oneTimeExtractorWrongInputTest() {
        assertThrows(ChatterBoxDeadlineError.class, () -> {
            Parser.oneTimeExtractor("assignment/by 01/12/2024");
        });
        assertThrows(ChatterBoxDeadlineError.class, () -> {
            Parser.oneTimeExtractor("assignment /by01/12/2024");
        });
        assertThrows(ChatterBoxDeadlineError.class, () -> {
            Parser.oneTimeExtractor("assignment 01/12/2024");
        });
    }

    @Test
    public void twoTimeExtractorTest() throws ChatterBoxEventError {
        assertEquals("assignment", Parser.twoTimeExtractor("assignment /from 01/12/2024 /to 02/12/2024")[0]);
        assertEquals("01/12/2024", Parser.twoTimeExtractor("assignment /from 01/12/2024 /to 02/12/2024")[1]);
        assertEquals("02/12/2024", Parser.twoTimeExtractor("assignment /from 01/12/2024 /to 02/12/2024")[2]);

        assertEquals("assignment 1", Parser.twoTimeExtractor("assignment 1 /from 01/12/2024 /to 02/12/2024")[0]);
        assertEquals("01/12/2024", Parser.twoTimeExtractor("assignment 1 /from 01/12/2024 /to 02/12/2024")[1]);
        assertEquals("02/12/2024", Parser.twoTimeExtractor("assignment 1 /from 01/12/2024 /to 02/12/2024")[2]);
    }

    @Test
    public void twoTimeExtractorWrongInputTest() {
        assertThrows(ChatterBoxEventError.class, () -> {
            Parser.twoTimeExtractor("assignment/from 01/12/2024 /to 02/12/2024");
        });
        assertThrows(ChatterBoxEventError.class, () -> {
            Parser.twoTimeExtractor("assignment /from 01/12/2024");
        });
        assertThrows(ChatterBoxEventError.class, () -> {
            Parser.twoTimeExtractor("assignment 01/12/2024");
        });
    }
}
