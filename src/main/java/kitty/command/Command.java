package kitty.command;

import kitty.TaskList;
import kitty.Ui;

class Command {
    public Ui ui;
    public TaskList tasks;

    public Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Carries out the operation
     */
    public void run() {
        return;
    }
}
