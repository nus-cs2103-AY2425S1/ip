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


    public String execute(TasksList tasksList, Ui ui, Storage storage) {
        String response = "";
        try {
            switch (commandType) {
            case BYE:
                response = handleBye(ui);
                break;
            case LIST:
                response = handleList(ui, tasksList);
                break;
            case MARK:
                response = handleMark(ui, tasksList);
                break;
            case UNMARK:
                response = handleUnmark(ui, tasksList);
                break;
            case TODO:
                response = handleToDo(ui, tasksList);
                break;
            case DEADLINE:
                response = handleDeadline(ui, tasksList);
                break;
            case EVENT:
                response = handleEvent(ui, tasksList);
                break;
            case DELETE:
                response = handleDelete(ui, tasksList);
                break;
            case FIND:
                response = handleFind(ui, tasksList);
                break;
            default:
                throw new UnknownCommandException();
            }
            storage.saveTasks(tasksList.getAllTasks());
        } catch (Exception e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

    private String handleBye(Ui ui) {
        String goodbyeMessage = ui.showGoodbye();

        // Schedule the app to close after showing the goodbye message
        new Thread(() -> {
            try {
                Thread.sleep(1000);  // Delay to let the message show
                System.exit(0);      // Close the application
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return goodbyeMessage;
    }


    private String handleList(Ui ui, TasksList tasksList) {
        return ui.showTasksList(tasksList.getAllTasks());
    }

    private String handleMark(Ui ui, TasksList tasksList) throws InvalidTaskNumberException {
        int markIndex = Integer.parseInt(arguments) - 1;
        Task taskToMark = tasksList.getTask(markIndex);
        taskToMark.markAsDone();
        return ui.showMarkTask(taskToMark);
    }

    private String handleUnmark(Ui ui, TasksList tasksList) throws InvalidTaskNumberException {
        int unmarkIndex = Integer.parseInt(arguments) - 1;
        Task taskToUnmark = tasksList.getTask(unmarkIndex);
        taskToUnmark.unmarkAsNotDone();
        return ui.showUnmarkTask(taskToUnmark);
    }

    private String handleToDo(Ui ui, TasksList tasksList) {
        Task newToDo = new ToDo(arguments);
        tasksList.addTask(newToDo);
        return ui.showAddTask(newToDo, tasksList.size());
    }

    private String handleDeadline(Ui ui, TasksList tasksList) throws InvalidInputFormatException {
        String[] deadlineParts = arguments.split(" /by ");
        if (deadlineParts.length < 2) {
            throw new InvalidInputFormatException("OOPS!!! The deadline command requires a description and a date/time after '/by'.");
        }
        Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
        tasksList.addTask(newDeadline);
        return ui.showAddTask(newDeadline, tasksList.size());
    }

    private String handleEvent(Ui ui, TasksList tasksList) throws InvalidInputFormatException {
        String[] eventParts = arguments.split(" /from | /to ");
        if (eventParts.length < 3) {
            throw new InvalidInputFormatException("OOPS!!! The event command requires a description, start time after '/from', and end time after '/to'.");
        }
        Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasksList.addTask(newEvent);
        return ui.showAddTask(newEvent, tasksList.size());
    }

    private String handleDelete(Ui ui, TasksList tasksList) throws InvalidTaskNumberException {
        int deleteIndex = Integer.parseInt(arguments) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasksList.size()) {
            throw new InvalidTaskNumberException();
        }
        Task taskToDelete = tasksList.getTask(deleteIndex);
        tasksList.deleteTask(deleteIndex);
        return ui.showDeleteTask(taskToDelete, tasksList.size());
    }

    private String handleFind(Ui ui, TasksList tasksList) {
        TasksList filteredTasksList = tasksList.getFilteredList(arguments);
        return ui.showTasksList(filteredTasksList.getAllTasks());
    }

}
