package elysia.commands;

import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;
import elysia.ui.Message;

import java.io.File;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList, FileReaderWriter fileReaderWriter) {
        super(taskList, fileReaderWriter);
    }

    @Override
    public String execute() {
        return "Here's your list! \n" + taskList.toString();
    }
}
