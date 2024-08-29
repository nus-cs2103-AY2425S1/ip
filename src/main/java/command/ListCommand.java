package command;

import task.TaskList;

import util.Storage;

import util.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks.toString());
    }
}