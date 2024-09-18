package victor.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import victor.messages.ReturnMessage;
import victor.tasklist.TaskList;
import victor.tasks.ToDo;

public class UnmarkCommandTest {
    private static final Path DATA_PATH = Paths.get("data", "data.txt");
    @Test
    public void unmarkCommand_normalTest_unmarkedCorrectResponse() {
        UnmarkCommand comm = new UnmarkCommand(new String[] {"unmark", "1"});
        TaskList taskList = new TaskList(DATA_PATH);
        comm.setData(taskList);
        ToDo todo = new ToDo("test");
        taskList.addTask(todo);
        ReturnMessage returnMessage = comm.execute();
        assertEquals(
                returnMessage.getMessagesAsString(),
                "  ~  Oops, I guess you didn't finish the task! I marked this task as undone:"
                        + "\n" + "  ~  " + todo);
    }

    @Test
    public void unmarkCommand_nonexistentNumber_unmarkErrorMessage() {
        UnmarkCommand comm = new UnmarkCommand(new String[] {"unmark", "1"});
        TaskList taskList = new TaskList(DATA_PATH);
        comm.setData(taskList);
        ReturnMessage returnMessage = comm.execute();
        assertEquals(
                returnMessage.getMessagesAsString(),
                "  ~  Oh dear, I don't think there's a task with that number :(");
    }

    @Test
    public void unmarkCommand_noNumber_unmarkErrorMessage() {
        UnmarkCommand comm = new UnmarkCommand(new String[] {"unmark"});
        TaskList taskList = new TaskList(DATA_PATH);
        comm.setData(taskList);
        ReturnMessage returnMessage = comm.execute();
        assertEquals(
                returnMessage.getMessagesAsString(),
                "  ~  Oh dear, I don't think there's a task with that number :(");
    }

    @Test
    public void unmarkCommand_notANumber_unmarkErrorMessage() {
        UnmarkCommand comm = new UnmarkCommand(new String[] {"unmark", "notANumber"});
        TaskList taskList = new TaskList(DATA_PATH);
        comm.setData(taskList);
        ReturnMessage returnMessage = comm.execute();
        assertEquals(
                returnMessage.getMessagesAsString(),
                "  ~  Oh dear, I don't think there's a task with that number :(");
    }
}
