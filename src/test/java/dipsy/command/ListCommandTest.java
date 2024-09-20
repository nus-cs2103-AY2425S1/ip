package dipsy.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dipsy.exception.InvalidCommandException;
import dipsy.exception.InvalidDateException;
import dipsy.task.Task;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;

public class ListCommandTest {

    private TaskList taskList;
    private UiStub uiStub;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
        uiStub = new UiStub();
    }

    private static class TaskStub extends Task {
        private final LocalDate relevantDate;

        public TaskStub(String description, boolean isDone, LocalDate relevantDate) {
            super(description, isDone);
            this.relevantDate = relevantDate;
        }

        @Override
        public LocalDate getRelevantDate() {
            return relevantDate;
        }

        @Override
        public String toString() {
            return "TestTask: " + relevantDate;
        }
    }

    private static class UiStub extends Ui {
        private final List<Task> displayedTasks = new ArrayList<>();

        @Override
        public String getTasksMessage(ArrayList<Task> tasks) {
            displayedTasks.addAll(tasks); // Simulate displaying tasks
            return ""; // Return an empty string since we are not testing the actual output
        }

        public List<Task> getDisplayedTasks() {
            return displayedTasks;
        }
    }

    @Test
    public void execute_listAllTasks_displaysAllTasks()
            throws InvalidDateException, InvalidCommandException {
        String userInput = "list";
        Task task1 = new TaskStub("Placeholder1", false, LocalDate.of(2024, 1, 1));
        Task task2 = new TaskStub("Placeholder2", false, LocalDate.of(2024, 1, 2));
        taskList.addTask(task1);
        taskList.addTask(task2);

        ListCommand command = new ListCommand(userInput, taskList, uiStub);
        command.execute();

        assertEquals(2, uiStub.getDisplayedTasks().size());
        assertEquals(task1, uiStub.getDisplayedTasks().get(0));
        assertEquals(task2, uiStub.getDisplayedTasks().get(1));
    }

    @Test
    public void execute_listTasksByDate_displaysTasksOnSpecificDate()
            throws InvalidDateException, InvalidCommandException {
        String userInput = "list 2024-01-01";
        Task task1 = new TaskStub("Placeholder1", false, LocalDate.of(2024, 1, 1));
        Task task2 = new TaskStub("Placeholder2", false, LocalDate.of(2024, 1, 2));
        taskList.addTask(task1);
        taskList.addTask(task2);

        ListCommand command = new ListCommand(userInput, taskList, uiStub);
        command.execute();

        assertEquals(1, uiStub.getDisplayedTasks().size());
        assertEquals(task1, uiStub.getDisplayedTasks().get(0));
    }
}
