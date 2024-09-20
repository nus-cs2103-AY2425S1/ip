package sinatra;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The Parser class is responsible for parsing user input.
 */
public class Parser {
    private static final String FILE_PATH = "tasks.txt";
    private ArrayList<Task> tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new Parser instance.
     */
    public Parser() {
        this.tasks = new ArrayList<Task>();
        ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.tasks = storage.loadTasksFromFile();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Starts the parser.
     */
    public void deleteAllTasks() {
        tasks.clear();
        storage.clear();
    }

    /**
     * Checks if the task is already in the this.tasks.
     *
     * @param testTask
     * @return
     */
    public boolean isTaskInTasksMemory(Task testTask) {
        for (Task task : tasks) {
            if (Objects.equals(task.getContent(), testTask.getContent())) {
                return true;
            }
        }
        return false;
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
        return response.toString();
    }

    /**
     * Starts the scanner to handle user inputs.
     */
    private void parserScanner() {
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

    private ArrayList<Task> findTasksWithContent(String contentPart) {
        return tasks.stream()
                .filter(task -> task.getContent().contains(contentPart))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Handles the user inputs and performs the corresponding actions.
     *
     * @param message the user input message
     */
    public List<String> handleInputs(String message) {
        ui.newOutput();
        try {
            Command command = Command.getCommandFromString(message);
            String commandContents = command.getCommandContentsFromString(message);
            switch (command) {
            case LIST:
                handleList();
                break;
            case BYE:
                handleBye();
                break;
            case MARK:
                handleMark(commandContents);
                break;
            case UNMARK:
                handleUnmark(commandContents);
                break;
            case TODO:
                handleTodo(commandContents);
                break;
            case DEADLINE:
                handleDeadline(commandContents);
                break;
            case EVENT:
                handleEvent(commandContents);
                break;
            case DELETE:
                handleDelete(commandContents);
                break;
            case FIND:
                handleFind(commandContents);
                break;
            default:
                ui.cacheUnknownCommandMessage();
                break;
            }
        } catch (Exception e) {
            ui.cacheUnexpectedErrorMessage(e);
        }
        return ui.getOutput();
    }

    private void handleList() {
        ui.cacheList(tasks);
    }

    private void handleBye() {
        ui.cacheByeMessage();
        System.exit(0);
    }

    private void handleMark(String commandContents) {
        Task markTask = tasks.get(Integer.parseInt(commandContents) - 1);
        markTask.setStatus(true);
        ui.cacheMarkedDoneMessage(markTask);
    }

    private void handleUnmark(String commandContents) {
        Task unmarkTask = tasks.get(Integer.parseInt(commandContents) - 1);
        unmarkTask.setStatus(false);
        ui.cacheUnmarkDoneMessage(unmarkTask);
    }

    private void handleTodo(String commandContents) {
        if (commandContents.isEmpty()) {
            ui.cacheToDoExceptionMessage();
            return;
        }
        ToDo toDo = new ToDo(commandContents, false);
        if (isTaskInTasksMemory(toDo)) {
            ui.cacheTaskAlreadyExistsExceptionMessage();
            return;
        }
        toDo.appendToStorage(FILE_PATH);
        tasks.add(toDo);
        ui.cacheToDoMessage(toDo, tasks.size());
    }

    private void handleDeadline(String commandContents) {
        if (commandContents.isEmpty() || !commandContents.contains(" /by ")) {
            ui.cacheDeadlineExceptionMessage();
            return;
        }
        String[] parts = commandContents.split(" /by ");
        String content = parts[0];
        String dateTimeString = parts[1];
        Deadline deadline = new Deadline(content, false, dateTimeString);
        if (isTaskInTasksMemory(deadline)) {
            ui.cacheTaskAlreadyExistsExceptionMessage();
            return;
        }
        deadline.appendToStorage(FILE_PATH);
        tasks.add(deadline);
        ui.cacheDeadlineMessage(deadline, tasks.size());
    }

    private void handleEvent(String commandContents) {
        if (commandContents.isEmpty() || !commandContents.contains(" /from ") || !commandContents.contains(" /to ")) {
            ui.cacheEventExceptionMessage();
            return;
        }
        String[] eventParts = commandContents.split(" /from ");
        String eventContent = eventParts[0];
        String[] timeParts = eventParts[1].split(" /to ");
        String from = timeParts[0];
        String to = timeParts[1];
        Event event = new Event(eventContent, false, from, to);
        if (isTaskInTasksMemory(event)) {
            ui.cacheTaskAlreadyExistsExceptionMessage();
            return;
        }
        event.appendToStorage(FILE_PATH);
        tasks.add(event);
        ui.cacheEventMessage(event, tasks.size());
    }

    private void handleDelete(String commandContents) {
        Task deleteTask = tasks.get(Integer.parseInt(commandContents) - 1);
        deleteTask.deleteFromStorage(FILE_PATH, Integer.parseInt(commandContents) - 1);
        tasks.remove(deleteTask);
        ui.cacheDeleteMessage(deleteTask, tasks.size());
    }

    private void handleFind(String commandContents) {
        String contentPart = commandContents;
        ArrayList<Task> foundTasks = findTasksWithContent(contentPart);
        ui.cacheFindMessage(foundTasks);
    }
}
