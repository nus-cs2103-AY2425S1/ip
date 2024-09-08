package elysia.ui;

import elysia.tasks.TaskList;

/**
 * A stub implementation of the {@link FileReaderWriter} class for testing purposes.
 * This class overrides methods from {@link FileReaderWriter} to track method calls and provide predefined responses.
 */
public class FileReaderWriterStub extends FileReaderWriter{
    boolean createFileCalled = false;
    boolean writeFileCalled = false;

    /**
     * Constructs a {@link FileReaderWriterStub} with a dummy {@link TaskList}.
     * The constructor is used to initialize the stub without actual file operations.
     *
     * @param taskList A {@link TaskList} instance (not used in this stub).
     */
    public FileReaderWriterStub(TaskList taskList) {
        super(null);// TaskList not used in this stub
    }

    /**
     * Overrides the {@link FileReaderWriter#createFile()} method to track if it is called.
     * Sets the {@code createFileCalled} flag to {@code true} and returns a predefined message.
     *
     * @return A string indicating that the file was created.
     */
    @Override
    public String createFile() {
        createFileCalled = true;
        return "I created data.txt for you~";
    }

    /**
     * Overrides the {@link FileReaderWriter#writeFile()} method to track if it is called.
     * Sets the {@code writeFileCalled} flag to {@code true} and returns a predefined message.
     *
     * @return A string indicating that the tasks are being saved.
     */
    @Override
    public String writeFile() {
        writeFileCalled = true;
        return "Saving all your tasks for you~";
    }

    /**
     * Overrides the {@link FileReaderWriter#readFile()} method to return a predefined message.
     * This method does not track if it is called and simply returns a fixed response.
     *
     * @return A string indicating that past data has been loaded.
     */
    @Override
    public String readFile() {
        return "I loaded your past data! Aren't I amazing? Come on praise me!";
    }
}
