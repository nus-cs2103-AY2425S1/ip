package command;

import task.Task;

import java.util.ArrayList;

public class UnknownCommand extends Command {
    public UnknownCommand() {

    }
    @Override
    public void execute(ArrayList<Task> todoList) {
        System.out.println("I do not understand your request. Clarify your command.");
    }
}
