package main.java.angel;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents the Angel chatbot application.
 * Handles user commands, task management, and data persistence.
 */
public class Angel {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String FILE_PATH = "./data/Angel.txt";
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an Angel instance and initializes the task list.
     * Loads tasks from the storage file if it exists.
     */
    public Angel() {
        taskList = new TaskList(FILE_PATH);
        ui = new Ui();
    }

    /**
     * Runs the chatbot application.
     * Continuously reads user input, processes commands, and updates task list.
     */
    public void run() {
        ui.print("Hello! I'm Angel\nWhat can I do for you?");

        while (true) {
            try {
                String input = ui.readInput();
                Parser.Command command = Parser.parseCommand(input);
                handleCommand(command);
            } catch (InvalidCommandException e) {
                ui.printError(e.getMessage());
            } catch (TaskNotFoundException e) {
                ui.printError(e.getMessage() + " Please check the task number and try again.");
            } catch (AngelException e) {
                ui.printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Processes the given command and takes appropriate action based on the command type.
     *
     * @param command The parsed command to be handled.
     * @throws InvalidCommandException If the command is invalid.
     * @throws AngelException If there is an issue while processing the command.
     * @throws TaskNotFoundException If the task related to the command is not found.
     */
    private void handleCommand(Parser.Command command)
            throws InvalidCommandException, AngelException, TaskNotFoundException {
        switch (command.getType()) {
        case "bye":
            handleByeCommand();
            break;
        case "list":
            handleListCommand();
            break;
        case "todo":
            handleTodoCommand(command);
            break;
        case "deadline":
            handleDeadlineCommand(command);
            break;
        case "event":
            handleEventCommand(command);
            break;
        case "mark":
            handleMarkCommand(command);
            break;
        case "unmark":
            handleUnmarkCommand(command);
            break;
        case "delete":
            handleDeleteCommand(command);
            break;
        case "find":
            handleFindCommand(command);
            break;
        default:
            throw new InvalidCommandException("Unknown command: " + command.getType()
                    + ". Please enter a valid command.");
        }
    }

    /**
     * Handles the "bye" command, which exits the application.
     */
    private void handleByeCommand() {
        ui.print("Bye. Hope to see you again soon!");
        ui.close();
    }

    /**
     * Handles the "list" command, which prints all tasks in the task list.
     */
    private void handleListCommand() {
        ui.printTasks(taskList);
    }

    /**
     * Handles the "todo" command, which adds a ToDo task.
     *
     * @param command The command containing the ToDo task details.
     * @throws AngelException If an error occurs while adding the task.
     */
    private void handleTodoCommand(Parser.Command command) throws AngelException {
        String todoDescription = (String) command.getDetails();
        taskList.addTask(new ToDo(todoDescription));
        ui.print("Got it. I've added this task:\n  " + todoDescription);
    }

    /**
     * Handles the "deadline" command, which adds a Deadline task.
     *
     * @param command The command containing the Deadline task details.
     * @throws AngelException If an error occurs while adding the task.
     */
    private void handleDeadlineCommand(Parser.Command command) throws AngelException {
        Parser.DeadlineDetail deadlineDetail = (Parser.DeadlineDetail) command.getDetails();
        taskList.addTask(new Deadline(deadlineDetail.getDescription(), deadlineDetail.getDeadline()));
        ui.print("Got it. I've added this task:\n  " + deadlineDetail.getDescription()
                + " by: " + deadlineDetail.getDeadline().format(INPUT_FORMAT));
    }

    /**
     * Handles the "event" command, which adds an Event task.
     *
     * @param command The command containing the Event task details.
     * @throws AngelException If an error occurs while adding the task.
     */
    private void handleEventCommand(Parser.Command command) throws AngelException {
        Parser.EventDetail eventDetail = (Parser.EventDetail) command.getDetails();
        taskList.addTask(new Event(eventDetail.getDescription(), eventDetail.getFrom(), eventDetail.getTo()));
        ui.print("Got it. I've added this task:\n  " + eventDetail.getDescription()
                + " from: " + eventDetail.getFrom().format(INPUT_FORMAT) + " to: "
                + eventDetail.getTo().format(INPUT_FORMAT));
    }

    /**
     * Handles the "mark" command, which marks a task as completed.
     *
     * @param command The command containing the task index to mark.
     * @throws AngelException If an error occurs while marking the task.
     * @throws TaskNotFoundException If the task to mark is not found.
     */
    private void handleMarkCommand(Parser.Command command) throws AngelException, TaskNotFoundException {
        int markIndex = (int) command.getDetails();
        taskList.markTask(markIndex);
        ui.print("Nice! I've marked this task as done:\n  " + taskList.listTasks().get(markIndex));
    }

    /**
     * Handles the "unmark" command, which marks a task as not completed.
     *
     * @param command The command containing the task index to unmark.
     * @throws AngelException If an error occurs while unmarking the task.
     * @throws TaskNotFoundException If the task to unmark is not found.
     */
    private void handleUnmarkCommand(Parser.Command command) throws AngelException, TaskNotFoundException {
        int unmarkIndex = (int) command.getDetails();
        taskList.unmarkTask(unmarkIndex);
        ui.print("OK, I've marked this task as not done yet:\n  " + taskList.listTasks().get(unmarkIndex));
    }

    /**
     * Handles the "delete" command, which deletes a task.
     *
     * @param command The command containing the task index to delete.
     * @throws AngelException If an error occurs while deleting the task.
     * @throws TaskNotFoundException If the task to delete is not found.
     */
    private void handleDeleteCommand(Parser.Command command) throws AngelException, TaskNotFoundException {
        int deleteIndex = (int) command.getDetails();
        taskList.deleteTask(deleteIndex);
        ui.print("Noted. I've removed this task:\n  " + taskList.listTasks().get(deleteIndex));
    }

    /**
     * Handles the "find" command, which searches for tasks with a specific keyword.
     *
     * @param command The command containing the keyword to search.
     */
    private void handleFindCommand(Parser.Command command) {
        String keyword = (String) command.getDetails();
        List<Task> foundTasks = taskList.findTasks(keyword);
        if (foundTasks.isEmpty()) {
            ui.print("No matching tasks found.");
        } else {
            ui.print("Here are the matching tasks in your list:");
            ui.printFoundTasks(foundTasks);
        }
    }

    /**
     * Processes user input and returns the corresponding response.
     *
     * @param input The user input command.
     * @return The response message.
     */
    public String getResponse(String input) {
        try {
            Parser.Command command = Parser.parseCommand(input);
            return handleCommandResponse(command);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (TaskNotFoundException e) {
            return e.getMessage() + " Please check the task number and try again.";
        } catch (AngelException e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    /**
     * Processes the given command and returns the appropriate response message.
     *
     * @param command The parsed command to be handled.
     * @return The response message.
     * @throws AngelException If there is an issue while processing the command.
     * @throws TaskNotFoundException If the task related to the command is not found.
     * @throws InvalidCommandException If the command is invalid.
     */
    private String handleCommandResponse(Parser.Command command)
            throws AngelException, TaskNotFoundException, InvalidCommandException {
        switch (command.getType()) {
        case "bye":
            return handleByeResponse();
        case "list":
            return handleListResponse();
        case "todo":
            return handleTodoResponse(command);
        case "deadline":
            return handleDeadlineResponse(command);
        case "event":
            return handleEventResponse(command);
        case "mark":
            return handleMarkResponse(command);
        case "unmark":
            return handleUnmarkResponse(command);
        case "delete":
            return handleDeleteResponse(command);
        case "find":
            return handleFindResponse(command);
        default:
            throw new InvalidCommandException("Unknown command: " + command.getType()
                    + ". Please enter a valid command.");
        }
    }

    /**
     * Handles the "bye" command and returns the response for ending the session.
     *
     * @return A string response indicating the chatbot session is ending.
     */
    private String handleByeResponse() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the "list" command and returns the formatted list of tasks.
     *
     * @return A string containing all tasks in the task list, formatted for display.
     */
    private String handleListResponse() {
        return formatTasks(taskList.listTasks());
    }

    /**
     * Handles the "todo" command by adding a ToDo task and returns the response message.
     *
     * @param command The command containing the ToDo task details.
     * @return A string response confirming the task has been added.
     * @throws AngelException If an error occurs while adding the task.
     */
    private String handleTodoResponse(Parser.Command command) throws AngelException {
        String todoDescription = (String) command.getDetails();
        taskList.addTask(new ToDo(todoDescription));
        return "Got it. I've added this task:\n  " + todoDescription;
    }

    /**
     * Handles the "deadline" command by adding a Deadline task and returns the response message.
     *
     * @param command The command containing the Deadline task details.
     * @return A string response confirming the task has been added, along with the deadline.
     * @throws AngelException If an error occurs while adding the task.
     */
    private String handleDeadlineResponse(Parser.Command command) throws AngelException {
        Parser.DeadlineDetail deadlineDetail = (Parser.DeadlineDetail) command.getDetails();
        taskList.addTask(new Deadline(deadlineDetail.getDescription(), deadlineDetail.getDeadline()));
        return "Got it. I've added this task:\n  " + deadlineDetail.getDescription()
                + " by: " + deadlineDetail.getDeadline().format(INPUT_FORMAT);
    }

    /**
     * Handles the "event" command by adding an Event task and returns the response message.
     *
     * @param command The command containing the Event task details.
     * @return A string response confirming the task has been added, along with the start and end times.
     * @throws AngelException If an error occurs while adding the task.
     */
    private String handleEventResponse(Parser.Command command) throws AngelException {
        Parser.EventDetail eventDetail = (Parser.EventDetail) command.getDetails();
        taskList.addTask(new Event(eventDetail.getDescription(), eventDetail.getFrom(), eventDetail.getTo()));
        return "Got it. I've added this task:\n  " + eventDetail.getDescription()
                + " from: " + eventDetail.getFrom().format(INPUT_FORMAT) + " to: "
                + eventDetail.getTo().format(INPUT_FORMAT);
    }

    /**
     * Handles the "mark" command by marking a task as completed and returns the response message.
     *
     * @param command The command containing the task index to mark as completed.
     * @return A string response confirming the task has been marked as completed.
     * @throws AngelException If an error occurs while marking the task.
     * @throws TaskNotFoundException If the task to be marked is not found.
     */
    private String handleMarkResponse(Parser.Command command) throws AngelException, TaskNotFoundException {
        int markIndex = (int) command.getDetails();
        taskList.markTask(markIndex);
        return "Nice! I've marked this task as done:\n  " + taskList.listTasks().get(markIndex);
    }

    /**
     * Handles the "unmark" command by marking a task as not completed and returns the response message.
     *
     * @param command The command containing the task index to unmark.
     * @return A string response confirming the task has been marked as not completed.
     * @throws AngelException If an error occurs while unmarking the task.
     * @throws TaskNotFoundException If the task to be unmarked is not found.
     */
    private String handleUnmarkResponse(Parser.Command command) throws AngelException, TaskNotFoundException {
        int unmarkIndex = (int) command.getDetails();
        taskList.unmarkTask(unmarkIndex);
        return "OK, I've marked this task as not done yet:\n  " + taskList.listTasks().get(unmarkIndex);
    }

    /**
     * Handles the "delete" command by deleting a task and returns the response message.
     *
     * @param command The command containing the task index to delete.
     * @return A string response confirming the task has been deleted.
     * @throws AngelException If an error occurs while deleting the task.
     * @throws TaskNotFoundException If the task to be deleted is not found.
     */
    private String handleDeleteResponse(Parser.Command command) throws AngelException, TaskNotFoundException {
        int deleteIndex = (int) command.getDetails();
        String deletedTask = taskList.listTasks().get(deleteIndex).toString();
        taskList.deleteTask(deleteIndex);
        return "Noted. I've removed this task:\n  " + deletedTask;
    }

    /**
     * Handles the "find" command by searching for tasks that match a keyword and returns the response message.
     *
     * @param command The command containing the keyword to search.
     * @return A string response listing the tasks that match the keyword, or a message indicating no matches found.
     */
    private String handleFindResponse(Parser.Command command) {
        String keyword = (String) command.getDetails();
        List<Task> foundTasks = taskList.findTasks(keyword);
        return foundTasks.isEmpty() ? "No matching tasks found."
                : "Here are the matching tasks in your list:\n" + formatTasks(foundTasks);
    }

    /**
     * Formats a list of tasks into a string for display.
     *
     * @param tasks The list of tasks to format.
     * @return The formatted string of tasks.
     */
    private String formatTasks(List<Task> tasks) {
        StringBuilder formattedTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            formattedTasks.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return formattedTasks.toString().trim();
    }

    public static void main(String[] args) {
        new Angel().run();
    }
}
