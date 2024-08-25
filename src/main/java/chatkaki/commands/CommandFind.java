package chatkaki.commands;

import chatkaki.tasks.TaskList;
import chatkaki.Ui;

public class CommandFind extends Command {

    private String keyword;
    public CommandFind(String[] inputs) {
        this.keyword = inputs[1];
    }

    @Override
    public void execute() {
        StringBuilder listMessage = new StringBuilder("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < TaskList.getSize(); i++) {
            if (TaskList.getTask(i).getDescription().contains(keyword)) {
                count++;
                listMessage.append("\n ").append(i + 1).append(". ").append(TaskList.getTask(i));
            }
        }
        if (count == 0) {
            Ui.printMessage("No matching tasks found.");
            return;
        }
        Ui.printMessage(listMessage.toString());
    }
}
