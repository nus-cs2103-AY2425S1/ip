package elysia.commands;

import elysia.exceptions.ElysiaException;
import elysia.storage.FileReaderWriter;
import elysia.tasks.Task;
import elysia.tasks.TaskList;

import java.io.File;

public abstract class Command {
    TaskList taskList;
    FileReaderWriter fileReaderWriter;

    public Command(TaskList taskList, FileReaderWriter fileReaderWriter) {
        this.taskList = taskList;
        this.fileReaderWriter = fileReaderWriter;
    }

    public abstract String execute() throws ElysiaException;
}
