package cook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private HashMap<String, Integer> init() {
        HashMap<String, Integer> validCommandArgs = new HashMap<>();

        validCommandArgs.put("bye", 1);
        validCommandArgs.put("list", 1);
        validCommandArgs.put("mark", 2);
        validCommandArgs.put("unmark", 2);
        validCommandArgs.put("delete", 2);
        validCommandArgs.put("todo", 2);
        validCommandArgs.put("deadline", 4);
        validCommandArgs.put("event", 6);

        return validCommandArgs;
    }

    @Test
    public void command_bye_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "bye");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("bye"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_list_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "list");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_mark_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "mark");
        expectedCommandArgs.put("mark", "42");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("mark 42"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_unmark_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "unmark");
        expectedCommandArgs.put("unmark", "42");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("unmark 42"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_delete_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "delete");
        expectedCommandArgs.put("delete", "42");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("delete 42"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_todo_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "todo");
        expectedCommandArgs.put("todo", "testing");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("todo testing"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_todoSpacedDesc_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "todo");
        expectedCommandArgs.put("todo", "test ing");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("todo test ing"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_deadline_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "deadline");
        expectedCommandArgs.put("deadline", "testing");
        expectedCommandArgs.put("/by", "2024-12-12 16:00");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("deadline testing /by 2024-12-12 16:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_deadlineSpacedDesc_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "deadline");
        expectedCommandArgs.put("deadline", "test ing");
        expectedCommandArgs.put("/by", "2024-12-12 16:00");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs, parser.readInput("deadline test ing /by 2024-12-12 16:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_event_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "event");
        expectedCommandArgs.put("event", "testing");
        expectedCommandArgs.put("/from", "2024-12-12 16:00");
        expectedCommandArgs.put("/to", "2024-12-12 18:00");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs,
                    parser.readInput("event testing /from 2024-12-12 16:00 /to 2024-12-12 18:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_eventSpacedDesc_parsedCorrectly() {
        HashMap<String, Integer> validCommandArgs = this.init();

        HashMap<String, String> expectedCommandArgs = new HashMap<>();
        expectedCommandArgs.put("command", "event");
        expectedCommandArgs.put("event", "test ing");
        expectedCommandArgs.put("/from", "2024-12-12 16:00");
        expectedCommandArgs.put("/to", "2024-12-12 18:00");

        Parser parser = new Parser(validCommandArgs);
        try {
            assertEquals(expectedCommandArgs,
                    parser.readInput("event test ing /from 2024-12-12 16:00 /to 2024-12-12 18:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
