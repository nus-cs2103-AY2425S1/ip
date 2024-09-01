package Karen.main;

import Karen.commands.Command;
import Karen.tasks.TaskList;
import Karen.util.Parser;
import Karen.util.Storage;
import Karen.util.Ui;

public class Karen {
    private TaskList taskList;
    private Ui ui;

    public Karen() {
        this.taskList = new TaskList();
        Storage.initFile();
        Storage.readFile(this.taskList);
        this.ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = this.ui.readInput();
                Command c = Parser.parse(commandString, this.ui);
                if (c != null) { //c is null when input is invalid
                    c.execute(this.taskList, this.ui);
                    isExit = c.isExit();
                }
            } catch (Exception e) {
                System.out.println("Exception occurred in main loop");
                System.out.println(e);
                return;
            }
        }
    }

    public static void main(String[] args) {
        new Karen().run();
    }
}
