import java.util.HashMap;

public class TaskUpdateCommand extends Command {

    public TaskUpdateCommand(String name, HashMap<String, String> optionMap) {
        super(name, optionMap);
    }


    /**
     * Gets the number of the task from the option set while checking for valid arguments
     * @return The number of the task
     * @throws ZaibotException if the number option is not a valid integer, or bigger than the tasks list.
     */
    private Integer getNumberForTask(TaskList tasks) throws ZaibotException {
        HashMap<String, String> optionMap = super.getOptionMap();

        if (!(optionMap.containsKey("number") &&
                optionMap.get("number").matches("-?\\d+"))) {
            throw new ZaibotException("The correct syntax for this is: mark NUMBER");
        }
        Integer number = Integer.parseInt(optionMap.get("number"));
        if (number < 0 || number > tasks.getNumberOfTasks()) {
            throw new ZaibotException("Invalid number of tasks entered.");
        }
        return Integer.parseInt(optionMap.get("number"));
    }

    @Override
    public void runCommandSpecificLogic(TaskList tasks, Storage storage) throws ZaibotException {
        Task task;
        switch (super.getName()) {
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
            default:
                throw new ZaibotException("Invalid command.\n");
        }
    }
}
