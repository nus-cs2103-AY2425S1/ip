package echo;

import echo.backend.Parser;
import echo.task.Event;
import echo.task.Todo;
import echo.task.Deadline;
import echo.task.TaskList;
import echo.task.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Ui class handles user interaction and command execution.
 * It accepts user input and interacts with the Parser class to process commands and the TaskList class.
 */
public class Ui {
    private Parser parser;
    private TaskList taskList;
    private Echo echo;
    private int tempIndex = -1;
    /**
     * Constructs a Ui object with the specified TaskList.
     *
     * @param taskList the TaskList object to be referenced by the Ui
     */
    public Ui(TaskList taskList, Echo echo) {
        assert taskList != null: "Task list should not be null";
        assert echo != null: "Echo should not be null";

        parser = new Parser(this);
        this.taskList = taskList;
        this.echo = echo;
    }
    /**
     * Starts accepting user input and processes commands until the user inputs 'Bye'.
     */
    public String handleInput(String input) {
        return parser.handleInput(input);
    }
    /**
     * Stops accepting user input.
     */
    public void stopAcceptingInput() {
        echo.stopRunning();
    }
    /**
     * Handles unknown commands by printing an error message.
     */
    public String handleUnknown() {
        return unknownMessage();
    }
    /**
     * Handles the "bye" command and stops accepting user input.
     */
    public String handleBye() {
        stopAcceptingInput();
        return byeMessage();
    }
    /**
     * Handles the "list" command and prints all tasks in the TaskList.
     */
    public String handleList() {
        return listMessage(taskList.getTasksString());
    }
    /**
     * Handles the "find" command by searching for tasks that contain the specified
     * substring and printing the matching tasks in a formatted list.
     *
     * @param arg The substring to search for within the tasks.
     */
    public String handleFind(String arg) {
        String foundTasks = taskList.getFoundTasks(arg);
        return foundMessage(foundTasks);
    }
    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param arg the argument provided with the "mark" command (task index)
     */
    public String handleMark(String arg) {
        // Error handling
        if (arg.length() != 1) { // Arg of incorrect length
            return "Please input 'mark [index]'";
        }

        int index;
        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) { // Index is not an integer
            return "Please input 'mark [index]'";
        }

        if (index > taskList.getNumTasks()) { // Index is not within tasks length
            return "Invalid index.";
        }

        taskList.markTask(index);

        return markedTaskMessage(taskList.getTaskString(index));
    }
    /**
     * Handles the "unmark" command to unmark a task as not done.
     *
     * @param arg the argument provided with the "unmark" command (task index)
     */
    public String handleUnmark(String arg) {
        if (arg.length() != 1) { // Incorrect argument length
            return "Please input 'unmark [index]'";
        }
        int index;
        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) { // Not a number input
            return "Please input 'unmark [index]'";
        }
        if (index > taskList.getNumTasks()) { // Index exceeds tasks length
            return "Invalid index.";
        }

        // Unmark task
        taskList.unmarkTask(index);

        // Print success msg
        return unmarkedTaskMessage(taskList.getTaskString(index));
    }
    /**
     * Handles the "todo" command to add a new todo task.
     *
     * @param task the task description
     */
    public String handleTodo(String task, TaskStatus status) {
        if (task.isEmpty()) {
            parser.changeState(StateType.TODO_DESCRIPTION);
            return "Enter task description: ";
        }

        switch(status) {
        case UPDATE:
            parser.changeStatus(TaskStatus.ADD);
            parser.resetTempStrings();
            taskList.updateTask(tempIndex, new Todo(task.trim()));
            return updateMessage(taskList.getTaskString(tempIndex));
        case ADD:
            taskList.addTask(task.trim(), TaskType.TODO, "");
            return handleAddedTask();
        }
        return "";
    }

    /**
     * Handles the "event" command to add a new event task.
     *
     * @param description the event description
     * @param startDate   the event start date
     */
    public String handleEvent(String description, String startDate, TaskStatus status) {
        String endDate = "";
        if (description.contains("/to")) { // No start date, end date provided (add bugs)
            String[] temp = parser.parseEventTo(description);
            description = temp[0].trim();
            endDate = temp[1];
        }

        if (description.isEmpty()) { // No echo.task description, start date provided
            if (!startDate.isEmpty()) {
                parser.keepTempString(startDate, 1);
            }
            if (!endDate.isEmpty()) {
                parser.keepTempString(endDate, 2);
            }
            parser.changeState(StateType.EVENT_DESCRIPTION);
            return "Enter task description: ";
        }

        if (startDate.isEmpty()) { // No start date provided (update bug)
            parser.keepTempString(description, 0);
            if (!endDate.isEmpty()) {
                parser.keepTempString(endDate, 2);
            }
            parser.changeState(StateType.EVENT_START);
            return "Start: ";
        }

        if (startDate.contains("/to")) {
            String[] temp = parser.parseEventTo(startDate);
            if (temp[0].isEmpty()) { // No start date provided, only end date
                parser.keepTempString(description, 0);
                parser.keepTempString(temp[1], 2);
                parser.changeState(StateType.EVENT_START);
                return "Start: ";
            }
            startDate = temp[0];
            endDate = temp[1].trim();
        }

        if (endDate.isEmpty()) {
            parser.keepTempString(description, 0);
            parser.keepTempString(startDate, 1);
            parser.changeState(StateType.EVENT_END);
            return "End: ";
        }

        startDate = startDate.trim();
        endDate = endDate.trim();

        switch(status) {
            case UPDATE:
                parser.changeStatus(TaskStatus.ADD);
                parser.resetTempStrings();
                taskList.updateTask(tempIndex, new Event(description, startDate, endDate));
                return updateMessage(taskList.getTaskString(tempIndex));
            case ADD:
                taskList.addTask(description, TaskType.EVENT, startDate + "->" + endDate);
                parser.resetTempStrings();
                return handleAddedTask();
        }
        return "";
    }
    /**
     * Handles the "deadline" command to add a new deadline task.
     *
     * @param description     the task description
     * @param deadlineToParse the deadline date string to be parsed
     */
    public String handleDeadline(String description, String deadlineToParse, TaskStatus status) {
        if (description.isEmpty()) {
            if (!deadlineToParse.isEmpty()) {
                parser.keepTempString(deadlineToParse, 3);
            }
            parser.changeState(StateType.DEADLINE_DESCRIPTION);
            return "Enter task description: ";
        }

        if (deadlineToParse.isEmpty()) {
            parser.keepTempString(description, 0);
            parser.changeState(StateType.DEADLINE_DEADLINE);
            return "Deadline:";
        }

        LocalDate deadline;
        try {
            deadline = parser.parseDate(deadlineToParse);
        } catch (DateTimeParseException e) {
            parser.changeState(StateType.DEADLINE_DEADLINE);
            return "No matching date formats.\n Deadline:";
        }

        switch(status) {
        case UPDATE:
            parser.changeStatus(TaskStatus.ADD);
            parser.resetTempStrings();
            taskList.updateTask(tempIndex, new Deadline(description, deadline));
            return updateMessage(taskList.getTaskString(tempIndex));
        case ADD:
            taskList.addDeadline(description, deadline);
            parser.resetTempStrings();
            return handleAddedTask();
        }
        return "";
    }
    /**
     * Prints a message indicating that a task was successfully added.
     */
    public String handleAddedTask() {
        assert taskList.getNumTasks() > 0: "Task list should have at least one task";
        return addedTaskMessage(taskList.getTaskString(taskList.getNumTasks()), taskList.getNumTasks());
    }
    public String handleUpdate(String indexString, String info) {
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            return "Invalid index.";
        }

        if (index > taskList.getNumTasks()) {
            return "Invalid index";
        }

        tempIndex = index;

        TaskType taskType = taskList.getTaskType(index);
        parser.keepTempStrings(taskList.getTempTaskStrings(index));
        switch (taskType) {
        case TODO:
            parser.changeStatus(TaskStatus.UPDATE);
            return parser.handleInput("Todo " + info);
        case DEADLINE:
            parser.changeStatus(TaskStatus.UPDATE);
            return parser.handleInput("Deadline " + info);
        case EVENT:
            parser.changeStatus(TaskStatus.UPDATE);
            return parser.handleInput("Event " + info);
        default:
            return "";
        }
    }
    /**
     * Handles the "delete" command to remove a task from the list.
     *
     * @param arg the argument provided with the "delete" command (task index)
     */
    public String handleDelete(String arg) {
        if (arg.isEmpty()) {
            return "Please input 'delete [item index]'";
        }
        int index;

        try {
            index = Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            return "Please input 'delete [item index]'";
        }

        if (index > taskList.getNumTasks()) {
            return "Invalid index.";
        }

        String deleteMsg = deleteMessage(taskList.getTaskString(index));

        taskList.deleteTask(index);
        return deleteMsg;
    }

    public String handleEmpty() {
        return emptyMessage();
    }

    private String updateMessage(String task) {
        return "Nice! I've edited this task:\n" +
                task;
    }
    public String welcomeMessage() {
        return "Hello! I'm Echo!\n" +
                "What can I do for you?";
    }
    private String markedTaskMessage(String task) {
        return "Nice! I've marked this task as done:\n" +
               task;
    }
    private String unmarkedTaskMessage(String task) {
        return "Ok, I've marked this task as not done yet:\n" +
                task;
    }

    private String foundMessage(String foundTasks) {
        return String.format(
                "Here are the matching tasks in your list:\n" +
                        "%s",
                foundTasks);
    }
    private String addedTaskMessage(String task, int numTasks) {
        return String.format(
                    "Got it. I've added this task:\n" +
                    task +
                    "Now you have %d task" +
                    (numTasks == 1 ? "" : "s") +
                    " in the list.\n",
                    numTasks
                );
    }
    private String emptyMessage() {
        return "OOPS!!! I'm sorry, but your input cannot be empty :(";
    }
    private String unknownMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
    private String listMessage(String tasks) {
        return "Here are the tasks in your list:\n" +
                tasks;
    }
    private String deleteMessage(String task) {
        return "Noted. I've removed this task:\n" +
                task;
    }
    private String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }
    /**
     * Prints an error message indicating that there was an issue loading from the file.
     */
    public String loadingErrorMessage() {
        return "Oh no! Error loading from file!";
    }

}
