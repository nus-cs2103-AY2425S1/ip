package elysia.commands;

import elysia.storage.FileReaderWriter;
import elysia.tasks.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(TaskList taskList, FileReaderWriter fileReaderWriter) {
        super(taskList, fileReaderWriter);
    }
    @Override
    public String execute() {
        return fileReaderWriter.createFile() + "\n" + fileReaderWriter.writeFile();
    }
}
