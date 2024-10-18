package velma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import velma.exception.VelmaException;
import velma.task.After;
import velma.task.Deadline;
import velma.task.Event;
import velma.task.Task;
import velma.task.TaskList;
import velma.task.Todo;


public class Velma {
    private static final String FILE_PATH = "./data/velma.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a new instance of the Velma class.
     * Sets up the UI, storage, and task list.
     * If loading tasks from storage fails, an empty task list is created.
     *
     * @param filePath The file path to the storage file.
     */
    public Velma(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Prints a line separator.
     */
    public void printLine() {
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command command = getCommand(input);
        StringBuilder response = new StringBuilder();
        try {
            switch (command) {
            case TODO:
                handleTodoCommand(input, response);
                break;

            case AFTER:
                handleAfterCommand(input, response);
                break;

            case DEADLINE:
                handleDeadlineCommand(input, response);
                break;

            case EVENT:
                handleEventCommand(input, response);
                break;

            case LIST:
                handleListCommand(input, response);
                break;

            case MARK:
            case UNMARK:
                handleMarkCommand(input, response);
                break;

            case DELETE:
                handleDeleteCommand(input, response);
                break;

            case FIND:
                handleFindCommand(input, response);
                break;


            case UNKNOWN:
            default:
                throw new VelmaException("Sorry boss! What are you talking about?");
            }
        } catch (VelmaException e) {
            response.append(e.getMessage());
        }
        return response.toString();
    }


    /**
     * Checks user input to determine which command to carry out.
     *
     * @param input The command specified to the chatbot.
     */
    public static Command getCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.contains("after")) {
            return Command.AFTER;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("list")) {
            return Command.LIST;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.startsWith("find")) {
            return Command.FIND;
        } else {
            return Command.UNKNOWN;
        }
    }

    /**
     * Handles the todo command.
     * @param input
     * @param response
     * @throws VelmaException
     */
    private void handleTodoCommand(String input, StringBuilder response) throws VelmaException {
        String todoDescription = input.replaceFirst("todo\\s*", "").trim();
        if (todoDescription.isEmpty()) {
            throw new VelmaException("Sorry boss! Where is your todo description?");
        }
        Task newTodo = new Todo(todoDescription);
        tasks.addTask(newTodo);
        response.append("Got it. I've added this task:\n")
                .append(newTodo)
                .append("\nNow you have ")
                .append(tasks.getSize())
                .append(" tasks in the list.");
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the after command.
     * @param input
     * @param response
     * @throws VelmaException
     */
    private void handleAfterCommand(String input, StringBuilder response) throws VelmaException {
        String[] afterParts = input.split("after", 2);
        if (afterParts.length < 2) {
            throw new VelmaException("Sorry boss! When does your task needs to complete?");
        }
        String afterDescription = afterParts[0];
        Task newAfter = new After(afterDescription, afterParts[1]);
        tasks.addTask(newAfter);
        response.append("Got it. I've added this task:\n")
                .append(newAfter)
                .append("\nNow you have ")
                .append(tasks.getSize())
                .append(" tasks in the list.");
        storage.save(tasks.getTasks());
    }

    /**
     * Handles the deadline command.
     * @param input
     * @param response
     * @throws VelmaException
     */
    private void handleDeadlineCommand(String input, StringBuilder response) throws VelmaException {
        String[] parts = input.replaceFirst("deadline\\s*", "").split(" /by ");
        if (parts.length < 2) {
            throw new VelmaException("Sorry boss! Your deadline task needs task description and deadline!");
        }
        String description = parts[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(parts[1], formatter);
        } catch (DateTimeParseException e) {
            throw new VelmaException("Sorry boss! The date format is incorrect. Please use d/M/yyyy HHmm.");
        }

        Task newDeadline = new Deadline(description, deadline);
        tasks.addTask(newDeadline);
        response.append("Got it. I've added this task:\n")
                .append(newDeadline)
                .append("\nNow you have ")
                .append(tasks.getSize())
                .append(" tasks in the list.");
        storage.save(tasks.getTasks());
    }

    private void handleEventCommand(String input, StringBuilder response) throws VelmaException {
        String[] parts = input.replaceFirst("event\\s+", "").split(" /from | /to ");
        if (parts.length < 3) {
            throw new VelmaException("Sorry boss! An event needs a valid start time "
                    + "and end time! Please use /from HHmm /to HHmm");
        }
        String description = parts[0];
        String startTime = parts[1];
        String endTime = parts[2];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            LocalTime start = LocalTime.parse(startTime, formatter);
            LocalTime end = LocalTime.parse(endTime, formatter);

            if (start.isAfter(end)) {
                throw new VelmaException("Sorry boss! The start time cannot be after the end time.");
            }

            Task newEvent = new Event(description, startTime, endTime);
            tasks.addTask(newEvent);
            response.append("Got it. I've added this task:\n")
                    .append(newEvent)
                    .append("\nNow you have ")
                    .append(tasks.getSize())
                    .append(" tasks in the list.");
            storage.save(tasks.getTasks());
        } catch (DateTimeParseException e) {
            throw new VelmaException("Sorry boss! The time format is incorrect. Please use HHmm.");

        }

    }

    /**
     * Handles the list command.
     * @param input
     * @param response
     * @throws VelmaException
     */
    private void handleListCommand(String input, StringBuilder response) throws VelmaException {
        String[] parts = input.split(" ");
        if (parts.length == 2) {
            String dateInString = parts[1];
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            try {
                date = LocalDate.parse(dateInString, dateFormatter);
            } catch (DateTimeParseException e) {
                throw new VelmaException("Sorry boss! The date format is incorrect. Please use yyyy-MM-dd.");
            }
            response.append("Here are the tasks on ")
                    .append(date)
                    .append(":\n")
                    .append(ui.showTasksOnDate(tasks.getTasks(), date));
        } else {
            response.append(ui.showAllTasks(tasks.getTasks()));
        }
    }

    /**
     * Handles the mark command.
     * @param input
     * @param response
     * @throws VelmaException
     */
    private void handleMarkCommand(String input, StringBuilder response) throws VelmaException {
        Command command = getCommand(input);
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new VelmaException("Sorry boss! Please specify which task.");
        }
        try {
            Integer taskIndex = Integer.parseInt(parts[1]) - 1;

            if (taskIndex < 1 || taskIndex > tasks.getSize()) {
                throw new VelmaException("Sorry boss! Please specify a valid task number.");
            }

            Task task = tasks.getTask(taskIndex);
            task.changeIsDoneStatus();

            if (command == Command.MARK) {
                response.append("Nice! I have marked this task as done:\n");
            } else {
                response.append("OK! I have marked this task as not done yet:\n");
            }

            response.append(task);
            storage.save(tasks.getTasks());
        } catch (NumberFormatException e) {
            throw new VelmaException("Sorry boss! Please specify a valid task number.");
        }
    }

    /**
     * Handles the delete command.
     * @param input
     * @param response
     * @throws VelmaException
     */
    private void handleDeleteCommand(String input, StringBuilder response) throws VelmaException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new VelmaException("Sorry boss! Please specify which task to delete.");
        }

        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;

            if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
                throw new VelmaException("Sorry boss! Please specify a valid task number.");
            }

            Task taskToDelete = tasks.getTask(taskIndex);
            tasks.deleteTask(taskIndex);
            response.append("Noted. I've removed this task:\n")
                    .append(taskToDelete)
                    .append("\nNow you have ")
                    .append(tasks.getSize())
                    .append(" tasks in the list.");
            storage.save(tasks.getTasks());
        } catch (NumberFormatException e) {
            throw new VelmaException("Sorry boss! Please specify a valid task number.");
        }

    }

    /**
     * Handles the find command
     * @param input
     * @param response
     * @throws VelmaException
     */
    private void handleFindCommand(String input, StringBuilder response) throws VelmaException {
        String keyword = input.replaceFirst("find\\s*", "");
        ArrayList<Task> foundTasks = tasks.findTasks(keyword);
        response.append("Here are the matching tasks in your list:\n")
                .append(ui.showFoundTasks(foundTasks));
    }


}
