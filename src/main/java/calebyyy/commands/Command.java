package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;
import calebyyy.exceptions.CalebyyyException;

public abstract class Command {
    protected Calebyyy calebyyy;
    protected TaskList taskList;
    protected Ui ui;

    public Command(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        this.calebyyy = calebyyy;
        this.ui = ui;
        this.taskList = taskList;
    }

    public abstract void execute(String input) throws CalebyyyException;
}