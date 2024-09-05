package command;

import java.util.Random;

import task.Task;
import task.TaskList;

public abstract class Command {
    private final Random RNG = new Random();
    private int taskIndex;
    private Task task;

    public Command(int i, Task t) {
        this.taskIndex = i;
        this.task = t;
    }

    public Task getTask() {
        return this.task;
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }

    /**
     * Randomly selects and returns a prefix for a command from a given list.
     * 
     * @param prefixes
     * @return a randomly selected prefix from the given list of prefixes.
     */
    public String generateRandomPrefix(String[] prefixes) {
        return prefixes[this.RNG.nextInt(prefixes.length)];
    }

    /**
     * Executes the command that the object is encapsulating
     * (adding/deleting/editing a task, listing the tasks, etc.).
     * 
     * @param tasks the task list for reference.
     * @return a string that will be output by the chatbot upon completion of the command.
     */
    public abstract String execute(TaskList tasks);

    public boolean isExitCommand() {
        return false;
    }

    public boolean isErrorCommand() {
        return false;
    }
}
