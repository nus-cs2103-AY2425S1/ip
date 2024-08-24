package chatkaki.commands;

import chatkaki.tasks.TaskList;
import chatkaki.Ui;

public class CommandList extends Command {
    private String[] inputs;

    public CommandList(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        StringBuilder listMessage = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.getSize(); i++) {
            listMessage.append("\n ").append(i + 1).append(". ").append(TaskList.getTask(i));
        }
        Ui.printMessage(listMessage.toString());
    }
}
