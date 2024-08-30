package puke;

import puke.exceptions.PukeException;
import puke.handlers.InputManager;
import puke.storage.Storage;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;
import puke.ui.Ui;

public class Puke {
    private MessageBuilder messageBuilder;
    private TaskManager taskManager;
    private InputManager inputManager;
    private Storage storage;
    private Ui ui;
    public Puke() {
        this.messageBuilder = new MessageBuilder();
        this.storage = new Storage();
        this.taskManager = new TaskManager(storage.loadTasks());
        this.inputManager = new InputManager(taskManager, messageBuilder);
        this.ui = new Ui(inputManager, messageBuilder);
    }
    public static void main(String[] args) throws PukeException {
        Puke chatBot = new Puke();
        chatBot.ui.start();
    }
}
