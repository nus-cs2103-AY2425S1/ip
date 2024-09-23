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

    /**
     * Executes the command and returns the response.
     *
     * @param tasksList List of tasks to be manipulated.
     * @param ui User interface to interact with user.
     * @param storage Storage to save and load tasks.
     * @return Response to be displayed to user.
     */
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

    /**
     * Handles the command to exit the application.
     *
     * @param ui User interface to interact with user.
     * @return Response to be displayed to user.
     */
    private String handleBye(Ui ui) {
        String goodbyeMessage = ui.showGoodbye();

        // Schedule the app to close after showing the goodbye message
        new Thread(() -> {
            try {
                // Delay to let the message show
                Thread.sleep(1000);
                // Close the application
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return goodbyeMessage;
    }

    /**
     * Handles the command to list all tasks.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be displayed.
     * @return Response to be displayed to user.
     */
    private String handleList(Ui ui, TasksList tasksList) {
        return ui.showTasksList(tasksList.getAllTasks());
    }

    /**
     * Handles the command to mark a task as done.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be manipulated.
     * @return Response to be displayed to user.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    private String handleMark(Ui ui, TasksList tasksList) throws InvalidTaskNumberException {
        int markIndex = Integer.parseInt(arguments) - 1;
        tasksList.getTask(markIndex);
        Task taskToMark = tasksList.getTask(markIndex);
        taskToMark.markAsDone();
        return ui.showMarkTask(taskToMark);
    }

    /**
     * Handles the command to unmark a task as not done.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be manipulated.
     * @return Response to be displayed to user.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    private String handleUnmark(Ui ui, TasksList tasksList) throws InvalidTaskNumberException {
        int unmarkIndex = Integer.parseInt(arguments) - 1;
        tasksList.getTask(unmarkIndex);
        Task taskToUnmark = tasksList.getTask(unmarkIndex);
        taskToUnmark.unmarkAsNotDone();
        return ui.showUnmarkTask(taskToUnmark);
    }

    /**
     * Handles the command to add a ToDo task.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be manipulated.
     * @return Response to be displayed to user.
     */
    private String handleToDo(Ui ui, TasksList tasksList) {
        Task newToDo = new ToDo(arguments);
        tasksList.addTask(newToDo);
        return ui.showAddTask(newToDo, tasksList.size());
    }

    /**
     * Handles the command to add a Deadline task.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be manipulated.
     * @return Response to be displayed to user.
     * @throws InvalidInputFormatException If the input format is invalid.
     */
    private String handleDeadline(Ui ui, TasksList tasksList) throws InvalidInputFormatException {
        String[] deadlineParts = arguments.split(" /by ");
        if (deadlineParts.length < 2) {
            throw new InvalidInputFormatException("OOPS!!! The deadline command requires a description"
                    + " and a date/time after '/by'.");
        }
        Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
        tasksList.addTask(newDeadline);
        return ui.showAddTask(newDeadline, tasksList.size());
    }

    /**
     * Handles the command to add an Event task.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be manipulated.
     * @return Response to be displayed to user.
     * @throws InvalidInputFormatException If the input format is invalid.
     */
    private String handleEvent(Ui ui, TasksList tasksList) throws InvalidInputFormatException {
        String[] eventParts = arguments.split(" /from | /to ");
        if (eventParts.length < 3) {
            throw new InvalidInputFormatException("OOPS!!! The event command requires a description,"
                    + " start time after '/from', and end time after '/to'.");
        }
        Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
        tasksList.addTask(newEvent);
        return ui.showAddTask(newEvent, tasksList.size());
    }

    /**
     * Handles the command to delete a task.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be manipulated.
     * @return Response to be displayed to user.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    private String handleDelete(Ui ui, TasksList tasksList) throws InvalidTaskNumberException {
        int deleteIndex = Integer.parseInt(arguments) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasksList.size()) {
            throw new InvalidTaskNumberException();
        }
        Task taskToDelete = tasksList.getTask(deleteIndex);
        tasksList.deleteTask(deleteIndex);
        return ui.showDeleteTask(taskToDelete, tasksList.size());
    }

    /**
     * Handles the command to filter task list by keyword.
     *
     * @param ui User interface to interact with user.
     * @param tasksList List of tasks to be filtered.
     * @return Response to be displayed to user.
     */
    private String handleFind(Ui ui, TasksList tasksList) {
        TasksList filteredTasksList = tasksList.getFilteredList(arguments);
        return ui.showTasksList(filteredTasksList.getAllTasks());
    }

}
