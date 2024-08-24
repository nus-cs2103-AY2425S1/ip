package Spongebob.command;

import Spongebob.exception.SpongebobException;
import Spongebob.storage.Storage;
import Spongebob.storage.TaskList;
import Spongebob.Ui;
import Spongebob.task.Deadline;
import Spongebob.task.Event;
import Spongebob.task.Task;
import Spongebob.task.Todo;

public class AddCommand extends Command {

    private String[] arguments;

    public AddCommand(String[] arguments) {


        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask = null;

        try {
            switch (this.arguments[0]) {
                case "todo":
                    newTask = new Todo(arguments[1]);
                    break;

                case "deadline":
                    newTask = new Deadline(arguments[1], arguments[2]);
                    break;
                case "event":
                    newTask = new Event(arguments[1], arguments[2], arguments[3]);
                    break;
            }

            taskList.add(newTask);
            storage.add(newTask);
            ui.showTaskAdded(newTask, taskList.size());

        } catch (SpongebobException e) {
            ui.showException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String[] getArgs() {
        return this.arguments;
    }
}
