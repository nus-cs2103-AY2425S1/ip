package gravitas.parser;

import gravitas.task.Task;
import gravitas.task.Todo;
import gravitas.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseMark_markTask_returnMarkTask() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);

            Parser parse = new Parser();
            parse.parseMark(tasklist, "mark 1");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseUnmark_unmarkTask_returnUnmarkTask() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);

            Parser parse = new Parser();
            parse.parseMark(tasklist, "mark 1");
            parse.parseUnmark(tasklist, "unmark 1");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseMark_outOfBound_exceptionThrown() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);

            Parser parse = new Parser();
            parse.parseMark(tasklist, "mark 5");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "The task that you wish to mark is invalid! please try again!");
        }
    }

    @Test
    public void parseUnmark_outOfBound_exceptionThrown() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            Todo todoTask = new Todo("Read Book");
            taskArrayList.add(todoTask);
            TaskList tasklist = new TaskList(taskArrayList);

            Parser parse = new Parser();
            parse.parseMark(tasklist, "mark 1");
            parse.parseUnmark(tasklist, "unmark 5");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "The task that you wish to mark is invalid! please try again!");
        }
    }

    @Test
    public void parseTask_createTodoTask_returnTodoTask() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            TaskList tasklist = new TaskList(taskArrayList);

            Parser parse = new Parser();
            parse.parseTask(tasklist, "todo borrow book");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDelete_deleteTask_returnUpdatedTaskList() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            TaskList tasklist = new TaskList(taskArrayList);

            Parser parse = new Parser();
            parse.parseTask(tasklist, "todo borrow book");
            parse.parseDelete(tasklist, "delete 1");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseDelete_invalidCommand_exceptionThrown() {
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            TaskList tasklist = new TaskList(taskArrayList);

            Parser parse = new Parser();
            parse.parseTask(tasklist, "todo borrow book");
            parse.parseDelete(tasklist, "delete");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "OOPS!!! The description of a delete cannot be empty.");
        }
    }
}
