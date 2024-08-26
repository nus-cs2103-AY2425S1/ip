package dumpling;

import dumpling.command.Command;
import dumpling.task.TaskList;

public class Dumpling {

    private Storage storage;
    private TaskList todoList;
    private Ui ui;
    private String saveDataPath;

    public Dumpling(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.todoList = new TaskList(storage.load());
        } catch (DumplingException e) {
            ui.showError(e);
            this.todoList = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        String userInput;
        while (!isExit) {
            try {
                userInput = this.ui.readCommand();
                this.ui.showLine();
                Command command = Parser.parse(userInput);
                command.execute(this.todoList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DumplingException e) {
                this.ui.showError(e);
            } finally {
                if (!isExit) {
                    this.ui.showLine();
                }
            }
        }
    }
}
