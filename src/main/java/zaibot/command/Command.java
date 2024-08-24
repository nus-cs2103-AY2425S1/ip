package zaibot.command;

import zaibot.*;
import zaibot.exception.ZaibotException;
import zaibot.task.DeadlineTask;
import zaibot.task.EventTask;
import zaibot.task.Task;
import zaibot.task.ToDoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return this.name;
    }

    public HashMap<String, String> getOptionMap() {
        return this.optionMap;
    }

    /**
     * Gets the number of the task from the option set while checking for valid arguments
     *
     * @return The number of the task
     * @throws ZaibotException if the number option is not a valid integer, or bigger than the tasks list.
     */
    private Integer getNumberForTask(TaskList tasks) throws ZaibotException {
        if (!(this.optionMap.containsKey("number") &&
                this.optionMap.get("number").matches("-?\\d+"))) {
            throw new ZaibotException("The correct syntax for this is: mark NUMBER");
        }
        Integer number = Integer.parseInt(this.optionMap.get("number"));
        if (number < 0 || number > tasks.getNumberOfTasks()) {
            throw new ZaibotException("Invalid number of tasks entered.");
        }
        return Integer.parseInt(this.optionMap.get("number"));
    }

    /**
     * Executes the command, having effect on the tasks and storage. Throws exception when there are
     * errors in the argument inputs.
     *
     * @param tasks   The task list
     * @param storage The storage object
     * @throws ZaibotException if there are errors in the argument inputs
     */
    public void execute(TaskList tasks, Storage storage) throws ZaibotException {
        Task task;

        this.runCommandSpecificLogic(tasks, storage);
        storage.saveToFile(tasks);
    }

    /**
     * This abstract method is to implement the command-specific logic
     *
     * @param tasks   The list of tasks
     * @param storage The storage object
     * @throws ZaibotException if there is an issue processing the command.
     */
    public abstract void runCommandSpecificLogic(TaskList tasks, Storage storage) throws ZaibotException;

    /**
     * Processes a task addition given the command and the task name.
     *
     * @param tasks The set of tasks
     * @throws ZaibotException throws errors if command is not following the syntax
     */
    public Task createTask(TaskList tasks) throws ZaibotException {

        Task task;

        String name = this.optionMap.get("name");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (this.name) {
        case "todo":
            task = new ToDoTask(name);
            break;
        case "deadline":
            if (!optionMap.containsKey("by")) {
                throw new ZaibotException("Deadline must have option /by.");
            }
            String by = optionMap.get("by");
            task = new DeadlineTask(name, LocalDateTime.parse(by, formatter));
            break;
        case "event":
            if (!optionMap.containsKey("from") || !optionMap.containsKey("to")) {
                throw new ZaibotException("Event must have option /from and /to.");
            }
            String from = optionMap.get("from");
            String to = optionMap.get("to");
            task = new EventTask(name,
                    LocalDateTime.parse(from, formatter),
                    LocalDateTime.parse(to, formatter));
            break;
        default:
            throw new ZaibotException("Invalid task");
        }
        tasks.addTask(task);
        return task;
    }
}
