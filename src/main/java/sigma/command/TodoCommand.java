package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;
import sigma.task.ToDoTask;

public class TodoCommand extends Commands {

    public TodoCommand(String[] split) {
        super(split);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        ToDoTask toDoTask = null;
        try {
            if (split.length < 2 || split[1].strip() == "") {
                ui.throwError("???? You're missing the task! Write \"todo <task>\"!");
            }
            toDoTask = new ToDoTask(split[1].strip());
        } catch (NullPointerException e) {
            ui.throwError("Please don't run me on null!");
        }
        tasks.add(toDoTask);
        ui.print("Productive! Added: \n" + toDoTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list!");
    }

}
