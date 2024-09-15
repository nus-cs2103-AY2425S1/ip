package elysia.commands;

import elysia.exceptions.ElysiaException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;
import elysia.ui.Message;

public class UnknownCommand extends Command {

    public UnknownCommand(TaskList taskList, FileReaderWriter fileReaderWriter) {
        super(taskList, fileReaderWriter);
    }

    @Override
    public String execute() {
        return "What are you trying to say? You need to talk to pretty girls nicely...";
    }
}
