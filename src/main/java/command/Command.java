package command;

import java.util.Random;

import task.Task;
import task.TaskList;

public abstract class Command {
    private final Random RNG = new Random();
    private int i;
    private Task task;

    public Command(int i, Task t) {
        this.i = i;
        this.task = t;
    }

    public Task getTask() {
        return this.task;
    }

    public int getI() {
        return this.i;
    }

    /**
     * Randomly selects and returns a prefix for a command from a given list
     * 
     * @param prefixes
     * @return a randomly selected prefix from the given list of prefixes
     */
    public String generateRandomPrefix(String[] prefixes) {
        return prefixes[this.RNG.nextInt(prefixes.length)];
    }

    public abstract String execute(TaskList tasks);

    public boolean isExitCommand() {
        return false;
    }

    public boolean isErrorCommand() {
        return false;
    }
}
