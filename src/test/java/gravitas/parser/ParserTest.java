package gravitas.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import gravitas.command.Command;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import gravitas.task.Task;
import gravitas.task.Todo;
import gravitas.tasklist.TaskList;

public class ParserTest {

    @Test
    public void parseMark_markTask_returnMarkTask() {
        try {
            String input = "mark 1";
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);
            Command c = Parser.parseCommand(input);
            c.executeCommand(tasklist, null);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseUnmark_unmarkTask_returnUnmarkTask() {
        try {
            String mark = "mark 1";
            String unMark = "unmark 1";
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);
            Command c = Parser.parseCommand(mark);
            Command d = Parser.parseCommand(unMark);
            c.executeCommand(tasklist, null);
            d.executeCommand(tasklist, null);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseMark_outOfBound_exceptionThrown() {
        try {
            String mark = "mark 5";
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);
            Command c = Parser.parseCommand(mark);
            c.executeCommand(tasklist, null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "The task that you wish to mark is invalid! please try again!");
        }
    }

    @Test
    public void parseUnmark_outOfBound_exceptionThrown() {
        try {
            String unMark = "unmark 5";
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);
            Command c = Parser.parseCommand(unMark);
            c.executeCommand(tasklist, null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "The task that you wish to mark is invalid! please try again!");
        }
    }

    @Test
    public void parseTask_createTodoTask_returnTodoTask() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            TaskList tasklist = new TaskList(taskArrayList);
            String input = "todo borrow book";
            Command c = Parser.parseCommand(input);
            c.executeCommand(tasklist, null);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDelete_deleteTask_returnUpdatedTaskList() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            TaskList tasklist = new TaskList(taskArrayList);

            String input = "todo borrow book";
            String delete = "delete 1";
            Command c = Parser.parseCommand(input);
            Command d = Parser.parseCommand(delete);
            c.executeCommand(tasklist, null);
            d.executeCommand(tasklist, null);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDelete_invalidCommand_exceptionThrown() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            TaskList tasklist = new TaskList(taskArrayList);
            String delete = "delete";
            Command c = Parser.parseCommand(delete);
            c.executeCommand(tasklist, null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "OOPS!!! The description of a delete cannot be empty.");
        }
    }
}
