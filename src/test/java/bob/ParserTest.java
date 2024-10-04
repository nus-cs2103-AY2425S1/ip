package bob;

import bob.command.*;
import bob.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void tokenize_success() {
        // Tokenizing an empty string returns {"CMD": ""}
        assertEquals(Map.of("CMD", ""), Parser.tokenize(""));

        // Tokenizing a command without arguments returns {"CMD": <command>}
        assertEquals(Map.of("CMD", "list"), Parser.tokenize("list"));

        // Tokenizing a command with unnamed argument returns {"CMD": <cmd>, "", <arg>}
        assertEquals(
                Map.of("CMD", "mark", "", "2"),
                Parser.tokenize("mark 2"));
        assertEquals(
                Map.of("CMD", "todo", "", "A todo!"),
                Parser.tokenize("todo A todo!"));

        // Tokenizing a command with unnamed and named arguments returns {"CMD": <cmd>, "": <arg>, <name>: <arg>, ...}
        assertEquals(
                Map.of("CMD", "deadline", "", "A deadline!", "by", "tmr"),
                Parser.tokenize("deadline A deadline! /by tmr"));
        assertEquals(
                Map.of("CMD", "event",
                        "", "An event :O",
                        "from", "now",
                        "to", "tomorrow"),
                Parser.tokenize("event An event :O /from now /to tomorrow"));

        // Tokenizing a command with unnamed arguments only returns {"CMD": <cmd>, <name>: <arg>, ...}
        assertEquals(
                Map.of("CMD", "blah",  "dummy", "dummy value"),
                Parser.tokenize("blah /dummy dummy value"));
        assertEquals(
                Map.of("CMD", "please",  "do", "this", "and", "that"),
                Parser.tokenize("please /do this /and that"));

        // Tokenizing a named argument without value returns {..., <name>: ""}
        assertEquals(
                Map.of("CMD", "test",  "dummy", ""),
                Parser.tokenize("test /dummy"));
        assertEquals(
                Map.of("CMD", "test",  "dummy", "", "another", ""),
                Parser.tokenize("test /dummy /another"));
    }

    @Test
    public void parse_validCommand_success() {
        Parser parser = new Parser();

        // deadline
        assertEquals(
                new DeadlineCommand(Map.of(
                        "CMD", "deadline",
                        "", "test description",
                        "by", "tmr")),
                parser.parse("deadline test description /by tmr"));

        // delete
        assertEquals(
                new DeleteCommand(Map.of(
                        "CMD", "delete",
                        "", "5")),
                parser.parse("delete 5"));

        // event
        assertEquals(
                new EventCommand(Map.of(
                        "CMD", "event",
                        "", "test description",
                        "from", "now",
                        "to", "tmr")),
                parser.parse("event test description /from now /to tmr"));

        // exit
        assertEquals(new ExitCommand(Map.of("CMD", "bye")), parser.parse("bye"));

        // find
        assertEquals(
                new FindCommand(Map.of("CMD", "find", "", "book")),
                parser.parse("find book"));

        // list
        assertEquals(new ListCommand(Map.of("CMD", "list")), parser.parse("list"));

        // mark
        assertEquals(
                new MarkCommand(Map.of(
                        "CMD", "mark",
                        "", "5")),
                parser.parse("mark 5"));

        // reset
        assertEquals(new ResetCommand(Map.of("CMD", "reset")), parser.parse("reset"));

        // todo
        assertEquals(
                new TodoCommand(Map.of(
                        "CMD", "todo",
                        "", "test description")),
                parser.parse("todo test description"));

        // unmark
        assertEquals(
                new UnmarkCommand(Map.of(
                        "CMD", "unmark",
                        "", "5")),
                parser.parse("unmark 5"));
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        Parser parser = new Parser();

        assertThrows(UnknownCommandException.class, () -> parser.parse("hahaha"));
    }
}
