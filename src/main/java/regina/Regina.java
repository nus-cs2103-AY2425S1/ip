package regina;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import dateandtime.ReginaDateAndTime;
import errorhandling.ReginaException;
import file.Storage;
import gui.MainWindow;
import tasks.DeadlinesTask;
import tasks.EventsTask;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDosTask;


/**
 * The regina.Regina class represents a chatbot designed to help users track their tasks and activities.
 * It provides functionalities to add, mark, unmark, delete, and list tasks.
 */
public class Regina {
    private static final String NAME = "regina.Regina";

    private final Parser parser;
    private final Marker marker;
    private final Ui ui;
    private TaskList listOfTasks;
    private MainWindow mainWindow;

    /**
     * Constructs a regina.Regina instance containing an empty task list and initializes the marker.
     */
    public Regina() {
        this.ui = new Ui();
        try {
            listOfTasks = Storage.readSavedData();
        } catch (FileNotFoundException | ReginaException e) {
            ui.printMessage("Saved data file is missing....");
        }
        this.marker = new Marker(listOfTasks);
        this.parser = new Parser();
    }

    public TaskList getListOfTasks() {
        return this.listOfTasks;
    }

    public void setMainWindow(MainWindow checkboxController) {
        this.mainWindow = checkboxController;
    }

    /**
     * Greets the user and provides instructions on how to interact with the chatbot.
     */
    public String greet() {
        return ui.greet(NAME);
    }

    /**
     * Validates whether a given task type is recognized.
     *
     * @param type The task type to check.
     * @return True if the task type is valid; false otherwise.
     */
    private boolean isValidTaskType(String type) {
        return type.equals("todo") || type.equals("deadline") || type.equals("event");
    }

    /**
     * Deletes all the tasks in the list
     */
    public String clearTaskList() {
        if (this.listOfTasks.isEmpty()) {
            return ui.printMessage("Nothing left to clear lah!");
        } else {
            this.listOfTasks.clear();
            saveFile();
            return ui.printMessage("Cleared all tasks!");
        }
    }

    /**
     * Validates if the command for marking or unmarking contains a number.
     *
     * @param parts The parts of the input command split into an array.
     * @throws ReginaException If the command does not properly contain a task number.
     */
    public boolean haveNumber(String[] parts) throws ReginaException {
        if (parts.length < 2) {
            throw new ReginaException("Which task you referring to lah!");
        }
        if (parts.length > 2) {
            throw new ReginaException("Follow the proper format please!\nType 'help' for reference.");
        }
        try {
            Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Lists all tasks occurring at the specified date and time.
     * <p>
     * This method takes a date and time input, creates an instance of ReginaDateAndTime,
     * and retrieves all tasks from the task list that occur on that specific date and time.
     * The resulting list of tasks is then displayed to the user via the regina.Ui class.
     *
     * @param dateAndTime A string representing the date and time to check for occurring tasks.
     * @throws ReginaException If the dateAndTime format is invalid or if an error occurs while
     *                         retrieving tasks.
     */
    public String occurringOn(String dateAndTime) throws ReginaException {
        ReginaDateAndTime occurringInstance = new ReginaDateAndTime(dateAndTime);
        TaskList tempList = this.listOfTasks.findTasksOccurringOn(occurringInstance);
        return ui.printMessage(tempList.toString());
    }

    /**
     * Searches for tasks in the list that contain the specified keyword and displays them to the user.
     *
     * <p>This method utilizes the {@link TaskList#findTasksWithKeyword(String)} method
     * to retrieve a list of tasks matching the given keyword. It then prints the result
     * to the user via the {@link Ui} instance. The method returns the filtered list of tasks.
     *
     * @param keyword The keyword to search for within task descriptions.
     *                The search is case-sensitive, and only tasks that contain
     *                the keyword in their string representation will be included in the result.
     * @return A {@link TaskList} containing tasks that match the keyword.
     */
    public TaskList find(String keyword) {
        TaskList tempList = this.listOfTasks.findTasksWithKeyword(keyword);
        ui.printMessage(tempList.toString());
        return tempList;
    }

    /**
     * Adds a new task based on the input command.
     *
     * @param input The user input string containing the task details.
     * @throws ReginaException If the input format is incorrect or invalid.
     */
    public String add(String input) throws ReginaException {
        String[] parts = input.split(" "); // Split input by spaces
        String taskType = parts[0];
        boolean taskDescriptionPresent = parts.length >= 2;
        if (!taskDescriptionPresent && isValidTaskType(taskType)) {
            String message = String.format("OOPS!!! Add your %s task description lah!", taskType);
            throw new ReginaException(message);
        }
        Task task;
        switch (taskType) {
        case "todo":
            String todoDescription = input.substring(5).trim();
            task = new ToDosTask(todoDescription);
            break;
        case "deadline":
            String[] deadlineParts = input.substring(9).trim().split(" /by ");
            // check if deadline was added for this task
            if (deadlineParts.length < 2) {
                throw new ReginaException("""
                        So....when's the deadline for this task?
                        Follow this format for the date and time please
                        (e.g. 01/10/2024 1700)""");
            }
            String deadlineDescription = deadlineParts[0];
            String deadline = deadlineParts[1];
            task = new DeadlinesTask(deadlineDescription, deadline);
            break;
        case "event":
            String[] eventParts = getEventStringSubparts(input);
            String eventDescription = eventParts[0];
            if (!eventParts[1].contains(" ")) {
                throw new ReginaException("NEITHER the start-time OR end-time can be left blank!\n"
                        + "Type 'help' for reference.");
            }
            String startTime = eventParts[1].substring(5).trim(); // take the substring after "from"
            String endTime = eventParts[2].substring(3).trim(); // take the substring after "to"
            task = new EventsTask(eventDescription, startTime, endTime);
            break;
        default:
            throw new ReginaException("Unknown task type. Use: todo, deadline, or event.");
        }
        listOfTasks.add(task);
        saveFile();
        int noOfTasks = listOfTasks.size();
        return ui.printMessage(String.format(
                "Got it. I've added this task: \n  %s\nNow you have %d task%s in the list.\nJiayous!\n",
                task,
                noOfTasks,
                noOfTasks > 1 ? "s" : ""));
    }

    private static String[] getEventStringSubparts(String input) throws ReginaException {
        String[] eventParts = input.substring(6).trim().split(" /");
        int length = eventParts.length;
        // check if there is the expected number of sub-parts
        if (length != 3) {
            throw new ReginaException("You need to add BOTH the start-time AND the end-time!\n"
                    + "Type 'help' for reference.");
        }
        // if the correct number of sub-parts then check if format is correct
        if (!(eventParts[1].contains("from") && eventParts[2].contains("to"))) {
            throw new ReginaException("OI! Use the correct format lah!\n"
                    + "Type 'help' for reference.");
        }
        return eventParts;
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws ReginaException If the index is out of bounds or if there are no tasks to delete.
     */
    public String delete(int index) throws ReginaException {
        if (listOfTasks.isEmpty()) {
            throw new ReginaException("No more tasks to delete alr lah!");
        }
        if (index < 0) {
            throw new ReginaException("Choose index greater than 1 please!");
        }
        int taskCount = listOfTasks.size();
        if (index >= taskCount) {
            String message = String.format("You cannot count ah! There %s only %d task%s!",
                    taskCount > 1 ? "are" : "is",
                    taskCount,
                    taskCount > 1 ? "s" : "");
            throw new ReginaException(message);
        }
        Task task = listOfTasks.get(index);
        listOfTasks.remove(index);
        saveFile();
        taskCount = listOfTasks.size(); // update the number of tasks
        return ui.printMessage(String.format("Wah shiok!\nCan forget about %s liao!\nList now has %d task%s!\n",
                task.toString(),
                taskCount,
                taskCount > 1 ? "s" : ""));
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @throws ReginaException If there are no tasks to display.
     */
    public String list() throws ReginaException {
        int length = listOfTasks.size();
        if (length == 0) {
            throw new ReginaException("HEHE no tasks for now!");
        }
        StringBuilder inputList = new StringBuilder();
        for (int i = 0; i < length; i++) {
            inputList.append(i + 1)
                    .append(".")
                    .append(listOfTasks.get(i).toString())
                    .append("\n"); // get task
        }
        return ui.printMessage(inputList.toString());
    }

    /**
     * Marks a task based on the given index.
     *
     * @param index The index of the task to mark as done.
     * @throws ReginaException If the index is out of bounds.
     */
    public String mark(int index) throws ReginaException {
        assert index >= 0 && index < listOfTasks.size() : "Out of bounds index for mark: " + index;
        this.marker.mark(index);
        Task task = this.listOfTasks.get(index);
        saveFile();
        return ui.printMessage(String.format("YAY! This task finish liao!\n%s\n", task.toString()));
    }

    /**
     * Unmarks a task based on the given index.
     *
     * @param index The index of the task to unmark.
     * @throws ReginaException If the index is out of bounds.
     */
    public String unmark(int index) throws ReginaException {
        assert index >= 0 && index < listOfTasks.size() : "Out of bounds index for mark: " + index;
        this.marker.unmark(index);
        Task task = this.listOfTasks.get(index);
        saveFile();
        return ui.printMessage(String.format("Hais! Need to do this task again!:\n%s\n", task.toString()));
    }

    /**
     * Saves the current list of tasks to a file.
     * This method attempts to persist the task data by calling the FileSaver's saveData method.
     * If an IOException occurs during this process, an error message will be displayed to inform the user.
     */
    public void saveFile() {
        try {
            Storage.saveData(listOfTasks.toSavedFormatting());
        } catch (IOException e) {
            ui.printMessage("******Error in syncing data******");
        }
    }

    /**
     * Processes user input and generates an appropriate response based on the command.
     *
     * <p>This method parses the input string for commands and executes the corresponding
     * actions based on the command type detected. It interacts with the Regina model
     * to update or retrieve information about tasks. If the command is one that requires
     * a task number, it validates the input and executes the appropriate method.</p>
     *
     * @param input the command input from the user as a String.
     * @return a String representing the response to the user, which may include error messages
     *         or confirmation of actions performed, such as marking, unmarking, or adding tasks.
     */
    public String getResponse(String input) {
        try {
            Optional<CommandData> commandData = parser.parse(input);
            if (commandData.isPresent()) {
                CommandData data = commandData.get();
                switch (data.getCommandType()) {
                case "help":
                    return this.ui.help(); // Create a method to build the help response
                case "now":
                    return ReginaDateAndTime.getCurrentDateAndTime(); // Return current date and time as a string
                case "clear":
                    return clearTaskList();
                case "list":
                    return list(); // Create a method that returns the list of tasks as a string
                case "occurring":
                    return occurringOn(data.getRawInput().substring(10)); // Convert to string if necessary
                case "find":
                    return find(data.getRawInput().substring(5)).toString(); // Convert to string if necessary
                case "mark":
                case "unmark":
                case "delete":
                    String[] parts = data.getRawInput().split(" "); // Split raw input to get parts
                    if (haveNumber(parts)) { // Validate that there's a task number
                        return getReplyForNumberedCommand(parts, data);
                    }
                default:
                    return add(input); // Add a new task
                }
            }
        } catch (ReginaException e) {
            return e.getMessage(); // Return the error messages
        }
        return "I'm not sure how to respond to that."; // Default response
    }

    private String getReplyForNumberedCommand(String[] parts, CommandData data) throws ReginaException {
        int index = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
        return switch (data.getCommandType()) {
        case "mark" -> mark(index);
        case "unmark" -> unmark(index);
        case "delete" -> delete(index);
        default -> this.ui.printMessage("Give a proper command lah!");
        };
    }


    public static void main(String[] args) {
        Regina regina = new Regina(); // Create an instance of the regina.Regina chatbot
        regina.greet(); // Greet the user
        String userInput;

        while (true) {
            try {
                userInput = regina.ui.readInput(); // Use the ui to read user input
                if (userInput.equals("bye")) {
                    regina.ui.exit(); // Exit the program
                    break;
                }

                Optional<CommandData> commandData = regina.parser.parse(userInput); // Call the parser
                if (commandData.isPresent()) {
                    // Extract command data
                    CommandData data = commandData.get();

                    switch (data.getCommandType()) {
                    case "help":
                        regina.ui.help(); // Provide help information
                        break;
                    case "now":
                        regina.ui.printMessage(ReginaDateAndTime.getCurrentDateAndTime());
                        break;
                    case "clear":
                        regina.clearTaskList(); // Clear all tasks
                        break;
                    case "list":
                        regina.list(); // List current tasks
                        break;
                    case "occurring":
                        // Get tasks occurring at a specific date
                        regina.occurringOn(data.getRawInput().substring(10));
                        break;
                    case "find":
                        regina.find(data.getRawInput().substring(5));
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        String[] parts = data.getRawInput().split(" "); // Split raw input to get parts
                        if (regina.haveNumber(parts)) { // Validate that there's a task number
                            int index = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
                            switch (data.getCommandType()) {
                            case "mark":
                                regina.mark(index);
                                break;
                            case "unmark":
                                regina.unmark(index);
                                break;
                            case "delete":
                                regina.delete(index);
                                break;
                            default:
                                regina.ui.printMessage("Give a proper command lah!");
                            }
                        }
                        break;
                    default:
                        regina.add(data.getRawInput()); // Handle adding tasks
                    }
                }
            } catch (ReginaException e) {
                // Display error messages to users
                regina.ui.printError(e.getMessage());
            }
        }
    }
}
