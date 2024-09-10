package ahmad;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ahmad.exceptions.CommandInvalidException;

public class ParserTest {

    @Test
    public void markTest() {
        try {
            Parser.parse("mark 1");
        } catch (CommandInvalidException e) {
            fail("Mark fail");
        }
    }

    @Test
    public void unmarkTest() {
        try {
            Parser.parse("unmark 12");
        } catch (CommandInvalidException e) {
            fail("Unmark fail");
        }
    }

    @Test
    public void listTest() {
        try {
            Parser.parse("list");
        } catch (CommandInvalidException e) {
            fail("List fail");
        }
    }

    @Test
    public void byeTest() {
        try {
            Parser.parse("bye");
        } catch (CommandInvalidException e) {
            fail("Bye fail");
        }
    }

    @Test
    public void todoTest() {
        try {
            Parser.parse("todo test");
        } catch (CommandInvalidException e) {
            fail("Todo fail");
        }
    }

    @Test
    public void deadlineTest() {
        try {
            Parser.parse("deadline test /by tomorrow");
        } catch (CommandInvalidException e) {
            fail("Deadline fail");
        }
    }

    @Test
    public void eventTest() {
        try {
            Parser.parse("event test /from today /to tomorrow");
        } catch (CommandInvalidException e) {
            fail("event fail");
        }
    }

    @Test
    public void deleteTest() {
        try {
            Parser.parse("delete 12");
        } catch (CommandInvalidException e) {
            fail("Delete fail");
        }
    }

    @Test
    public void invalidCommandTest() {
        assertThrows(CommandInvalidException.class, () -> Parser.parse("invalid command"));
    }
}
