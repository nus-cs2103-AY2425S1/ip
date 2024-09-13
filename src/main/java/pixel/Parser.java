package pixel;

/**
 * Class that deals with the control flow upon obtaining user command
 */
public class Parser {

    /**
     * Constructor method of Parser
     */
    public Parser() {
    }

    /**
     * Creates a ToDo
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     * @return String for Ui to display confirmation of addition
     */
    public static String createToDo(String command, TaskList tasks, Storage storage) {
        String description = command.replace("todo", "").trim();
        if (description.isEmpty()) {
            return Ui.handleEmptyDescriptionException();
        }
        ToDo newToDo = new ToDo(description);
        tasks.addTask(newToDo);
        storage.updateFile(tasks);
        return Ui.getAddConfirmation(tasks);
    }

    /**
     * Creates a Deadline
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     * @return String for Ui to display confirmation of addition
     */
    public static String createDeadline(String command, TaskList tasks, Storage storage) {
        String[] stringArray = command.split("/", 0);
        assert stringArray.length == 2;
        String description = stringArray[0].replace("deadline", "").trim();
        String by = stringArray[1].replace("by", "").trim();
        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        storage.updateFile(tasks);
        return Ui.getAddConfirmation(tasks);
    }

    /**
     * Creates an Event
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     * @return String for Ui to display confirmation of addition
     */
    public static String createEvent(String command, TaskList tasks, Storage storage) {
        String[] stringArray = command.split("/", 0);
        assert stringArray.length == 3;
        String description = stringArray[0].replace("event", "").trim();
        String from = stringArray[1].replace("from", "").trim();
        String to = stringArray[2].replace("to", "").trim();
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        storage.updateFile(tasks);
        return Ui.getAddConfirmation(tasks);
    }

    /**
     * Marks a Task as complete
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     * @return String for Ui to display confirmation of marking
     */
    public static String markTask(String command, TaskList tasks, Storage storage) {
        String[] stringArray = command.split(" ", 0);
        Task currentTask = tasks.getTask(Integer.parseInt(stringArray[1]) - 1);
        currentTask.markAsDone();
        storage.updateFile(tasks);
        return Ui.getMarkConfirmation(currentTask);
    }

    /**
     * Unmarks a Task as incomplete
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     * @return String for Ui to display confirmation of unmarking
     */
    public static String unmarkTask(String command, TaskList tasks, Storage storage) {
        String[] stringArray = command.split(" ", 0);
        Task currentTask = tasks.getTask(Integer.parseInt(stringArray[1]) - 1);
        currentTask.markAsUndone();
        storage.updateFile(tasks);
        return Ui.getUnmarkConfirmation(currentTask);
    }

    /**
     * Deletes a Task from TaskList
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     * @return String for Ui to display confirmation of deletion
     */
    public static String deleteTask(String command, TaskList tasks, Storage storage) {
        String[] stringArray = command.split(" ", 0);
        Integer index = Integer.parseInt(stringArray[1]) - 1;
        Task removedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.updateFile(tasks);
        return Ui.getDeleteConfirmation(removedTask, tasks);
    }

    /**
     * Finds tasks that matches the keyword
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @return String for Ui to display all tasks that matches the keywords
     */
    public static String findTaskByKeyword(String command, TaskList tasks) {
        String searchKey = command.replace("find ", "").trim();
        return Ui.getMatchingTasks(searchKey, tasks);
    }

    public static String findTaskByDate(String command, TaskList tasks) {
        String[] stringArray = command.split(" ", 0);
        assert stringArray.length == 2;
        String date = stringArray[1].trim();
        return Ui.getMatchingDates(date, tasks);
    }

    /**
     * Act based on command given from the user's input
     *
     * @param command command obtained from Ui
     * @param tasks TaskList for processing of tasks
     * @param storage Storage for processing of files
     */
    public static String parse(String command, TaskList tasks, Storage storage) {
        if (command.equals("list")) {
            return Ui.getListAsString(tasks);
        } else if (command.startsWith("todo")) {
            return createToDo(command, tasks, storage);
        } else if (command.startsWith("deadline")) {
            return createDeadline(command, tasks, storage);
        } else if (command.startsWith("event")) {
            return createEvent(command, tasks, storage);
        } else if (command.startsWith("mark")) {
            return markTask(command, tasks, storage);
        } else if (command.startsWith("unmark")) {
            return unmarkTask(command, tasks, storage);
        } else if (command.startsWith("delete")) {
            return deleteTask(command, tasks, storage);
        } else if (command.startsWith("find")) {
            return findTaskByKeyword(command, tasks);
        } else if (command.startsWith("view")) {
            return findTaskByDate(command, tasks);
        } else if (command.equals("bye")) {
            return Ui.getExit();
        } else {
            return Ui.handleInvalidCommandException();
        }
    }
}
