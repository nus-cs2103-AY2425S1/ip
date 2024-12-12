package xizi.chatbot.command;

import java.io.IOException;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.TaskList;


/**
 * Represents an executable command in the Xizi chatbot.
 */
public interface Command {

    /**
     * Executes the command, performing the necessary operations on the task list,
     * storage, and user interface.
     *
     * @param actions the {@code TaskList} containing the user's tasks
     * @param storage the {@code Storage} used to save and load tasks
     * @param ui      the {@code Ui} used to interact with the user
     * @throws IOException   if an I/O error occurs during command execution
     * @throws XiziException if a specific error related to the Xizi chatbot occurs
     */
    void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException;
}


