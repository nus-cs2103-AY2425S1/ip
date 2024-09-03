package bob.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bob.data.TaskList;
import bob.tasks.DeadlineTask;

public class OnTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TaskList taskList;
    private On onCommand;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));

        // Initialize the task list and the List command
        taskList = new TaskList();
        LocalDate date = LocalDate.of(2024, 9, 15);
        onCommand = new On(date);
    }

    @Test
    public void onTask_tasksOnDate_success() {
        LocalDateTime date = LocalDateTime.of(2024, 9, 15, 16, 30);
        taskList.add(new DeadlineTask("Task 1", date));
        onCommand.execute(taskList, null, null);
        String expectedOutput = "Here are the tasks on 15/09/2024:\n"
                + "1. [D][ ] Task 1 (by: 15 September 2024 4:30pm)";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    public void onTask_noTasksOnDate_success() {
        onCommand.execute(taskList, null, null);
        String expectedOutput = "There are no tasks on 15/09/2024.";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
}
