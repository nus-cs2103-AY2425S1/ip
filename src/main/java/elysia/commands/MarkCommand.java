package elysia.commands;

import elysia.exceptions.EmptyArgumentException;
import elysia.exceptions.WrongArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;
import elysia.ui.Message;

import java.util.Objects;

public class MarkCommand extends Command {
    String[] args;

    public MarkCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }

    @Override
    public String execute() throws EmptyArgumentException, WrongArgumentException {
        StringBuilder output = new StringBuilder();
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
            taskList.markTask(taskNumber);
            output.append("Amazing! You've completed this task! \n");
            output.append(taskList.printTask(taskNumber));
            assert(!Objects.equals(taskList.getSizeAsString(), "0"));
        } catch (IndexOutOfBoundsException e) {
            output.append("Uh oh, this task number does not exist...");
        } catch (NullPointerException e) {
            output.append("You haven't even added anything to your list!");
        }
        return output.toString();
    }
}
