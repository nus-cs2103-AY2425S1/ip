package xizi.chatbot.command;

import xizi.chatbot.Storage;
import xizi.chatbot.task.TaskList;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;

import java.io.IOException;

public interface Command {
    void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException;
}

