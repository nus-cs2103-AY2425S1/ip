package astra.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import astra.task.Task.TaskType;

public class AddCommandTest {
    @Test
    public void constructor_todoTask_success() {
        TaskType type = TaskType.TODO;
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "test");
        try {
            AddCommand command = new AddCommand(type, args);
            assertEquals(command.toString(), "ADD T | 0 | test");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void constructor_todoTask_missingDescription() {
        TaskType type = TaskType.TODO;
        HashMap<String, String> args = new HashMap<>();
        try {
            AddCommand command = new AddCommand(type, args);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Task description cannot be empty.");
        }
    }

    @Test
    public void constructor_deadlineTask_success() {
        TaskType type = TaskType.DEADLINE;
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "test");
        args.put("by", "2021-08-23");
        try {
            AddCommand command = new AddCommand(type, args);
            assertEquals(command.toString(), "ADD D | 0 | test | 2021-08-23");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void constructor_deadlineTask_missingDates() {
        TaskType type = TaskType.DEADLINE;
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "test");
        try {
            AddCommand command = new AddCommand(type, args);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Please supply dates.");
        }
    }

    @Test
    public void constructor_eventTask_success() {
        TaskType type = TaskType.EVENT;
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "test");
        args.put("from", "2021-08-23");
        args.put("to", "2021-08-25");
        try {
            AddCommand command = new AddCommand(type, args);
            assertEquals(command.toString(), "ADD E | 0 | test | 2021-08-23 | 2021-08-25");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void constructor_eventTask_invalidDates() {
        TaskType type = TaskType.EVENT;
        HashMap<String, String> args = new HashMap<>();
        args.put("main", "test");
        args.put("from", "2021-08-23");
        args.put("to", "what");
        try {
            AddCommand command = new AddCommand(type, args);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Please supply valid dates (yyyy-MM-dd).");
        }
    }
}
