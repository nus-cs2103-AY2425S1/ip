package chatbot;

import chatbot.exception.InputException;

public class Bobby {
//    private final Scanner input;
    private Ui ui;
//    private ArrayList<Bobby.Task> tasks;
    private TaskList taskList;
    private Storage storage;
    private static final String DIR_PATH = "./data/";
    private static final String FILE_NAME = "tasks.txt";

    public Bobby() {
        this.ui = new Ui();
        this.storage = new Storage(DIR_PATH, FILE_NAME);
        this.taskList = new TaskList(this.storage.load());
    }

    public void run() {
        this.ui.sayHi();
        while (this.ui.isRunning()) {
            try {
                Parser.processInput(this.ui.getInput(), this.taskList, this.storage, this.ui);
            } catch (InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Bobby chatBot = new Bobby();
        chatBot.run();
    }
}
