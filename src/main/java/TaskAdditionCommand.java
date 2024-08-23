import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TaskAdditionCommand extends Command {

    public TaskAdditionCommand(String name, HashMap<String, String> optionMap) {
        super(name, optionMap);
    }

    @Override
    public void runCommandSpecificLogic(TaskList tasks, Storage storage) throws ZaibotException {
        Task task;

        switch (super.getName()) {
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

    /**
     * Processes a task addition given the command and the task name.
     * @param tasks The set of tasks
     * @throws ZaibotException throws errors if command is not following the syntax
     */
    public Task createTask(TaskList tasks) throws ZaibotException{

        Task task;
        HashMap<String,String> optionMap = super.getOptionMap();

        String command = super.getName();
        String taskName = optionMap.get("name");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (command) {
            case "todo":
                task = new ToDoTask(taskName);
                break;
            case "deadline":
                if (!optionMap.containsKey("by")) {
                    throw new ZaibotException("Deadline must have option /by.");
                }
                String by = optionMap.get("by");
                task = new DeadlineTask(taskName, LocalDateTime.parse(by, formatter));
                break;
            case "event":
                if (!optionMap.containsKey("from") || !optionMap.containsKey("to")) {
                    throw new ZaibotException("Event must have option /from and /to.");
                }
                String from = optionMap.get("from");
                String to = optionMap.get("to");
                task = new EventTask(taskName,
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
