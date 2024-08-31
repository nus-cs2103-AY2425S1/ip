package main.java.commands;

import main.java.Event;
import main.java.TaskList;
import main.java.util.Storage;
import main.java.util.Ui;

public class EventCommand extends Command{
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.event);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
