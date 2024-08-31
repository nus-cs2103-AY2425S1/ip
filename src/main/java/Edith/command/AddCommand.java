package Edith.command;

import Edith.Ui;
import Edith.Storage;
import Edith.EdithException;
import Edith.task.Task;
import Edith.task.ToDo;
import Edith.task.Deadline;
import Edith.task.Event;
import Edith.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String instruction;

    public AddCommand(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        Task task = null;

        if (instruction.startsWith("todo ")) {
            String taskString = instruction.substring(5).trim();
            if (taskString.isEmpty()) {
                throw new EdithException("Invalid Edith.task as no description for this todo was provided.");
            }
            task = new ToDo(taskString);
        } else if (instruction.startsWith("deadline ")) {
            String[] parts = instruction.substring(9).split(" /by ");
            if (parts.length != 2) {
                throw new EdithException("Deadlines must have both a description and a due date.");
            }
            String taskString = parts[0].trim();
            String dueDate = parts[1].trim();
            task = new Deadline(taskString, dueDate);
        } else if (instruction.startsWith("event ")) {
            String[] parts = instruction.substring(6).split(" /from | /to ");
            if (parts.length != 3) {
                throw new EdithException("Edith.task.Event must have a description, start time, and end time.");
            }
            String taskString = parts[0].trim();
            String startTime = parts[1].trim();
            String endTime = parts[2].trim();
            task = new Event(taskString, startTime, endTime);
        } else {
            throw new EdithException("Invalid Edith.command for adding tasks.");
        }

        try {
            tasks.addTask(task);
            ui.showIndentedMessage("Got it. I've added this Edith.task:");
            ui.showIndentedMessage(task.toString());
            ui.showIndentedMessage("There are now " + tasks.getNumOfTasks() + " tasks in your list.");
            ui.showLineBreak();
            storage.save(tasks.getListOfTasks());
        } catch (DateTimeParseException e) {
            throw new EdithException(ui.invalidDateTimeError(), 1);
        } catch (IOException e) {
            ui.showErrorMessage("An error occurred while saving updated Edith.task list.");
        }
    }
}
