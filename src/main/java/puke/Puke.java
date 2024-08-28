package puke;

import puke.handlers.InputManager;
import puke.tasklist.TaskManager;
import puke.ui.MessageBuilder;
import puke.ui.Ui;

public class Puke {
    private MessageBuilder messageBuilder;
    private TaskManager taskManager;
    private InputManager inputManager;
    private Ui ui;
    public Puke() {
        this.messageBuilder = new MessageBuilder();
        this.taskManager = new TaskManager();
        this.inputManager = new InputManager(taskManager, messageBuilder);
        this.ui = new Ui(inputManager, messageBuilder);
    }
    public static void main(String[] args) {
        Puke chatBot = new Puke();
        chatBot.ui.start();
    }
}
