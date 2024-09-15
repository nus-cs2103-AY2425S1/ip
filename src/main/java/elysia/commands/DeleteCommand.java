package elysia.commands;

import elysia.exceptions.ElysiaException;
import elysia.exceptions.EmptyArgumentException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Task;
import elysia.tasks.TaskList;
import elysia.ui.Message;

public class DeleteCommand extends Command {
    String[] args;

    public DeleteCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    @Override
    public String execute() throws EmptyArgumentException, WrongArgumentException {
        String output = "";
        if (args.length == 1) {
            throw new EmptyArgumentException(args[0]);
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new WrongArgumentException("task number");
        }
        try {
            Task deletedTask = taskList.deleteTask(taskNumber);
            output = "You don't need this task below anymore? Ok deleting it~\n";
            output += deletedTask.toString();
            output += "Wow! You now have " + taskList.getSizeAsString() + " tasks in your list!";
        } catch (IndexOutOfBoundsException e) {
            output = "Uh oh, this task number does not exist...";
        }
        return output;
    }
}
