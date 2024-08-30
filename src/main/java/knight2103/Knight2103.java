package knight2103;

import knight2103.command.InstructionInvalid;
import knight2103.command.MissingCommand;
import knight2103.command.Parser;
import knight2103.command.Command;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

public class Knight2103 {
    private String name;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Knight2103(String filePath) {
        this.name = "Knight2103";
        this.ui = new Ui(this.name);
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) { // file cannot be loaded exception
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
            String fullCommand = this.ui.readCommand();
            this.ui.showLine();
                Command c = Parser.parse(fullCommand).orElseThrow(() -> new MissingCommand());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MissingCommand e) {
                System.out.println(e);
            } catch (InstructionInvalid e) {
                System.out.println("Instruction wrong format.");
            }
        }
    }
    public static void main(String[] args) {
        new Knight2103("./savedTaskList.txt").run(); // in ip folder, not java folder
    }
}