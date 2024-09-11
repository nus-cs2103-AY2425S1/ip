package easton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

import easton.exception.DateTimeFormatException;
import easton.exception.EmptyDescriptionException;
import easton.exception.InvalidFormatException;
import easton.exception.InvalidIndexException;
import easton.model.Deadline;
import easton.model.Event;
import easton.model.Task;
import easton.model.ToDo;
import easton.view.Ui;

/**
 * Represents the chatbot, easton.
 */
public class Easton {

    public static final String EXIT_RESPONSE = "Bye. Hope to see you again soon!";
    public static final String INVALID_RESPONSE = "I'm sorry, but I'm not programmed to do this :/";
    public static final String LIST_HEADER_RESPONSE = "Here are the tasks in your list:";
    public static final String MARK_HEADER_RESPONSE = "Nice! I've marked this task as done:";
    public static final String UNMARK_HEADER_RESPONSE = "OK, I've marked this task as not done yet:";
    private ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;

    /**
     * Constructs an instance of the chatbot.
     *
     * @param fileName Name of the file storing the records.
     */
    public Easton(String fileName) {
        try {
            storage = new Storage(fileName);
        } catch (IOException e) {
            Ui.displayToConsole("Cannot connect to the storage.");
        }

        tasks = retrieveTasks();
    }

    /**
     * Executes the chatbot to run.
     */
    public void run() {
        Ui.welcome();

        boolean isFinished = false;
        Scanner scanner = new Scanner(System.in);
        while (!isFinished) {

            String input = scanner.nextLine();
            Action action = getActionFromInput(input);

            boolean isNotList = action != Action.LIST;
            boolean isNotBye = action != Action.BYE;
            boolean isNotInvalid = action != Action.INVALID;
            String body = "";
            try {
                if (isNotList && isNotBye && isNotInvalid) {
                    body = getBodyFromInput(input);
                }
                Ui.displayToConsole(performAction(action, body));
            } catch (EmptyDescriptionException e) {
                Ui.displayToConsole(e.getMessage());
            }

            isFinished = !isNotBye;

            Ui.divider();
        }
    }


    /**
     * Executes the program to start.
     * @param args Environment arguments.
     */
    public static void main(String[] args) {
        new Easton("task.csv").run();
    }

    /**
     * Changes the status of a given task.
     *
     * @param body Body from the prompt.
     * @param isDone Is the task done.
     * @param message Message displayed to the user interface.
     * @return Response to be displayed to the user interface.
     */
    public String changeTaskStatus(String body, boolean isDone, String message) {
        StringBuilder response = new StringBuilder();
        try {
            int index = getIndexFromBody(body);
            Task task = tasks.get(index);
            task.setDone(isDone);

            response.append(message).append("\n");
            response.append(task);

        } catch (InvalidIndexException e) {
            response.append(e.getMessage());
        }

        return response.toString();
    }

    /**
     * Deletes a task.
     *
     * @param body Body from the prompt.
     * @return Response to be displayed to the user interface.
     */
    public String deleteTask(String body) {
        StringBuilder response = new StringBuilder();
        try {
            int index = getIndexFromBody(body);
            Task task = tasks.remove(index);

            response.append("Noted. I've removed this task:").append("\n");
            response.append(task).append("\n");
            response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        } catch (InvalidIndexException e) {
            response.append(e.getMessage());
        }

        return response.toString();
    }

    /**
     * Returns the index from the input by the user.
     * If the index does not exist, an exception is thrown.
     *
     * @param body Body from the prompt.
     * @return Index that exist in the task list.
     * @throws InvalidIndexException If the index does not exist in the task list.
     */
    private int getIndexFromBody(String body) throws InvalidIndexException {
        int index;

        try {
            index = Integer.parseInt(body);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(body);
        }

        if (0 < index && index <= tasks.size()) {
            return index - 1;
        } else {
            throw new InvalidIndexException(body);
        }
    }

    /**
     * Creates a todo task from the input.
     * If the body of the input is empty, an exception is thrown.
     *
     * @param body Body from the prompt.
     * @return A new todo task.
     */
    public static ToDo createToDo(String body) {
        return new ToDo(body);
    }

    /**
     * Creates a deadline task from the input.
     * If the body of the input is empty or the format is invalid, an exception is thrown.
     *
     * @param body Body from the prompt.
     * @return A new deadline task.
     * @throws InvalidFormatException If the body is in the incorrect format.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public static Deadline createDeadline(String body) throws InvalidFormatException, DateTimeFormatException {
        String dueDateTag = " /by ";
        if (!body.contains(dueDateTag)) {
            throw new InvalidFormatException();
        }

        String[] content = body.split(dueDateTag, 2);
        return new Deadline(content[0], content[1]);
    }

    /**
     * Creates an event task from the input.
     * If the body of the input is empty or the format is invalid, an exception is thrown.
     *
     * @param body Body from the prompt.
     * @return A new event task.
     * @throws InvalidFormatException If the body is in the incorrect format.
     * @throws DateTimeFormatException If the date & time indicated is in the wrong format.
     */
    public static Event createEvent(String body) throws InvalidFormatException, DateTimeFormatException {
        String startDateTag = " /from ";
        String endDateTag = " /to ";
        boolean hasDateTags = body.contains(startDateTag) && body.contains(endDateTag);
        if (!hasDateTags) {
            throw new InvalidFormatException();
        }

        String[] content = body.split(startDateTag + "|" + endDateTag, 3);
        return new Event(content[0], content[1], content[2]);
    }

    /**
     * Returns an action that can be done/exist.
     * If the action cannot be handled, an exception is thrown.
     *
     * @param input Input from the prompt.
     * @return A valid action.
     */
    private static Action getActionFromInput(String input) {
        String action = input.split(" ", 2)[0];
        try {
            return Action.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Action.INVALID;
        }
    }

    /**
     * Adds a given task to the list.
     *
     * @param task Task
     * @return Response to be displayed to the user interface.
     */
    public String addTask(Task task) {
        StringBuilder response = new StringBuilder();
        tasks.add(task);
        response.append("Got it. I've added this task:").append("\n");
        response.append(task).append("\n");
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");

        return response.toString();
    }

    /**
     * Retrieves the tasks from the given file/storage.
     *
     * @return A list of tasks.
     */
    public ArrayList<Task> retrieveTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        ArrayList<String> records = new ArrayList<>();
        Task task;
        try {
            records = storage.retrieveRecords();
        } catch (IOException e) {
            Ui.displayToConsole("Could not retrieve tasks from storage.");
        }

        for (String record : records) {
            String[] data = record.split(",");
            switch (data[0]) {
            case "T":
                task = new ToDo(data[2]);
                break;
            case "D":
                try {
                    task = new Deadline(data[2], data[3]);
                } catch (DateTimeFormatException e) {
                    Ui.displayToConsole("easton.model.Deadline ("
                            + data[2]
                            + ") is using the wrong DateTime Format, the record is voided.");
                    continue;
                }
                break;
            case "E":
                try {
                    task = new Event(data[2], data[3], data[4]);
                } catch (DateTimeFormatException e) {
                    Ui.displayToConsole("easton.model.Event ("
                            + data[2]
                            + ") is using the wrong DateTime Format, the record is voided.");
                    continue;
                }
                break;
            default:
                continue;
            }

            task.setDone(data[1].equals("1"));
            taskArrayList.add(task);
        }

        return taskArrayList;
    }

    /**
     * Saves the tasks to the file/storage.
     */
    public void saveTasks() {
        ArrayList<String> records = new ArrayList<>();

        for (Task task : tasks) {
            records.add(task.getCsvFormat() + "\n");
        }

        storage.saveRecords(records);
    }

    /**
     * Finds tasks with a matching keyword in their description.
     *
     * @param body Body from the prompt.
     * @return Response to be displayed to the user interface.
     */
    public String findTasks(String body) {
        StringBuilder response = new StringBuilder();
        String[] keywords = body.split("\\s+");
        response.append(toNumberedTaskList(task -> task.hasKeywords(keywords)));

        if (response.isEmpty()) {
            response.append("No match was found.");
        } else {
            response.insert(0, "Here are the matching tasks in your list:");
        }

        return response.toString();
    }


    /**
     * Generates a response for the user's chat message.
     *
     * @return Response to the user's chat.
     */
    public String getResponse(String input) {
        Action action = getActionFromInput(input);
        try {
            String body = "";
            boolean hasABodyAction = action != Action.LIST && action != Action.BYE;
            if (hasABodyAction) {
                body = getBodyFromInput(input);
            }
            return performAction(action, body);
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }


    /**
     * Returns the body embedded inside the input.
     *
     * @param input Input from the prompt.
     * @return Body from the input.
     * @throws EmptyDescriptionException If the body of the prompt is empty.
     */
    public String getBodyFromInput(String input) throws EmptyDescriptionException {
        String[] splitInput = input.split("\\s+", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        return splitInput[1].trim();
    }

    /**
     * Performs the action with the given body.
     *
     * @param action Action from the prompt.
     * @param body Body from the prompt.
     * @return Response to be displayed to users.
     */
    public String performAction(Action action, String body) {
        StringBuilder response = new StringBuilder();

        switch (action) {
        case BYE:
            response.append(EXIT_RESPONSE);
            break;
        case LIST:
            response.append(LIST_HEADER_RESPONSE);
            response.append(toNumberedTaskList(task -> true));
            break;
        case MARK:
            response.append(changeTaskStatus(body, true, MARK_HEADER_RESPONSE));
            saveTasks();
            break;
        case UNMARK:
            response.append(changeTaskStatus(body, false, UNMARK_HEADER_RESPONSE));
            saveTasks();
            break;
        case TODO:
            ToDo todo = createToDo(body);
            response.append(addTask(todo));
            saveTasks();
            break;
        case DEADLINE:
            try {
                Deadline deadline = createDeadline(body);
                response.append(addTask(deadline));
                saveTasks();
            } catch (InvalidFormatException | DateTimeFormatException e) {
                response.append(e.getMessage());
            }
            break;
        case EVENT:
            try {
                Event event = createEvent(body);
                response.append(addTask(event));
                saveTasks();
            } catch (InvalidFormatException | DateTimeFormatException e) {
                response.append(e.getMessage());
            }
            break;
        case DELETE:
            response.append(deleteTask(body));
            saveTasks();
            break;
        case FIND:
            response.append(findTasks(body));
            break;
        case INVALID:
            response.append(INVALID_RESPONSE);
            break;
        default:
            break;
        }

        return response.toString();
    }

    /**
     * Generates a numbered task list.
     * The tasks can be filtered through with a predicate.
     *
     * @param predicate Predicate to filter the list of tasks.
     * @return A numbered task list as a string.
     */
    public String toNumberedTaskList(Predicate<Task> predicate) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (predicate.test(task)) {
                stringBuilder.append("\n").append(i + 1).append(".").append(task);
            }
        }
        return stringBuilder.toString();
    }
}
