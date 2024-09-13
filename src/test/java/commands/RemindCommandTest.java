package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.TaskList;
import utils.Storage;
import utils.Ui;




public class RemindCommandTest {

    @Test
    public void execute_reminderWithDueTasks_displaysUpcomingTasks() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/testReminder.txt");

        // Add tasks with different due dates
        taskList.add(new Deadline("Submit project", "2029-12-01")); // Due in the future
        taskList.add(new Deadline("Finish homework", "2029-09-10")); // Due in the future
        taskList.add(new Deadline("Prepare for meeting", "2023-09-12")); // Due in the past

        // Reminder for tasks due within 1000000 days from now
        ReminderCommand reminderCommand = new ReminderCommand(1000000000);
        String resultMessage = reminderCommand.execute(taskList, ui, storage);

        // Expected output for tasks due in the next 1000000000 days
        String expectedMessage = "Here are the tasks due within the next 1000000000 days:\n"
                + "1. [D][ ] Submit project (by: Dec 01 2029)\n"
                + "2. [D][ ] Finish homework (by: Sep 10 2029)\n";

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    public void execute_reminderWithNoDueTasks_displaysNoTasks() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/testReminder.txt");

        // Add a task that is far in the future
        taskList.add(new Deadline("Submit project", "2023-12-01")); //Due in the past
        ReminderCommand reminderCommand = new ReminderCommand(3);
        String resultMessage = reminderCommand.execute(taskList, ui, storage);
        String expectedMessage = "No tasks are due within the next 3 days.";

        assertEquals(expectedMessage, resultMessage);
    }
}
