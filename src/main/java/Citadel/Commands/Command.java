package Citadel.Commands;
import Citadel.exception.CitadelException;
import Citadel.Task.TaskList;

public abstract class Command {

    public String input;
    public TaskList tasks;

    public Command(String input, TaskList tasks) {
        this.input = input;
        this.tasks = tasks;
    }
    public abstract void run() throws CitadelException;
}
