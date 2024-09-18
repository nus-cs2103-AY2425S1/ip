package rotodo.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rotodo.commands.AddCommand.TaskType;
import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

public class DeleteCommandTest {
    @Test
    public void delete_givenTaskIndex_deletesTaskFromList() {
        // Arrange
        TaskList tasklist = new TaskList();
        Gui gui = new Gui();
        Storage storage = new Storage("./.data/rotodo.txt");
        AddCommand addTodo;
        AddCommand addDeadline;
        AddCommand addEvent;
        try {
            addTodo = new AddCommand(TaskType.TODO,
                new String[] {
                    "Todo task"
                });
            addDeadline = new AddCommand(TaskType.DEADLINE,
                new String[] {
                    "Deadline task",
                    "01/01/2001 0102"
                });
            addEvent = new AddCommand(TaskType.EVENT,
                new String[] {
                    "Event task",
                    "01/01/2001 0102",
                    "02/02/2003 0203"
                });
            addTodo.execute(tasklist, null, null);
            addDeadline.execute(tasklist, null, null);
            addEvent.execute(tasklist, null, null);
        } catch (Exception e) {
            assertEquals(e, null, "Instantiating and executing AddCommand should not have exceptions.\n"
                    + "(Ideal params were used)");
        }
        DeleteCommand deleteTask;
        String expected = "1.[D][ ] Deadline task (by: 01/01/2001 0102)\n"
                + "2.[E][ ] Event task (from: 01/01/2001 0102 to: 02/02/2003 0203)";


        // Act
        deleteTask = new DeleteCommand(0);
        deleteTask.execute(tasklist, gui, storage);

        // Assert
        String actual = tasklist.toString();
        assertEquals(expected, actual, "DeleteCommand deleted the task wrongly.");
    }
}
