package purrfessordipsy.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import purrfessordipsy.exception.InvalidDateException;
import purrfessordipsy.task.Task;
import purrfessordipsy.tasklist.TaskList;
import purrfessordipsy.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListCommandTest {

    private TaskList taskList;
    private TestUi testUi;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
        testUi = new TestUi();
    }

    private static class TestTask extends Task {
        private final LocalDate relevantDate;

        public TestTask(String description, boolean isDone, LocalDate relevantDate) {
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

    private static class TestUi extends Ui {
        private final List<Task> displayedTasks = new ArrayList<>();

        @Override
        public void printListOfTasks(ArrayList<Task> tasks) {
            displayedTasks.addAll(tasks);  // Simulate displaying tasks
        }

        public List<Task> getDisplayedTasks() {
            return displayedTasks;
        }
    }

    @Test
    public void testExecute_ListAllTasks() throws InvalidDateException {
        String userInput = "list";
        Task task1 = new TestTask("Placeholder1", false, LocalDate.of(2024, 1, 1));
        Task task2 = new TestTask("Placeholder2", false, LocalDate.of(2024, 1, 2));
        taskList.addTask(task1);
        taskList.addTask(task2);

        ListCommand command = new ListCommand(userInput, taskList, testUi);
        command.execute();

        assertEquals(2, testUi.getDisplayedTasks().size());
        assertEquals(task1, testUi.getDisplayedTasks().get(0));
        assertEquals(task2, testUi.getDisplayedTasks().get(1));
    }

    @Test
    public void testExecute_ListTasksByDate() throws InvalidDateException {
        String userInput = "list 2024-01-01";
        Task task1 = new TestTask("Placeholder1", false, LocalDate.of(2024, 1, 1));
        Task task2 = new TestTask("Placeholder2", false, LocalDate.of(2024, 1, 2));
        taskList.addTask(task1);
        taskList.addTask(task2);

        ListCommand command = new ListCommand(userInput, taskList, testUi);
        command.execute();

        assertEquals(1, testUi.getDisplayedTasks().size());
        assertEquals(task1, testUi.getDisplayedTasks().get(0));
    }
}
