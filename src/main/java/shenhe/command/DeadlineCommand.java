package shenhe.command;

import java.time.LocalDateTime;
import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;
import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.parser.DateParser;
import shenhe.task.Deadline;
public class DeadlineCommand extends Command {
    private String userInput;
    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 8) {
            throw new EmptyTaskDescriptionException();
        }

        // Split the input string into two parts using the first "/" character
        String[] parts = userInput.split("/", 2);

        // Extract the task description part (before the first "/")
        String task = parts[0].substring(8).trim();

        // Extract the deadline part (after the first "/")
        String by = parts[1].substring(2).trim();
        LocalDateTime dateTime = DateParser.parse(by);
        tasks.addTask(new Deadline(task, false, dateTime));
        int totalNumberOfTasks = tasks.getSize();
        ui.showAddTaskMessage();
        System.out.println(tasks.getTask(totalNumberOfTasks - 1).toString());
        System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
        storage.saveTasks(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
