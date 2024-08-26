package xizi.chatbot.command;

import xizi.chatbot.Storage;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.Ui;

public class HelpCommand implements Command {
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.printHelp();
    }
}

