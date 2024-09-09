package rotodo.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import rotodo.commands.AddCommand.TaskType;
import rotodo.exception.InvalidInputException;
import rotodo.tasklist.TaskList;

public class AddCommandTest {
    @Test
    public void initialise_givenTaskType_addCorrectTypedTask() {
        // Arrange
        TaskList tasklist = new TaskList();
        AddCommand addTodo;
        AddCommand addDeadline;
        AddCommand addEvent;
        String expected = "1.[T][ ] Todo task\n"
                + "2.[D][ ] Deadline task (by: 01/01/2001 0102)\n"
                + "3.[E][ ] Event task (from: 01/01/2001 0102 to: 02/02/2003 0203)";

        // Act
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
            // should not have exception here
        }

        // Assert
        String actual = tasklist.toString();
        assertEquals(expected, actual, "AddCommand added the task wrongly, or task details are wrong.");
    }

    @Test
    public void initialise_wrongDateTimeFormat_throwsInvalidInputException() {
        // Arrange
        String expected = "\u001B[31mRoTodo.Error: Invalid Input\n"
                + "  Whaaaatt? RoTodo has no idea what date that is\n"
                + "RoTodo needs valid date/time in the form:\n"
                + "  dd/MM/yyyy HHmm\u001B[0m\n"
                + "type 'help' to see guide";

        // Act
        InvalidInputException exception = assertThrows(InvalidInputException.class, () ->
            new AddCommand(TaskType.DEADLINE,
                new String[] { "dummy task", "some bad datetime" })
        );

        // Assert
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void initialise_insufficientInputParams_throwsIncompleteInputException() {
        // Arrange
        String expected = "\u001B[31mRoTodo.Error: Invalid Input\n"
                + "  RoTodo can't read your mind, otherwise "
                + "RoTodo's creator would be rich!\n"
                + "  RoTodo needs a task description, from and to date/time"
                + "\u001B[0m\n" + "type 'help' to see guide";

        // Act
        InvalidInputException exception = assertThrows(InvalidInputException.class, () ->
            new AddCommand(TaskType.EVENT,
                new String[] { "dummy task", "01/01/2001 0102" })
        );

        // Assert
        assertEquals(expected, exception.getMessage());
    }
}
