package tars.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tars.tasks.Task;
import tars.tasks.TaskList;
import tars.tasks.ToDo;
import tars.tasks.Deadline;
import tars.tasks.Event;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
public class AddCommandTest {

    @Test
    public void addToDo() {

        List<Task> taskList = new ArrayList<>();

        TaskList tasks = new TaskList(taskList);
        String input = "/todo return book";

        ToDo expectedTaskCreated = new ToDo("return book");

        AddCommand addCommand = new AddCommand();
        String result = addCommand.execute(input, tasks);

        String expected = String.format("""
                Added yet another task
                   %s
                You now have %d tasks. Are you gonna do any of them?""",
                expectedTaskCreated, tasks.noOfTasks());

        assertEquals(expected, result);

    }

    @Test
    public void addDeadline() {

        List<Task> taskList = new ArrayList<>();

        TaskList tasks = new TaskList(taskList);
        String input = "/deadline return book /by 23-04-25";

        LocalDate date = LocalDate.parse("2025-04-23");
        Deadline expectedTaskCreated = new Deadline("return book", date);

        AddCommand addCommand = new AddCommand();
        String result = addCommand.execute(input, tasks);

        String expected = String.format("""
                Added yet another task
                   %s
                You now have %d tasks. Are you gonna do any of them?""",
                expectedTaskCreated, tasks.noOfTasks());

        assertEquals(expected, result);

    }

    @Test
    public void addEvent() {

        List<Task> taskList = new ArrayList<>();

        TaskList tasks = new TaskList(taskList);
        String input = "/event return book /from 23-04-25 /to 23-04-25";

        LocalDate date = LocalDate.parse("2025-04-23");
        Event expectedTaskCreated = new Event("return book", date, date);

        AddCommand addCommand = new AddCommand();
        String result = addCommand.execute(input, tasks);

        String expected = String.format("""
                Added yet another task
                   %s
                You now have %d tasks. Are you gonna do any of them?""",
                expectedTaskCreated, tasks.noOfTasks());

        assertEquals(expected, result);

    }

}
