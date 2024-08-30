package joe;

import joe.command.Command;
import joe.task.TaskList;

public class Joe {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Joe(String filePath) {
       this.ui = new Ui();
       this.storage = new Storage(filePath);
       try {
           this.tasks = new TaskList(this.storage.loadData());
       } catch (JoeException e) {
           this.ui.printLoadingError();
           this.tasks = new TaskList();
       }
    }
    private void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JoeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Joe("./data/joe.txt").run();
    }
}
