import java.io.IOException;

public abstract class Command {
    /**
     * Executes intended command
     *
     * @param list
     * @param ui
     * @param fileManager
     */
    public abstract void execute(TaskList list, UI ui, FileManager fileManager) throws InputErrorException, IOException;

}
