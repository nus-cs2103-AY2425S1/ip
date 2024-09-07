package pebble;

/**
 * Class that handles the commands that the user can execute.
 */
public class Command {
    private CommandType commandType;
    private String arguments;

    /**
     * Constructs a Command object.
     *
     * @param commandType Type of command to be executed.
     * @param arguments Additional information for command.
     */
    public Command(CommandType commandType, String arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getArguments() {
        return arguments;
    }

    /**
     * Receives input from either user or local storage to operate the chat bot.
     *
     * @param tasksList The current list of tasks.
     * @param ui The UI instance to handle output.
     * @param storage The Storage Instance to retrieve or create tasksList
     */
    public void execute(TasksList tasksList, Ui ui, Storage storage) {
        try {
            switch (commandType) {
            case BYE:
                ui.showGoodbye();
                break;
            case LIST:
                ui.showTasksList(tasksList.getAllTasks());
                break;
            case MARK:
                int markIndex = Integer.parseInt(arguments) - 1;
                Task taskToMark = tasksList.getTask(markIndex);
                taskToMark.markAsDone();
                ui.showMarkTask(taskToMark);
                break;
            case UNMARK:
                int unmarkIndex = Integer.parseInt(arguments) - 1;
                Task taskToUnmark = tasksList.getTask(unmarkIndex);
                taskToUnmark.unmarkAsNotDone();
                ui.showUnmarkTask(taskToUnmark);
                break;
            case TODO:
                Task newToDo = new ToDo(arguments);
                tasksList.addTask(newToDo);
                ui.showAddTask(newToDo, tasksList.size());
                break;
            case DEADLINE:
                String[] deadlineParts = arguments.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new InvalidInputFormatException("OOPS!!! The deadline command requires "
                            + "a description and a date/time after '/by'.");
                }
                Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasksList.addTask(newDeadline);
                ui.showAddTask(newDeadline, tasksList.size());
                break;
            case EVENT:
                String[] eventParts = arguments.split(" /from | /to ");
                if (eventParts.length < 3) {
                    throw new InvalidInputFormatException("OOPS!!! The event command requires "
                            + "a description, start time after '/from', and end time after '/to'.");
                }
                Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                tasksList.addTask(newEvent);
                ui.showAddTask(newEvent, tasksList.size());
                break;
            case DELETE:
                int deleteIndex = Integer.parseInt(arguments) - 1;
                if (deleteIndex < 0 || deleteIndex > tasksList.size()) {
                    throw new InvalidTaskNumberException();
                }
                Task taskToDelete = tasksList.getTask(deleteIndex);
                tasksList.deleteTask(deleteIndex);
                ui.showDeleteTask(taskToDelete, tasksList.size());
                break;
            case FIND:
                TasksList filteredTasksList = tasksList.getFilteredList(arguments);
                ui.showTasksList(filteredTasksList.getAllTasks());
                break;
            default:
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
            storage.saveTasks(tasksList.getAllTasks());
        } catch (Exception e) {
            ui.showError("Error: " + e.getMessage());
        }
    }

}
