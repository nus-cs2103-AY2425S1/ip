package command;

import utility.Ui;
import utility.Storage;
import utility.TaskList;
import task.TodoTask;
import exception.ElliotException;

public class TodoCommand extends Command {
    private final String taskDescription;

    public TodoCommand() {
        this.taskDescription = "";
    }

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public Command parseArguments(String unparsedArguments) throws ElliotException {
        if (unparsedArguments == "") {
            Ui.say("give your todo task a description\n");
            throw new ElliotException();
        }
        return new TodoCommand(unparsedArguments.strip());
    }

    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        taskList = taskList.addTask(new TodoTask(taskDescription));
        Ui.say("Got it. I've added this task:\n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.\n");
        return taskList;
    }

}
