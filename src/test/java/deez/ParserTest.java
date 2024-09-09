package deez;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Properties;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;

public class ParserTest {

    @Test
    public void testParseBye() {
        // Arrange
        String input = "bye";
        Pair<Command, Properties> result = Parser.parse(input);

        // Assert
        assertEquals(Command.EXIT, result.getKey());
    }

    @Test
    public void testParseList() {
        // Arrange
        String input = "list";
        Pair<Command, Properties> result = Parser.parse(input);

        // Assert
        assertEquals(Command.LIST, result.getKey());
    }

    @Test
    public void testParseMark() {
        // Arrange
        String input = "mark 3";
        Pair<Command, Properties> result = Parser.parse(input);

        // Assert
        assertEquals(Command.MARK, result.getKey());
        assertEquals("3", result.getValue().getProperty("index"));
    }

    @Test
    public void testParseEvent() {
        // Arrange
        String input = "event project meeting /from 2019-10-15 1800 /to 2019-10-16 1900 #work #important";
        Pair<Command, Properties> result = Parser.parse(input);

        // Assert
        assertEquals(Command.EVENT, result.getKey());
        Properties props = (Properties) result.getValue();
        assertEquals("project meeting", props.getProperty("name"));
        assertEquals("2019-10-15 1800", props.getProperty("from"));
        assertEquals("2019-10-16 1900", props.getProperty("to"));
        assertEquals("work,important", props.getProperty("tags"));
    }

    @Test
    public void testParseDeadline() {
        // Arrange
        String input = "deadline return book /by 2019-10-15 1800 #library";
        Pair<Command, Properties> result = Parser.parse(input);

        // Assert
        assertEquals(Command.DEADLINE, result.getKey());
        Properties props = (Properties) result.getValue();
        assertEquals("return book", props.getProperty("name"));
        assertEquals("2019-10-15 1800", props.getProperty("by"));
        assertEquals("library", props.getProperty("tags"));
    }

    @Test
    void testEmptyInput() {
        // Act
        Pair<Command, Properties> result = Parser.parse("");

        // Assert
        assertEquals(Command.UNKNOWN, result.getKey());
        assertNotNull(result.getValue()); // Empty properties for unknown command
    }

    @Test
    void testBlankPropStringForTodoCommand() {
        // Act
        Pair<Command, Properties> result = Parser.parse("todo ");

        // Assert
        assertEquals(Command.TODO, result.getKey());
        assertNotNull(result.getValue()); // Null properties for TODO command with blank prop string
    }

    @Test
    void testInvalidCommand() {
        // Act
        Pair<Command, Properties> result = Parser.parse("abdc ");

        // Assert
        assertEquals(Command.UNKNOWN, result.getKey());
    }

    @Test
    void testFindCommand() {
        // Act
        Pair<Command, Properties> result = Parser.parse("find book");

        // Assert
        assertEquals(Command.FIND, result.getKey());
        assertEquals("book", result.getValue().getProperty("keyword"));
    }
}
