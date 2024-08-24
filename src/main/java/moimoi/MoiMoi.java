package moimoi;

import java.util.ArrayList;
import moimoi.command.Command;
import moimoi.exception.MoiMoiException;
import moimoi.task.Task;

public class MoiMoi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MoiMoi(String path) {
        try {
            this.storage = new Storage(path);
            this.ui = new Ui();
            this.tasks = new TaskList(this.storage.load());
        } catch (MoiMoiException e) {
            this.ui.showMoiMoiException(e);
            this.tasks = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {

        this.ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                this.ui.showUserHeader();
                String input = this.ui.getInput();
                
                this.ui.showMoiMoiHeader();
                Command command = Parser.parse(input);
                command.execute(this.storage, this.tasks, this.ui);
                isExit = command.isExit();
            } catch (MoiMoiException e) {
                ui.showMoiMoiException(e);
            }
        }

    }

    public static void main(String[] args) {
        new MoiMoi("data/moimoi.txt").run();
    }

}
