package zaibot.command;

import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.exception.ZaibotException;
import zaibot.task.Task;

import java.util.HashMap;

/**
 * This class represents a command by its name (the command itself) and a collection of its objects,
 * as parsed by the zaibot.Parser.
 */
public abstract class Command {

    private final String name;
    private final HashMap<String, String> optionMap;
    private boolean willContinueLoop;

    public Command(String name, HashMap<String, String> optionMap) {
        this.name = name;
        this.optionMap = optionMap;
        this.willContinueLoop = true;
    }

    /**
     * Returns if the command exits the program
     *
     * @return true if command is "bye", false otherwise.
     */
    public boolean checkContinue() {
        return this.willContinueLoop;
    }

    public void setWillContinueLoop(boolean willContinueLoop) {
        this.willContinueLoop = willContinueLoop;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getOptionMap() {
        return optionMap;
    }

    /**
     * Executes the command, having effect on the tasks and storage. Throws exception when there are
     * errors in the argument inputs. Returns the response.
     *
     * @param tasks   The task list
     * @param storage The storage object
     * @throws ZaibotException if there are errors in the argument inputs
     *
     * @return Gets the response from zaibot.
     */
    public String execute(TaskList tasks, Storage storage) throws ZaibotException {
        Task task;

        String result = this.runCommandSpecificLogic(tasks, storage);
        storage.saveToFile(tasks);

        return result;
    }

    /**
     * This abstract method is to implement the command-specific logic
     *
     * @param tasks   The list of tasks
     * @param storage The storage object
     * @throws ZaibotException if there is an issue processing the command.
     */
    public abstract String runCommandSpecificLogic(TaskList tasks, Storage storage) throws ZaibotException;
}
