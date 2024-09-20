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

    /**
     * Checks if the task is already in the this.tasks.
     *
     * @param testTask
     * @return
     */
    private boolean isTaskInTasksMemory(Task testTask) {
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

    /**
     * Handles the user inputs and performs the corresponding actions.
     *
     * @param message the user input message
     */
    private List<String> handleInputs(String message) {
        ui.newOutput();
        try {
            Command command = Command.getCommandFromString(message);
            String commandContents = command.getCommandContentsFromString(message);
            switch (command) {
            case LIST:
                ui.cacheList(tasks);
                break;
            case BYE:
                ui.cacheByeMessage();
                System.exit(0);
                break;
            case MARK:
                Task markTask = tasks.get(Integer.parseInt(commandContents) - 1);
                markTask.setStatus(true);
                ui.cacheMarkedDoneMessage(markTask);
                break;
            case UNMARK:
                Task unmarkTask = tasks.get(Integer.parseInt(commandContents) - 1);
                unmarkTask.setStatus(false);
                ui.cacheUnmarkDoneMessage(unmarkTask);
                break;
            case TODO:

                if (commandContents.isEmpty()) {
                    ui.cacheToDoExceptionMessage();
                    break;
                }
                ToDo toDo = new ToDo(commandContents, false);
                if (isTaskInTasksMemory(toDo)) {
                    ui.cacheTaskAlreadyExistsExceptionMessage();
                    break;
                }
                toDo.appendToStorage(FILE_PATH);
                tasks.add(toDo);
                ui.cacheToDoMessage(toDo, tasks.size());
                break;
            case DEADLINE:
                if (commandContents.isEmpty()) {
                    ui.cacheDeadlineExceptionMessage();
                    break;
                }
                String[] parts = commandContents.split(" /by ");
                String content = parts[0];
                String dateTimeString = parts[1];
                Deadline deadline = new Deadline(content, false, dateTimeString);
                if (isTaskInTasksMemory(deadline)) {
                    ui.cacheTaskAlreadyExistsExceptionMessage();
                    break;
                }
                deadline.appendToStorage(FILE_PATH);
                tasks.add(deadline);
                ui.cacheDeadlineMessage(deadline, tasks.size());
                break;
            case EVENT:
                if (commandContents.isEmpty()) {
                    ui.cacheEventExceptionMessage();
                    break;
                }
                String[] eventParts = commandContents.split(" /from ");
                String eventContent = eventParts[0];
                String[] timeParts = eventParts[1].split(" /to ");
                String from = timeParts[0];
                String to = timeParts[1];
                Event event = new Event(eventContent, false, from, to);
                if (isTaskInTasksMemory(event)) {
                    ui.cacheTaskAlreadyExistsExceptionMessage();
                    break;
                }
                event.appendToStorage(FILE_PATH);
                tasks.add(event);
                ui.cacheEventMessage(event, tasks.size());
                break;
            case DELETE:
                Task deleteTask = tasks.get(Integer.parseInt(commandContents) - 1);
                tasks.remove(deleteTask);
                ui.cacheTaskAlreadyExistsExceptionMessage();
                break;
            case FIND:
                String contentPart = commandContents;
                ArrayList<Task> foundTasks = findTasksWithContent(contentPart);
                ui.cacheFindMessage(foundTasks);
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
}
