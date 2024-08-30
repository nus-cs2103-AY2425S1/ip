package colress.command;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskList;
import colress.Ui;
import colress.task.Deadline;
import colress.task.Event;
import colress.task.Task;
import colress.task.ToDo;



public final class AddCommand extends Command {
    public AddCommand() {
        super("Okay. I have added this task to your list:\n");
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        String taskType = ui.promptTaskType();
        String description = ui.promptDescription(taskType);
        LocalDate date;
        LocalTime from;
        LocalTime to;
        Task task;

        if (taskType.equals("todo")) {
            task = new ToDo(description);
        } else if (taskType.equals("deadline")) {
            date = ui.promptDate(taskType);
            task = new Deadline(description, date);
        } else {
            date = ui.promptDate(taskType);
            from = ui.promptTime("from");
            to = ui.promptTime("to");
            task = new Event(description, date, from, to);
        }
        ui.printConfirmationMessage(taskList, getExecuteSuccessMessage() + taskList.addTask(task));
    }
}
