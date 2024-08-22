import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * This class represents a command by its name (the command itself) and a collection of its objects,
 * as parsed by the Parser.
 */
public class Command {

    private final String name;
    private final HashMap<String, String> optionMap;
    private boolean continueLoop;

    public Command(String name, HashMap<String, String> optionMap) {
        this.name = name;
        this.optionMap = optionMap;
        this.continueLoop = true;
    }

    /**
     * Returns if the command exits the program
     * @return true if command is "bye", false otherwise.
     */
    public boolean toContinue() {
        return this.continueLoop;
    }

    /**
     * Gets the number of the task from the option set while checking for valid arguments
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
     * @param tasks The task list
     * @param storage The storage object
     * @throws ZaibotException if there are errors in the argument inputs
     */
    public void execute(TaskList tasks, Storage storage) throws ZaibotException {
        Task task;

        try {

            switch (this.name) {
                case "bye":
                    Ui.printMessage("GOODBYE");
                    this.continueLoop = false;
                    break;
                case "list":
                    Ui.printTaskList(tasks);
                    break;
                case "mark":
                    task = tasks.markTask(this.getNumberForTask(tasks));
                    Ui.displayTask(task, "mark", tasks);
                    break;
                case "unmark":
                    task = tasks.unmarkTask(this.getNumberForTask(tasks));
                    Ui.displayTask(task, "mark", tasks);
                    break;
                case "delete":
                    tasks.removeTask(this.getNumberForTask(tasks));
                    Ui.displayTasksNumber(tasks);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    task = createTask(tasks);
                    Ui.displayTask(task, "add", tasks);
                    break;
                default:
                    throw new ZaibotException("Invalid command.\n");
            }
        }
        catch (ZaibotException e) {
            Ui.displayError(e);
        }
        finally {
            storage.saveToFile(tasks);
        }
    }

    /**
     * Processes a task addition given the command and the task name.
     * @param tasks The set of tasks
     * @throws ZaibotException throws errors if command is not following the syntax
     */
    public Task createTask(TaskList tasks) throws ZaibotException{

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
