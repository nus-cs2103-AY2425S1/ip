package ipman.models;

/**
 * Represents a file manager that does nothing
 */
public class DummyFileManager extends TasksFileManager {
    public DummyFileManager() {
        super("");
    }

    @Override
    public void append(Task task) {}

    @Override
    public void save(TaskList tasks) {}

    @Override
    public void load(TaskList tasks) {}
}
