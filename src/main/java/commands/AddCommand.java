package commands;

import exceptions.InputException;
import storage.Storage;
import storage.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddCommand implements Command {
    private final String taskType;
    private final String taskDescription;

    public AddCommand(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InputException {
        Task task = switch (taskType) {
            case "todo" -> new ToDo("").createTask(taskDescription);
            case "deadline" -> new Deadline("", LocalDateTime.now()).createTask(taskDescription);
            case "event" -> new Event("", LocalDateTime.now(), LocalDateTime.now()).createTask(taskDescription);
            default -> throw new InputException("Invalid task type.");
        };

        tasks.add(task);
        storage.saveTaskList(tasks.getTasks());

        ui.printLine();
        ui.show("Got it. I've added this task:");
        ui.show(task.toString());
        ui.show("Now you have " + tasks.size() + " tasks in the list.");
        ui.printLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
