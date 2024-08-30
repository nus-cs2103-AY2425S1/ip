package shenhe.command;

import shenhe.TaskList;
import shenhe.Ui;
import shenhe.Storage;
import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.task.Event;


public class EventCommand extends Command {
    private String userInput;
    public EventCommand(String userInput) {

        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 5) {
            throw new EmptyTaskDescriptionException();
        }

        // Split the input string into two parts using the first "/" character
        String[] parts = userInput.split("/", 3);

        // Extract the task description part (before the first "/")
        String task = parts[0].substring(5).trim();

        // Extract the starting time part (after the first "/")
        String from = parts[1].substring(4).trim();
        String to = parts[2].substring(2).trim();
        tasks.addTask(new Event(task, false, from, to));
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
