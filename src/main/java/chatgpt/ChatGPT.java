package chatgpt;

import chatgpt.command.Command;
import chatgpt.command.Parser;

import chatgpt.exception.ChatBotException;

import chatgpt.task.TaskList;

import chatgpt.ui.Ui;

import chatgpt.storage.Storage;

public class ChatGPT {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public ChatGPT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }

    public static void main(String[] args) {
        new ChatGPT("\\data\\data1.txt").run();
    }
}
