package tars.commands;

import tars.tasks.TaskList;

public abstract class Command {
    public abstract String execute(String input, TaskList tasks);



}
