package elysia.ui;

import elysia.tasks.TaskList;

public class FileReaderWriterStub extends FileReaderWriter{
    boolean createFileCalled = false;
    boolean writeFileCalled = false;

    public FileReaderWriterStub(TaskList taskList) {
        super(null);
    }

    @Override
    public String createFile() {
        createFileCalled = true;
        return "I created data.txt for you~";
    }

    @Override
    public String writeFile() {
        writeFileCalled = true;
        return "Saving all your tasks for you~";
    }

    @Override
    public String readFile() {
        return "I loaded your past data! Aren't I amazing? Come on praise me!";
    }
}
