package sinatra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the main Sinatra application.
 */
public class Sinatra {

    private static final String FILE_PATH = "tasks.txt";
    private static final String INTRO_MESSAGE = "Hello! I'm Sinatra. \nWhat can I do for you?";
    private static final String LIST_OUTPUT_START = "Here are the tasks in your list:";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String MARKED_DONE_MESSAGE = "Nice! I've marked this task as done: ";
    private static final String MARKED_NOT_DONE_MESSAGE = "OK, I've marked this task as not done yet: ";
    private static final String TODO_EXCEPTION_MESSAGE = "OOPS!!! The description of a todo cannot be empty.";
    private static final String DEADLINE_EXCEPTION_MESSAGE = "OOPS!!! The description of a deadline cannot be empty.";
    private static final String EVENT_EXCEPTION_MESSAGE = "OOPS!!! The description of an event cannot be empty.";
    private static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:";
    private static final String REMOVED_TASK_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.";
    private static final String FIND_OUTPUT_START = "Here are the matching tasks in your list:";
    private static final String UNKNOWN_COMMAND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred: ";

    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a new Sinatra object, initializes tasks, prints the introduction,
     * loads tasks from storage, and starts the scanner for user input.
     */
    public Sinatra() {
        this.tasks = new ArrayList<Task>();
        this.printIntro();
        this.storage = new Storage(FILE_PATH);
        this.tasks = storage.loadTasksFromFile();
    }

    /**
     * Prints the introduction message.
     */
    private void printIntro() {
        System.out.println(INTRO_MESSAGE);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        List<String> output = handleInputs(input);
        StringBuilder response = new StringBuilder();
        for (String s : output) {
            response.append(s).append("\n");
        }
        return "Sinatra: \n" + response.toString();
    }

    private ArrayList<Task> findTasksWithContent(String contentPart) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getContent().contains(contentPart)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Starts the scanner to handle user inputs.
     */
    private void sinatraScanner() {
        Scanner scanner = new Scanner(System.in);
        List<String> output;
        while (true) {
            String input = scanner.nextLine();
            output = handleInputs(input);
            for (String s : output) {
                System.out.println(s);
            }
        }
    }

    /**
     * Handles the user inputs and performs the corresponding actions.
     *
     * @param message the user input message
     */
    private List<String> handleInputs(String message) {
        List<String> output = new ArrayList<>();
        try {
            Command command = Command.getCommandFromString(message);
            String commandContents = command.getCommandContentsFromString(message);
            System.out.println(commandContents);
            switch (command) {
            case LIST:
                output.add(LIST_OUTPUT_START);
                for (int i = 0; i < tasks.size(); i++) {
                    int count = i + 1;
                    output.add(count + "." + tasks.get(i).toString());
                }
                break;
            case BYE:
                output.add(BYE_MESSAGE);
                System.exit(0);
                break;
            case MARK:
                Task markTask = tasks.get(Integer.parseInt(commandContents) - 1);
                markTask.setStatus(true);
                output.add(MARKED_DONE_MESSAGE);
                output.add("  " + markTask);
                break;
            case UNMARK:
                Task unmarkTask = tasks.get(Integer.parseInt(commandContents) - 1);
                unmarkTask.setStatus(false);
                output.add(MARKED_NOT_DONE_MESSAGE);
                output.add("  " + unmarkTask);
                break;
            case TODO:
                if (message.length() <= Command.TODO.getCommand().length()) {
                    throw new SinatraException(TODO_EXCEPTION_MESSAGE);
                }
                ToDo toDo = new ToDo(commandContents, false);
                toDo.appendToStorage(FILE_PATH);
                tasks.add(toDo);
                output.add(ADDED_TASK_MESSAGE);
                output.add("  " + tasks.get(tasks.size() - 1));
                output.add(String.format(TASK_COUNT_MESSAGE, tasks.size()));
                break;
            case DEADLINE:
                if (message.length() <= Command.DEADLINE.getCommand().length()) {
                    throw new SinatraException(DEADLINE_EXCEPTION_MESSAGE);
                }
                String[] parts = commandContents.split(" /by ");
                String content = parts[0];
                String dateTimeString = parts[1];
                Deadline deadline = new Deadline(content, false, dateTimeString);
                deadline.appendToStorage(FILE_PATH);
                tasks.add(deadline);
                output.add(ADDED_TASK_MESSAGE);
                output.add("  " + tasks.get(tasks.size() - 1));
                output.add(String.format(TASK_COUNT_MESSAGE, tasks.size()));
                break;
            case EVENT:
                if (message.length() <= Command.EVENT.getCommand().length()) {
                    throw new SinatraException(EVENT_EXCEPTION_MESSAGE);
                }
                String[] eventParts = commandContents.split(" /from ");
                String eventContent = eventParts[0];
                String[] timeParts = eventParts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];
                Event event = new Event(eventContent, false, from, to);
                event.appendToStorage(FILE_PATH);
                tasks.add(event);
                output.add(ADDED_TASK_MESSAGE);
                output.add("  " + tasks.get(tasks.size() - 1));
                output.add(String.format(TASK_COUNT_MESSAGE, tasks.size()));
                break;
            case DELETE:
                Task deleteTask = tasks.get(Integer.parseInt(commandContents) - 1);
                tasks.remove(deleteTask);
                output.add(REMOVED_TASK_MESSAGE);
                output.add("  " + deleteTask);
                output.add(String.format(TASK_COUNT_MESSAGE, tasks.size()));
                break;
            case FIND:
                String contentPart = commandContents;
                ArrayList<Task> foundTasks = findTasksWithContent(contentPart);
                output.add(FIND_OUTPUT_START);
                for (int i = 0; i < foundTasks.size(); i++) {
                    output.add(i + 1 + "." + foundTasks.get(i).toString());
                }
                break;
            default:
                output.add(UNKNOWN_COMMAND_MESSAGE);
                break;
            }
        } catch (SinatraException e) {
            output.add(e.getMessage());
        } catch (Exception e) {
            output.add(UNEXPECTED_ERROR_MESSAGE + e.getMessage());
        }
        return output;
    }
}
