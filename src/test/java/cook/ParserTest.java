package cook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.ByeCommand;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ToDoCommand;
import commands.UnmarkCommand;

public class ParserTest {

    @Test
    public void command_bye_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new ByeCommand(), parser.readInput("bye"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_list_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new ListCommand(), parser.readInput("list"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_mark_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new MarkCommand(42), parser.readInput("mark 42"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_unmark_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new UnmarkCommand(42), parser.readInput("unmark 42"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_delete_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new DeleteCommand(42), parser.readInput("delete 42"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_todo_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new ToDoCommand("testing"), parser.readInput("todo testing"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_todoSpacedDesc_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new ToDoCommand("test ing"), parser.readInput("todo test ing"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_deadline_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new DeadlineCommand("testing", "2024-12-12 16:00"),
                    parser.readInput("deadline testing /by 2024-12-12 16:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_deadlineSpacedDesc_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new DeadlineCommand("test ing", "2024-12-12 16:00"),
                    parser.readInput("deadline test ing /by 2024-12-12 16:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_event_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new EventCommand("testing", "2024-12-12 16:00", "2024-12-12 18:00"),
                    parser.readInput("event testing /from 2024-12-12 16:00 /to 2024-12-12 18:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void command_eventSpacedDesc_parsedCorrectly() {
        Parser parser = new Parser();
        try {
            assertEquals(new EventCommand("test ing", "2024-12-12 16:00", "2024-12-12 18:00"),
                    parser.readInput("event test ing /from 2024-12-12 16:00 /to 2024-12-12 18:00"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
