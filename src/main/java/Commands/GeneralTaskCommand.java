package Commands;

import exception.InvalidDeadlineException;
import prince.Prince;
import task.DeadlinesTask;
import task.EventTask;
import task.TaskList;
import task.ToDoTask;
import ui.Ui;

public class GeneralTaskCommand extends Command {

    public GeneralTaskCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {
        // according to the first word, create a new specific task
        // split into two, first word is type, and the second phrase is task
        System.out.println("Command passed checkUnknownCommand: " + this.cmd);

        String[] split = this.cmd.split(" ", 2);

        String type = split[0];
        String stringTask = split[1];

        if (type.equals(Prince.TaskType.todo.toString())) {

            ToDoCommand c = new ToDoCommand(stringTask);
            return c.commandAction();

        } else if (type.equals(Prince.TaskType.deadline.toString())) {

            DeadlineCommand c = new DeadlineCommand(stringTask);
            return c.commandAction();

        } else {

            EventCommand c = new EventCommand(stringTask);
            return c.commandAction();

        }
    }
}
