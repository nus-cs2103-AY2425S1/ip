package citadel.commands;

import citadel.exception.CitadelException;
import citadel.Task.TaskList;

public abstract class Command {

    public String input;
    public TaskList tasks;

    public Command(String input, TaskList tasks) {
        this.input = input;
        this.tasks = tasks;
    }
    public abstract void run() throws CitadelException;
}
