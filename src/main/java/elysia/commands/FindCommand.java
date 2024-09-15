package elysia.commands;

import elysia.exceptions.ArgumentFormatException;
import elysia.exceptions.ElysiaException;
import elysia.exceptions.EmptyArgumentException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;
import elysia.ui.Message;

import java.util.Objects;

public class FindCommand extends Command {
    String[] args;

    public FindCommand(TaskList taskList, FileReaderWriter fileReaderWriter, String[] args) {
        super(taskList, fileReaderWriter);
        this.args = args;
    }
    @Override
    public String execute() throws ElysiaException {
        String output = "";
        if (args.length == 1) {
            throw new EmptyArgumentException(args[0]);
        } else if (args.length != 2) {
            throw new ArgumentFormatException(args[0]);
        }
        TaskList searchResults = taskList.searchByKeyword(args[1]);
        if (Objects.equals(searchResults.getSizeAsString(), "0")) {
            output = "I couldn't find anything...";
        } else {
            output = "Here's the tasks matching your search string! " +
                    "I hope you found what you're looking for!\n";
            output += searchResults.toString();
        }
        return output;
    }
}
