package command;

import task.Task;

import java.util.ArrayList;

public class EmptyCommand extends Command {
    public EmptyCommand() {

    }
    @Override
    public void execute(ArrayList<Task> todoList) {
        System.out.println("Insufficient data. Clarify your command.");
    }
}
